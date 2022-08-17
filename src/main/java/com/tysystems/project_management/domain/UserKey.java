package com.tysystems.project_management.domain;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
public class UserKey implements Serializable {
    private String company;
    private String business_unit;
    private String user_date;
    private String user_code;
}
