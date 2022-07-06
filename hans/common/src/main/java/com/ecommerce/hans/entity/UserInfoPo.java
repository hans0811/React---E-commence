package com.ecommerce.hans.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "USER_INFO")
public class UserInfoPo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "USERNAME", nullable = false)
    private String username;

    @Column(name = "ACCOUNT_TYPE")
    private String accountType;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "FIRST_NAME")
    private String firstNAME;

    @Column(name = "LAST_NAME")
    private String lastNAME;

    @Column(name = "BIRTHDAY")
    private String birthday;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "MOBILE_NO")
    private String mobileNo;

    @Basic
    @Column(name = "UPDATE_TIME")
    @LastModifiedDate
    private Date updateTime;


    @Basic
    @Column(name = "CREATE_TIME")
    @CreatedDate
    private Date createTime;

    public UserInfoPo() {
    }

    /**
     * @param { id}
     */
    public Long getId() {
        return id;
    }

    /**
     * @return { id }
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @param { username}
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return { username }
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @param { accountType}
     */
    public String getAccountType() {
        return accountType;
    }

    /**
     * @return { accountType }
     */
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    /**
     * @param { password}
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return { password }
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @param { firstNAME}
     */
    public String getFirstNAME() {
        return firstNAME;
    }

    /**
     * @return { firstNAME }
     */
    public void setFirstNAME(String firstNAME) {
        this.firstNAME = firstNAME;
    }

    /**
     * @param { lastNAME}
     */
    public String getLastNAME() {
        return lastNAME;
    }

    /**
     * @return { lastNAME }
     */
    public void setLastNAME(String lastNAME) {
        this.lastNAME = lastNAME;
    }

    /**
     * @param { birthday}
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * @return { birthday }
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
     * @param { email}
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return { email }
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @param { mobileNo}
     */
    public String getMobileNo() {
        return mobileNo;
    }

    /**
     * @return { mobileNo }
     */
    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    /**
     * @param { updateTime}
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @return { updateTime }
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @param { createTime}
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @return { createTime }
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}

