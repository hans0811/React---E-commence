package com.ecommerce.hans.core.api.enums;

public enum StatusCode {
    SUCCESS("0000", "SUCCESS"),
    RESPONSE_MSG("9001", "RESPONSE"), // 有確認取消action
    RESPONSE_MSG_ALERT("9002", "RESPONSE"), // 只需彈出警示訊息

    CASE_CANCEL_EXCEPTION("R001", "案件已申請取消"),
    CASE_CHECK_FAIL_EXCEPTION("R002", "案件無法傳送"),
    CASE_NOT_AUTH_EXCEPTION("R003", "您非目前處理人員，不可執行案件"),

    USER_NOT_FOUND("E001", "使用者不存在"),
    USER_ALREADY_EXIST("E002", "使用者已經存在"),
    USER_PASSWORD_ERROR("E003", "使用者或密碼不正確"),
    USER_EXIST_CASE("E004", "使用者尚有在途案件未移轉，無法更新人員資訊"),
    USER_NOT_IN_SAME_GROUP("E005", "代理人部門角色與請假人不同"),
    USER_PASSWORD_APPEARED_RECENTLY_ERROR("E006", "使用者密碼與過去五次相同"),

    B204_CANNOT_MATCH("E010", "聯徵報送寫入錯誤，請洽系統管理員"),

    BPM_NO_ASSIGNEE("E986", "案件無可分派人員；請洽系統管理員"),
    BPM_APPROVER_RULE("E987", "案件核准人員不得與建立人員為同一位"),
    BPM_NO_ASSIGN_DEVIATION("E988", "系統目前查無符合案件核決權限之人員，無法進行系統分派；請洽系統管理員"),
    BPM_NEXT_MAX_LEVEL("E989", "案件已達最大層級, 無法送下一關"),
    BPM_INVALID_STATUS_TASK("E990", "案件流程執行非有效狀態"),
    BPM_NO_CASE_TYPE_TASK("E991", "無此案件類型"),
    BPM_NO_BACK_TASK("E992", "已是原經辦，無法退回"),
    BPM_NO_NEXT_TASK("E993", "案件無下一步功能"),
    BPM_NO_CANCEL_TASK("E994", "案件無取消功能"),
    BPM_NO_APPEAL_TASK("E995", "案件無申覆功能"),
    BPM_NO_RESUBMIT_TASK("E996", "案件無補件功能"),
    BPM_NO_RECALL_TASK("E997", "案件無返回原人員功能"),
    BPM_NO_CURRENT_TASK("E998", "無此案待辦事項"),
    BPM_EXCEPTION("E999", "BPM錯誤"),

    SYSTEM_ERROR("9999", "SYSTEM ERROR")
    ;

    /** 代碼 */
    private String code;
    /** 說明 */
    private String desc;


    /**
     * 建構子
     * @param code
     * @param desc
     */
    private StatusCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 取得 code
     *
     * @return 傳回 code。
     */
    public String getCode() {
        return code;
    }

    /**
     * 取得 desc
     *
     * @return 傳回 desc。
     */
    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return code + ":" + desc;
    }

}
