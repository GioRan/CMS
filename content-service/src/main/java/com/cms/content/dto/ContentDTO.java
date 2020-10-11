package com.cms.content.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class ContentDTO extends BaseDTO {

    @JsonIgnore
    private Integer contentId;
    private String contentUuid;
    private List<ElementDTO> elements;
}
