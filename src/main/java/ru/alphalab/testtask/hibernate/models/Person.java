package ru.alphalab.testtask.hibernate.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author Ilya Avkhimenya
 */
@Getter
@Setter
@Entity
@Table(name = "persons")
public class Person extends BaseEntity {
    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;
    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;
    @Column(name = "SECOND_NAME")
    private String secondName;
    @Column(name = "B_DATE")
    private Date dateOfBirth;

    // gender, passport details, person creator, etc.
}
