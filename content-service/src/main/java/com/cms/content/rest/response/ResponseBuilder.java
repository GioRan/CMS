package com.cms.content.rest.response;

import lombok.Data;

@Data
public class ResponseBuilder {

    private String message;
    private Object data;

    public void set(String message, Object data){
        this.message = message;
        this.data = data;
    }
}
