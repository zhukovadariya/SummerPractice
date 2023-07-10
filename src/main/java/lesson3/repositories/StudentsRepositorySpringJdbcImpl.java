package lesson3.repositories;

import lesson3.models.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class StudentsRepositorySpringJdbcImpl implements StudentsRepository {

    private static final String STUDENT_TABLE = "student";

    private final static String SQL_SELECT_ALL = "select * from student";

    private final DataSource dataSource;

    private final SimpleJdbcInsert insertStudent;

    private final JdbcTemplate jdbcTemplate;

    public StudentsRepositorySpringJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;

        jdbcTemplate = new JdbcTemplate(dataSource);

        insertStudent = new SimpleJdbcInsert(dataSource);

        insertStudent.withTableName(STUDENT_TABLE)
                .usingGeneratedKeyColumns("id");
    }

    private static final Function<Student, Map<String, Object>> toParams = student -> {
        Map<String, Object> params = new HashMap<>();

        params.put("first_name", student.getFirstName());
        params.put("last_name", student.getLastName());
        params.put("age", student.getAge());

        return params;
    };

    private static final RowMapper<Student> toStudent = (row, rowNumber) -> Student.builder()
            .id(row.getInt("id"))
            .firstName(row.getString("first_name"))
            .lastName(row.getString("last_name"))
            .age(row.getInt("age"))
            .build();


    @Override
    public void save(Student model) {
        int generatedId = insertStudent.executeAndReturnKey(toParams.apply(model)).intValue();

        model.setId(generatedId);

    }

    @Override
    public List<Student> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, toStudent);
    }
}


