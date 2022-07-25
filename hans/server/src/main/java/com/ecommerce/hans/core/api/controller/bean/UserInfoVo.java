package com.ecommerce.hans.core.api.controller.bean;

import com.ecommerce.hans.common.model.UserPo;

import java.util.Set;

public class UserInfoVo extends UserPo {

    private String id;

    private String prevGroupId;

    private Set<String> userFuncs;

    /**
     * @param { id}
     */
    public String getId() {
        return id;
    }

    /**
     * @return { id }
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @param { prevGroupId}
     */
    public String getPrevGroupId() {
        return prevGroupId;
    }

    /**
     * @return { prevGroupId }
     */
    public void setPrevGroupId(String prevGroupId) {
        this.prevGroupId = prevGroupId;
    }

    /**
     * @param { userFuncs}
     */
    public Set<String> getUserFuncs() {
        return userFuncs;
    }

    /**
     * @return { userFuncs }
     */
    public void setUserFuncs(Set<String> userFuncs) {
        this.userFuncs = userFuncs;
    }
}
