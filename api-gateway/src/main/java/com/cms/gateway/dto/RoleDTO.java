package com.cms.gateway.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
public class RoleDTO {


    private Integer roleId;
    private String name;
    private List<PrivilegeDTO> privileges;

    @JsonIgnore
    private Integer userId;
}
