package com.ecommerce.hans.dao.impl;

import com.ecommerce.hans.dao.support.SqlBuilder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;


@Repository
public class SqlDaoCustomerImpl {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Map<String,Object>> query(String sql){
        return null;
    }


}
