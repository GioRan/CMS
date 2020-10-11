package com.cms.content.dto;

import lombok.Data;

@Data
public class BaseDTO {
    private Long	whenAdded;
    private Integer	whoAdded;
    private Long	whenUpdated;
    private Integer	whoUpdated;
}
