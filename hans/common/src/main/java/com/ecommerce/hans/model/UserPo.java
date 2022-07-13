package com.ecommerce.hans.model;

import org.apache.commons.lang3.StringUtils;

public class UserPo {

    public static final String STATUS_ENABLED = "ENABLED";

    public static final String STATUS_DISABLED = "DISABLED";

    public static final String KEY_MEMBER_NO = "MEMBER_NO";

    public static final String KEY_STATUS = "STATUS";

    public static final String KEY_UPDATED_TIME = "UPDATED_TIME";

    public static final String[] KEYS = {
            KEY_MEMBER_NO,
            KEY_STATUS,
            KEY_UPDATED_TIME
    };


    private String userId;

    private String employeeNO;

    private String firstName;

    private String lastName;

    /** 電子郵件 */
    private String email;

    /** 密碼 */
    private String password;

    /** 角色ID */
    private String groupId;

    /** 角色名稱 */
    private String groupName;

    /** 部門ID */
    private String deptId;

    /** 部門名稱 */
    private String deptName;

    private String status;

    private String updatedTime;

    private Number pwdExpiredTime;

    public String getUserName(){
        return StringUtils.defaultString(firstName, StringUtils.EMPTY)
                + StringUtils.defaultString(lastName, StringUtils.EMPTY);
    }


    /**
     * @param { userId}
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @return { userId }
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @param { employeeNO}
     */
    public String getEmployeeNO() {
        return employeeNO;
    }

    /**
     * @return { employeeNO }
     */
    public void setEmployeeNO(String employeeNO) {
        this.employeeNO = employeeNO;
    }

    /**
     * @param { firstName}
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @return { firstName }
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @param { lastName}
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @return { lastName }
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
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
     * @param { groupId}
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * @return { groupId }
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    /**
     * @param { groupName}
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * @return { groupName }
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * @param { deptId}
     */
    public String getDeptId() {
        return deptId;
    }

    /**
     * @return { deptId }
     */
    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    /**
     * @param { deptName}
     */
    public String getDeptName() {
        return deptName;
    }

    /**
     * @return { deptName }
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    /**
     * @param { status}
     */
    public String getStatus() {
        return status;
    }

    /**
     * @return { status }
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @param { updatedTime}
     */
    public String getUpdatedTime() {
        return updatedTime;
    }

    /**
     * @return { updatedTime }
     */
    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }

    /**
     * @param { pwdExpiredTime}
     */
    public Number getPwdExpiredTime() {
        return pwdExpiredTime;
    }

    /**
     * @return { pwdExpiredTime }
     */
    public void setPwdExpiredTime(Number pwdExpiredTime) {
        this.pwdExpiredTime = pwdExpiredTime;
    }
}
