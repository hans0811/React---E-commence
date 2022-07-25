package com.ecommerce.hans.core.api.controller.support;

import com.ecommerce.hans.core.api.enums.StatusCode;
import com.ecommerce.hans.core.api.service.bean.ValidateResult;
import org.springframework.data.domain.Page;

import java.util.LinkedList;
import java.util.List;

public class ServiceResponseBuilder<E> {

    public static <T> ServiceResponse<T> empty() {
        return new ServiceResponseBuilder<T>()
                .statusCode(StatusCode.SUCCESS)
                .build();
    }

    public static <T> ServiceResponse<List<T>> build(Page<T> model) {
        return new ServiceResponseBuilder<List<T>>()
                .model(model.getContent())
                .paging(model)
                .statusCode(StatusCode.SUCCESS)
                .build();
    }

    public static <T> ServiceResponse<T> build(T model) {
        return new ServiceResponseBuilder<T>()
                .model(model)
                .statusCode(StatusCode.SUCCESS)
                .build();
    }

    public static <T> ServiceResponse<T> buildAlertResult(String alertResult) {
        return new ServiceResponseBuilder<T>()
                .addAlertMessage(alertResult)
                .statusCode(StatusCode.SUCCESS)
                .build();
    }

    public static <T> ServiceResponse<T> buildValidateResult(ValidateResult r) {
        return new ServiceResponseBuilder<T>()
                .validateResult(r)
                .build();
    }

    private ServiceResponse<E> response = new ServiceResponse<>();

    public ServiceResponseBuilder<E> validateResult(ValidateResult r) {
        r.getWarningMessage().stream().forEach(this::addWarnMessage);
        r.getErrorMessage().stream().forEach(this::addErrorMessage);

        return statusCode(StatusCode.RESPONSE_MSG);
    }

    public ServiceResponseBuilder<E> model(E model){
        response.setModel(model);
        return this;
    }

    public ServiceResponseBuilder<E> statusCode(StatusCode statusCode){
        response.setResultCode(statusCode.getCode());
        response.setResultDescription(statusCode.getDesc());
        return this;
    }

    public ServiceResponse<E> build(){
        return response;
    }

    public ServiceResponseBuilder<E> addAlertMessage(String message) {
        if (response.getAlertMessages() == null) {
            response.setAlertMessages(new LinkedList<String>());
        }
        response.getAlertMessages().add(message);
        return this;
    }

    public ServiceResponseBuilder<E> addWarnMessage(String message) {
        if (response.getWarningMessages() == null) {
            response.setWarningMessages(new LinkedList<String>());
        }
        response.getWarningMessages().add(message);
        return this;
    }

    public ServiceResponseBuilder<E> addErrorMessage(String message) {
        if (response.getErrorMessages() == null) {
            response.setErrorMessages(new LinkedList<String>());
        }
        response.getErrorMessages().add(message);
        return this;
    }

    public ServiceResponseBuilder<E> paging(Page<?> page) {
        final Paging paging = new Paging();
        paging.setPageNumber(page.getNumber() + 1);
        paging.setPageSize(page.getSize());
        paging.setTotalCount(page.getTotalElements());
        paging.setTotalPages(page.getTotalPages());
        response.setPaging(paging);
        return this;
    }
}
