package ru.alphalab.testtask.hibernate.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;

/**
 * @author Ilya Avkhimenya
 */
@MappedSuperclass
public class BaseEntity {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "CREATED_AT", nullable = false)
    @CreationTimestamp
    private Instant createdAt;
    @Column(name = "UPDATED_AT", nullable = false)
    @UpdateTimestamp
    private Instant updatedAt;
}
