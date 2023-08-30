package com.example.healthtourismapplication.util;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@MappedSuperclass
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class BaseEntityWithoutAutoId {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    @CreatedDate
    private Date creationDate;

    @LastModifiedDate
    private Date updateDate;
}
