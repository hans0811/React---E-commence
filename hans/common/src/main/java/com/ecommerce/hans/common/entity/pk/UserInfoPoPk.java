package com.ecommerce.hans.common.entity.pk;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class UserInfoPoPk implements Serializable {

    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "USERNAME")
    private String username;

    public UserInfoPoPk() {
    }

    public UserInfoPoPk(Integer id, String username) {
        this.id = id;
        this.username = username;
    }

    /**
     * @param { id}
     */
    public Integer getId() {
        return id;
    }

    /**
     * @return { id }
     */
    public void setId(Integer id) {
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


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        UserInfoPoPk other = (UserInfoPoPk) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;


        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        return result;
    }
}
