package com.tysystems.project_management.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@IdClass(UserKey.class)
public class PL_USER {

    @Id
    private String company;

    @Id
    private String business_unit;

    @Id
    private String user_date;

    @Id
    private String user_code;

    private String user_name;

    private String org_code;

    private String org_nm;

    private String user_desc;

    private String deptid_s;

    private String deptid_s_nm;

    private String deptid_b;

    private String deptid_b_nm;

    @Temporal(TemporalType.DATE)
    private Date start_date;

    @Temporal(TemporalType.DATE)
    private Date end_date;

    @Temporal(TemporalType.DATE)
    private Date create_date;

    @Temporal(TemporalType.DATE)
    private Date modify_date;

    private String esc_grade;

    private String user_grade;

    private String live_gb;

    private String join_date;
}
