package com.cms.content.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "content")
public class ContentEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "content_id", columnDefinition = "INT UNSIGNED NOT NULL")
    private Integer contentId;

    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "content_uuid", columnDefinition = "VARCHAR(255) UNIQUE")
    private String contentUuid = UUID.randomUUID().toString();

    @JsonBackReference
    @OneToMany(mappedBy = "content", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<ElementEntity> elements;
}