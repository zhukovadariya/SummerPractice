package lesson3.repositories;

import lesson3.models.Course;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class CoursesRepositorySpringJdbcImpl implements CoursesRepository {
    private static final String COURSES_TABLE = "course";

    private final static String SQL_SELECT_ALL = "select * from course";

    private final DataSource dataSource;

    private final SimpleJdbcInsert insertCourse;

    private final JdbcTemplate jdbcTemplate;

    public CoursesRepositorySpringJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;

        jdbcTemplate = new JdbcTemplate(dataSource);

        insertCourse = new SimpleJdbcInsert(dataSource);

        insertCourse.withTableName(COURSES_TABLE)
                .usingGeneratedKeyColumns("id");
    }

    private static final Function<Course, Map<String, Object>> toParams = course -> {
        Map<String, Object> params = new HashMap<>();

        params.put("name", course.getName());
        params.put("start_date", course.getStartDate());
        params.put("finish_date", course.getFinishDate());

        return params;
    };

    private static final RowMapper<Course> toCourse = (row, rowNumber) -> Course.builder()
            .id(row.getInt("id"))
            .name(row.getString("name"))
            .startDate(row.getDate("start_date"))
            .finishDate(row.getDate("finish_date"))
            .build();



    @Override
    public void save(Course model) {
        int generatedId = insertCourse.executeAndReturnKey(toParams.apply(model)).intValue();

        model.setId(generatedId);
    }

    @Override
    public List<Course> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, toCourse);
    }
}
