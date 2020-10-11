package com.cms.gateway.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
public class PrivilegeDTO {


    private Integer privilegeId;
    private String name;

    @JsonIgnore
    private Integer roleId;
}
