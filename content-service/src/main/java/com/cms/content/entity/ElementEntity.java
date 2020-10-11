package com.cms.content.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "element")
public class ElementEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "element_id", columnDefinition = "INT(11) UNSIGNED NOT NULL")
    private Integer elementId;

    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "element_uuid", columnDefinition = "VARCHAR(255) UNIQUE")
    private String elementUuid = UUID.randomUUID().toString();

    @Column(name = "type", columnDefinition = "VARCHAR(255)")
    private String type;

    @Column(name = "value", columnDefinition = "VARCHAR(255)")
    private String value;

    @Column(name = "class_name", columnDefinition = "VARCHAR(255)")
    private String className;

    @Column(name = "id_name", columnDefinition = "VARCHAR(255)")
    private String idName;

//    @Column(name = "x", columnDefinition = "DECIMAL(10, 5)")
    @Column(name = "x", columnDefinition = "VARCHAR(255)")
    private String x;

    @Column(name = "y", columnDefinition =  "VARCHAR(255)")
    private String y;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_id")
    private ContentEntity content;
}