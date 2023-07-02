package lesson1.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // toString, Getter/Setter, equals, hashCode
@AllArgsConstructor // конструктор со всеми параметрами
@NoArgsConstructor // конструктор без параметров
@Builder // специальный функционал для удобного создания объектов
public class User {
    private String id;

    private String email;
    private String password;
}

