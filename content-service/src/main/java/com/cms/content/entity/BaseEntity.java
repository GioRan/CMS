package com.cms.content.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Data
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BaseEntity implements Serializable {

    @Column(name = "when_added")
    private Long	whenAdded;

    @Column(name = "who_added")
    private Integer	whoAdded;

    @Column(name = "when_updated")
    private Long	whenUpdated;

    @Column(name = "who_updated")
    private Integer	whoUpdated;

    @PrePersist
    public void prePersist() {
        LocalDateTime time = LocalDateTime.now();
        whenAdded = time.toEpochSecond(ZoneOffset.UTC);
    }

    @PreUpdate
    public void preUpdate() {
        LocalDateTime time = LocalDateTime.now();
        whenUpdated = time.toEpochSecond(ZoneOffset.UTC);
    }
}
