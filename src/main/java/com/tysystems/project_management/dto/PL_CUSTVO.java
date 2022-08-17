package com.tysystems.project_management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PL_CUSTVO {

    private String cust_name1;
    private String cust_desc;
    private String cust_name2;
    private String status;
    private String grade;
    private String cust_code;

    private String cust_rename;
    private String cust_reno;
    private String res_no;

    private String business_stat;
    private String business_item;
    private String tel_no;
    private String tax_address;
    private String cust_kind;
    private String nation;

    private String distribution;
    private String cust_eng_name;
    private String fax_no;
    private String address;
    private String eng_address;
    
    private String email_address;
    private String homepage;
    private String attached_file;
    private String cust_explain;
    private String cust_charge;

    private Date create_date;
    private String create_person;

    private Date modify_date;
    private String modify_person;

    private String cust_inner_code;
    private String nation_inner_code;
}
