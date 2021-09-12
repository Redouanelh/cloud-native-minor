package nl.minor.clsd.domain.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @CreationTimestamp
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime lastModified;

    /** Empty constructor for creation **/
    protected BaseEntity(){}

    /************ Setters & Getters ************/
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
}
