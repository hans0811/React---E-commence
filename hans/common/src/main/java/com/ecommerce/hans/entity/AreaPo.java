package com.ecommerce.hans.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "AREA")
public class AreaPo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "AREA_CODE")
    private String areaCode;

    @Id
    @Column(name = "AREA_NAME")
    private String areaName;

    @Basic
    @Column(name = "CITY_NO")
    private String cityNo;

    @Id
    @Column(name = "LOCALE")
    private String locale;


}
