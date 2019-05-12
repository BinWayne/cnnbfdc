package com.ibm.cnnbfdc.entity;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "saleshouse")
@ToString
@Cacheable
public class SalesHouseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String projectName;//项目名称
    private String regionName;//项目区域
    private int saleCount;//销售套数
    private double saleArea;//销售面积
    @Column(name = "created_at")
    private Date createdAt;

    @PrePersist
    void createdAt() {
        this.createdAt =  new Date();
    }

}
