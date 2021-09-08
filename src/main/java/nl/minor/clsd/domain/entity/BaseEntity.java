package nl.minor.clsd.domain.entity;

import javax.persistence.*;

@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /** Empty constructor for creation **/
    protected BaseEntity(){}

    /************ Setters & Getters ************/
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
}
