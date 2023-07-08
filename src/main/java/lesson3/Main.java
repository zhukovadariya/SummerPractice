package lesson3;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lesson3.models.Course;
import lesson3.models.Student;
import lesson3.repositories.CoursesRepository;
import lesson3.repositories.CoursesRepositoryJdbcImpl;
import lesson3.repositories.StudentsRepository;
import lesson3.repositories.StudentsRepositoryJdbcImpl;
import lombok.SneakyThrows;

import java.text.SimpleDateFormat;

public class Main {
    @SneakyThrows
    public static void main(String[] args) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:postgresql://localhost:54321/taxi_db");
        hikariConfig.setUsername("postgres");
        hikariConfig.setPassword("3274232742");
        hikariConfig.setDriverClassName("org.postgresql.Driver");

        //STUDENT
        HikariDataSource dataSourceForStudent = new HikariDataSource(hikariConfig);

        StudentsRepository studentsRepository = new StudentsRepositoryJdbcImpl(dataSourceForStudent);

        Student student = Student.builder()
                .firstName("Dariya")
                .lastName("Zhukova")
                .age(18)
                .build();
        studentsRepository.save(student);
        System.out.println(student);
        System.out.println(studentsRepository.findAll());

        //COURSE
        HikariDataSource dataSourceForCourse = new HikariDataSource(hikariConfig);

        CoursesRepository coursesRepository = new CoursesRepositoryJdbcImpl(dataSourceForCourse);

        Course course = Course.builder()
                .name("Java")
                .startDate(new java.sql.Date((new SimpleDateFormat( "dd.MM.yyyy" ).parse( "29.06.2023" )).getTime()))
                .finishDate(new java.sql.Date((new SimpleDateFormat( "dd.MM.yyyy" ).parse( "12.07.2023" )).getTime()))
                .build();

        coursesRepository.save(course);
        System.out.println(course);
        System.out.println(coursesRepository.findAll());
    }
}
