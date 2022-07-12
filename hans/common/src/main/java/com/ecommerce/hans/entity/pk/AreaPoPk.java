package com.ecommerce.hans.entity.pk;

import java.io.Serializable;

public class AreaPoPk implements Serializable {
    private static final long serialVersionUID = 1683648897045185415L;
    private String areaCode;
    private String areaName;
    private String locale;

    public AreaPoPk() {}

    public AreaPoPk(String areaCode, String areaName, String locale) {
        this.areaCode = areaCode;
        this.areaName = areaName;
        this.locale = locale;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String toString() {
        return areaCode + "::" + areaName + "::" + locale;
    }

    public int hashCode() {
        int rs = 17;
        rs = rs * 37 + ((areaCode == null) ? 0 : areaCode.hashCode());
        rs = rs * 37 + ((areaName == null) ? 0 : areaName.hashCode());
        rs = rs * 37 + ((locale == null) ? 0 : locale.hashCode());
        return rs;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }

        AreaPoPk other = (AreaPoPk) obj;
        return ((areaCode == null && other.areaCode == null) || (areaCode != null && areaCode.equals(other.areaCode)))
                && ((areaName == null && other.areaName == null) || (areaName != null && areaName.equals(other.areaName)))
                && ((locale == null && other.locale == null) || (locale != null && locale.equals(other.locale)));
    }
}
