package sc.spring.natsListener.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Users")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class User {

    @Id
    @SequenceGenerator(name = "users_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
    private long id;

    private String name;

    private int age;

    private int salary;

    @Column(name = "average_department_salary")
    private Double averageDepartmentSalary;

    private int department;

    public User(String name, int age, int salary, int department) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.department = department;
    }
}
