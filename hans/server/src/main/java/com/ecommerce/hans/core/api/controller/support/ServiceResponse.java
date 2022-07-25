package com.ecommerce.hans.core.api.controller.support;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

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

    @JsonCreator
    public ServiceResponse(){
        this.model = model;
    }

    /**
     * @param { resultCode}
     */
    public String getResultCode() {
        return resultCode;
    }

    /**
     * @return { resultCode }
     */
    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    /**
     * @param { resultDescription}
     */
    public String getResultDescription() {
        return resultDescription;
    }

    /**
     * @return { resultDescription }
     */
    public void setResultDescription(String resultDescription) {
        this.resultDescription = resultDescription;
    }

    /**
     * @param { resultModel}
     */
    public Map<String, Object> getResultModel() {
        return resultModel;
    }

    /**
     * @return { resultModel }
     */
    public void setResultModel(Map<String, Object> resultModel) {
        this.resultModel = resultModel;
    }

    /**
     * @param { alertMessages}
     */
    public Collection<String> getAlertMessages() {
        return alertMessages;
    }

    /**
     * @return { alertMessages }
     */
    public void setAlertMessages(Collection<String> alertMessages) {
        this.alertMessages = alertMessages;
    }

    /**
     * @param { errorMessages}
     */
    public Collection<String> getErrorMessages() {
        return errorMessages;
    }

    /**
     * @return { errorMessages }
     */
    public void setErrorMessages(Collection<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    /**
     * @param { warningMessages}
     */
    public Collection<String> getWarningMessages() {
        return warningMessages;
    }

    /**
     * @return { warningMessages }
     */
    public void setWarningMessages(Collection<String> warningMessages) {
        this.warningMessages = warningMessages;
    }

    /**
     * @param { responseTime}
     */
    public Date getResponseTime() {
        return responseTime;
    }

    /**
     * @return { responseTime }
     */
    public void setResponseTime(Date responseTime) {
        this.responseTime = responseTime;
    }

    /**
     * @param { paging}
     */
    public Paging getPaging() {
        return paging;
    }

    /**
     * @return { paging }
     */
    public void setPaging(Paging paging) {
        this.paging = paging;
    }

    /**
     * @param { model}
     */
    public T getModel() {
        return model;
    }

    /**
     * @return { model }
     */
    public void setModel(@JsonProperty("model") T model) {
        this.model = model;
    }
}
