package com.tysystems.project_management.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
public class GroupLinkKey implements Serializable {
    private String user_code;
    private String group_code;
}
