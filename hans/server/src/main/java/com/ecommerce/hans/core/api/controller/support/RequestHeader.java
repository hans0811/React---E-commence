package com.ecommerce.hans.core.api.controller.support;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class RequestHeader {

    private String msgNo;

    private String txnCode;

    @JsonFormat(pattern="yyyy-MM-DD HH:mm:ss")
    private Date txnTime;

    /** Front end **/
    private String senderCode;
    /** Back end **/
    private String operatorCode;

    private String unitCode;

    private String authorizerCode;

    /**
     * @param { msgNo}
     */
    public String getMsgNo() {
        return msgNo;
    }

    /**
     * @return { msgNo }
     */
    public void setMsgNo(String msgNo) {
        this.msgNo = msgNo;
    }

    /**
     * @param { txnCode}
     */
    public String getTxnCode() {
        return txnCode;
    }

    /**
     * @return { txnCode }
     */
    public void setTxnCode(String txnCode) {
        this.txnCode = txnCode;
    }

    /**
     * @param { txnTime}
     */
    public Date getTxnTime() {
        return txnTime;
    }

    /**
     * @return { txnTime }
     */
    public void setTxnTime(Date txnTime) {
        this.txnTime = txnTime;
    }

    /**
     * @param { senderCode}
     */
    public String getSenderCode() {
        return senderCode;
    }

    /**
     * @return { senderCode }
     */
    public void setSenderCode(String senderCode) {
        this.senderCode = senderCode;
    }

    /**
     * @param { operatorCode}
     */
    public String getOperatorCode() {
        return operatorCode;
    }

    /**
     * @return { operatorCode }
     */
    public void setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode;
    }

    /**
     * @param { unitCode}
     */
    public String getUnitCode() {
        return unitCode;
    }

    /**
     * @return { unitCode }
     */
    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    /**
     * @param { authorizerCode}
     */
    public String getAuthorizerCode() {
        return authorizerCode;
    }

    /**
     * @return { authorizerCode }
     */
    public void setAuthorizerCode(String authorizerCode) {
        this.authorizerCode = authorizerCode;
    }
}
