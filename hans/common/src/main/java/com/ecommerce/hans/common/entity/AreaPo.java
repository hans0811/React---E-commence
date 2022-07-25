package com.ecommerce.hans.common.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "AREA")
public class AreaPo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "AREA_CODE")
    private String areaCode;

    @Basic
    @Column(name = "AREA_NAME")
    private String areaName;

    @Basic
    @Column(name = "CITY_NO")
    private String cityNo;

    @Basic
    @Column(name = "LOCALE")
    private String locale;

    /**
     * @param { areaCode}
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * @return { areaCode }
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    /**
     * @param { areaName}
     */
    public String getAreaName() {
        return areaName;
    }

    /**
     * @return { areaName }
     */
    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    /**
     * @param { cityNo}
     */
    public String getCityNo() {
        return cityNo;
    }

    /**
     * @return { cityNo }
     */
    public void setCityNo(String cityNo) {
        this.cityNo = cityNo;
    }

    /**
     * @param { locale}
     */
    public String getLocale() {
        return locale;
    }

    /**
     * @return { locale }
     */
    public void setLocale(String locale) {
        this.locale = locale;
    }
}
