package lesson3.repositories;

import lesson3.models.Course;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CoursesRepositoryJdbcImpl implements CoursesRepository{
    //language=sql
    private final static String SQL_SELECT_ALL = "select * from course";

    //language=sql
    private final static String SQL_INSERT = "insert into course(name, start_date, finish_date) values (?, ?, ?)";

    private final DataSource dataSource;

    public CoursesRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public void save(Course model) {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, model.getName());
                statement.setDate(2, (Date) model.getStartDate());
                statement.setDate(3, (Date) model.getFinishDate());

                int affectedRows = statement.executeUpdate();

                if (affectedRows != 1) {
                    throw new SQLException("Cannot insert course");
                }

                try (ResultSet generatedIds = statement.getGeneratedKeys()){
                    if (generatedIds.next()) {
                        model.setId(generatedIds.getInt("id"));
                    } else {
                        throw new SQLException("Cannot retrieve id");
                    }
                }

            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }

    }

    @Override
    public List<Course> findAll() {
        List<Course> courses = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            try (ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL)) {
                while (resultSet.next()) {
                    Course course = Course.builder()
                            .id(resultSet.getInt("id"))
                            .name(resultSet.getString("name"))
                            .startDate(resultSet.getDate("start_date"))
                            .finishDate(resultSet.getDate("finish_date"))
                            .build();

                    courses.add(course);
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return courses;
    }
}




