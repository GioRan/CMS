package com.cms.content.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class ElementDTO extends BaseDTO{

    @JsonIgnore
    private String elementId;
    private String elementUuid;
    private String type;
    private String value;
    private String className;
    private String idName;
    private String x;
    private String y;

    @JsonIgnore
    private Integer contentId;
}
