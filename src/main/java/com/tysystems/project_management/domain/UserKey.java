package com.tysystems.project_management.domain;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserKey  implements Serializable {
    private String company;
    private String business_unit;
    private String user_date;
    private String user_code;
}
