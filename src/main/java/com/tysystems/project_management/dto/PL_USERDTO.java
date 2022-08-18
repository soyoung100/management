package com.tysystems.project_management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Data
public class PL_USERDTO {

    private String company;
    private String business_unit;
    private String user_date;
    private String user_code;
    private String user_name;
    private String org_code;
    private String org_nm;
    private String user_desc;
    private String deptid_s;
    private String deptid_s_nm;
    private String deptid_b;
    private String deptid_b_nm;
    private Date start_date;
    private Date end_date;
    private Date create_date;
    private Date modify_date;
    private String esc_grade;
    private String user_grade;
    private String live_gb;
    private String join_date;

    private List<PL_USERDTO> pl_userdtos;
}
