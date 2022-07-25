package com.ecommerce.hans.core.api.service.bean;

import java.util.LinkedList;
import java.util.List;

public class ValidateResult {

    private static final int WARN = 1;
    private static final int ERROR = 2;

    private List<ValidateMessage> message = new LinkedList<>();
    private List<String> warningMessage = new LinkedList<>();
    private List<String> errorMessage = new LinkedList<>();

    public ValidateResult add(ValidateResult r) {
        r.message.stream().forEach(this::add);
        return this;
    }

    public ValidateResult add(List<ValidateMessage> dataList) {
        dataList.stream().forEach(this::add);
        return this;
    }

    public ValidateResult add(ValidateMessage message) {
        this.message.add(message);
        if (message.level == WARN) {
            this.warningMessage.add(message.message);
        } else {
            this.errorMessage.add(message.message);
        }
        return this;
    }

    public ValidateResult addWarning(String s) {
        this.add(ValidateMessage.warn(s));
        return this;
    }

    public ValidateResult addError(String s) {
        this.add(ValidateMessage.error(s));
        return this;
    }


    public static class ValidateMessage {

        private int level;

        private String message;

        public static ValidateMessage warn(String message) {
            ValidateMessage validateMessage = new ValidateMessage();
            validateMessage.level = WARN;
            validateMessage.message = message;
            return validateMessage;
        }

        public static ValidateMessage error(String message) {
            ValidateMessage validateMessage = new ValidateMessage();
            validateMessage.level = ERROR;
            validateMessage.message = message;
            return validateMessage;
        }

        public String getMessage() {
            return message;
        }

    }

    public List<ValidateMessage> getMessage() {
        return message;
    }

    public List<String> getWarningMessage() {
        return warningMessage;
    }

    public List<String> getErrorMessage() {
        return errorMessage;
    }

}
