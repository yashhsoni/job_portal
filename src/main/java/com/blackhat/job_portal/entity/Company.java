package com.blackhat.job_portal.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;


import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "COMPANIES")
@Getter
@Setter
public class Company extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID",nullable = false)
    private Long id;

    @Column(name = "NAME",nullable = false,unique = true)
    private String name;

    @Column(name = "LOGO", length = 500)
    private String logo;

    @Column(name = "INDUSTRY", nullable = false, length = 100)
    private String industry;

    @Column(name = "SIZE", nullable = false, length = 50)
    private String size;

    @Column(name = "RATING", nullable = false, precision = 3, scale = 2)
    private BigDecimal rating;

    @Column(name = "LOCATIONS", length = 1000)
    private String locations;

    @Column(name = "FOUNDED", nullable = false)
    private Integer founded;

//    @Lob
//    @Column(name = "DESCRIPTION")
//    private String description;


    @Lob
    @JdbcTypeCode(SqlTypes.LONGVARCHAR)      // tell Hibernate to use a text-like type, not OID
    @Column(columnDefinition = "TEXT", name="DESCRIPTION")
    private String description;


    @Column(name = "EMPLOYEES")
    private Integer employees;

    @Column(name = "WEBSITE", length = 500)
    private String website;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Job> jobs = new ArrayList<>();

}
