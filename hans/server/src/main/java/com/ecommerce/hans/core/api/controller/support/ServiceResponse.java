package com.ecommerce.hans.core.api.controller.support;

import javax.xml.crypto.Data;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

public class ServiceResponse<T> {

    private String resultCode;

    private String resultDescription;

    private Map<String, Object> resultModel;

    private Collection<String> alertMessages;

    private Collection<String> errorMessages;

    private Collection<String> warningMessages;

    private Date responseTime = new Date();

    private Paging paging;

    /**
     * return response
     */
    private T model;

    public ServiceResponse(){

    }



}
