package com.tysystems.project_management.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
public class CompositeKey implements Serializable {
    private String company;
    private String business_unit;
    private String cust_code;
    private String cust_name;
}
