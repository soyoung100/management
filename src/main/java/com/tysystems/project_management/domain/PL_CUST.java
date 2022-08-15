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
@IdClass(CompositeKey.class)
public class PL_CUST {

    @Id
    private String company;

    @Id
    private String business_unit;

    @Id
    private String cust_code;

    @Id
    private String cust_name;

    private String cust_desc;
    private String status;
    private String grade;
    private String reg_num;
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
    @Temporal(TemporalType.DATE)
    private Date create_date;
    @Temporal(TemporalType.DATE)
    private Date modify_date;

}
