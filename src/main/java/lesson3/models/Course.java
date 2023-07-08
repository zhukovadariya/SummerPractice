package lesson3.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {

    private Integer id;
    private String name;
    private Date startDate;
    private Date finishDate;
}