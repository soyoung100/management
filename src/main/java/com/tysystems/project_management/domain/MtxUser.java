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
@Table(name = "mtx_user")
public class MtxUser {
    @Id
    private String user_code;
    //private String user_name;
    //private String user_pwd;
    //private int user_role_no;
    //private String org_code;
    //private int pwd_failcnt;
    private String lock_flag;
    //private String user_desc;
    //@Temporal(TemporalType.DATE)
    //private Date start_date;
    //@Temporal(TemporalType.DATE)
    //private Date end_date;
    //private String attribute1;
    //private String attribute2;
    //private String attribute3;
    //private String attribute4;
    //private String attribute5;
    //private int user_seq;
    //@Temporal(TemporalType.DATE)
    //private Date create_date;
    //@Temporal(TemporalType.DATE)
    //private Date modify_date;
}
