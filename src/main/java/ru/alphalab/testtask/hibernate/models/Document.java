package ru.alphalab.testtask.hibernate.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Ilya Avkhimenya
 */
@Getter
@Setter
@Entity
@Table(name = "documents")
public class Document extends BaseEntity {

    @Column(name = "DOCUMENT_NUMBER")
    private Long documentNumber;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DATA")
    private byte[] data;

    @Column(name = "DOCUMENT_ACTIVE")
    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "PERSON_ID")
    private Person person;
}
