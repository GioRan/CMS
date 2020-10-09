package com.cms.authentication.controller.response.builder;


import com.cms.authentication.utilities.Constants;
import lombok.Data;

@Data
public class ResponseBuilder {

    private Integer status;
    private String message;
    private String description;
    private Object data;

    public void success(String description, Object data){
        this.status = Constants.VENDOR_MESSAGE_CODE_SUCCESSFUL;
        this.message = Constants.VENDOR_MESSAGE_SUCCESSFUL;
        this.description = description;
        this.data = data;
    }

    public void falsy(Integer status, String message, String description){
        this.status = status;
        this.message = message;
        this.description = description;
        this.data = null;
    }

    public void badRequest(Object data){
        this.status = Constants.VENDOR_MESSAGE_CODE_FAILED;
        this.message = Constants.VENDOR_MESSAGE_FAILED;
        this.description = Constants.VENDOR_MESSAGE_DESCRIPTION_FAILED;
        this.data = data;
    }

    public void error(){
        this.status = Constants.VENDOR_MESSAGE_CODE_FAILED;
        this.message = Constants.VENDOR_MESSAGE_FAILED;
        this.description = Constants.ERROR_SOMETHING_WENT_WRONG;
        this.data = null;
    }
}
