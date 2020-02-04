package br.com.clinic.domain;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public abstract class ClinicModel implements Serializable {

    public static final String SEQ_NAME = "SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
    private Long id;

    @Version
    @Column(name = "VERSION")
    private Integer version;

    public Long getId() {
        return id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

}
