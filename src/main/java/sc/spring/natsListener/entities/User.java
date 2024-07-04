package sc.spring.natsListener.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class User {

    @Id
    private long id;
    private String name;
    private int age;
    private int salary;
    private Double averageDepartmentSalary;
    private String department;
}
