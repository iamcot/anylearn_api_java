package com.anylearn.anylearn_api.domain.course.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "items")
@Data
public class Item {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String type;

    private String subType;

    private Long userId;

    private String image;

    private String shortContent;

    private Long price;

    private Long orgPrice;

    private Float commissionRate;

    private String companyCommission;

    private Date dateStart;

    private Date dateEnd;

    private String locationType;

    private String location;

    private Boolean isHot;

    private Boolean status;

    private Boolean userStatus;

    private Date createdAt;

    private Date updatedAt;

    private String tags;

    private Boolean nolimitTime;
}
