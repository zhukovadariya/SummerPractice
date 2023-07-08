package lesson3.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {

    private Integer id;
    private String firstName;
    private String lastName;
    private Integer age;
}


