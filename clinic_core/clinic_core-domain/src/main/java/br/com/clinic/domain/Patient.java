package br.com.clinic.domain;

import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Audited
@Entity(name = "PATIENT")
@Table(name = "PATIENT")
@PrimaryKeyJoinColumn(name="id")
public class Patient extends Person{

    public Patient() { }

}
