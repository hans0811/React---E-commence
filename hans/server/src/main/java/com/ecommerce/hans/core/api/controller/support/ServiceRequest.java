package com.ecommerce.hans.core.api.controller.support;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ServiceRequest<T> {

    private RequestHeader header;

    private Paging paging;

    private Map<String, Object> requestModel;

    private T model;

    public ServiceRequest() {

    }

    private org.springframework.data.domain.Sort getSort(){
        return Optional.ofNullable(requestModel)
                .filter(r -> r.get("sort") != null && r.get("sort") instanceof List)
                .map( r -> (List<Map<String, String>>) r.get("sort"))
                .map(rm -> rm.stream()
                        .map(s -> "DESC".equals(s.get("direction")) ? Sort.Order.desc(s.get("property")) : Sort.Order.asc(s.get("property")))
                        .collect(Collectors.toList()))
                .map(Sort::by)
                .orElse(Sort.unsorted());
    }


    public Pageable getPageble(){
        return Optional.ofNullable(paging)
                .map(p -> (Pageable) PageRequest.of(p.getPageNumber(), p.getPageSize(), getSort()))
                .orElseGet(Pageable::unpaged);
    }

    @JsonCreator
    public ServiceRequest(@JsonProperty("model") T model){
        this.model = model;
    }

    public T getModel() {
        return this.model;
    }

    public void setModel(@JsonProperty("model") T model){
        this.model = model;
    }

    /**
     * @return the header
     */
    public RequestHeader getHeader() {
        return header;
    }

    /**
     * @param header the header to set
     */
    public void setHeader(RequestHeader header) {
        this.header = header;
    }


    /**
     * @return the requestModel
     */
    public Map<String, Object> getRequestModel() {
        return requestModel;
    }

    /**
     * @param requestModel the requestModel to set
     */
    public void setRequestModel(Map<String, Object> requestModel) {
        this.requestModel = requestModel;
    }

    /**
     * @return the page
     */
    public Paging getPaging() {
        return paging;
    }

    /**
     * @param page the page to set
     */
    public void setPaging(Paging paging) {
        this.paging = paging;
    }

}
