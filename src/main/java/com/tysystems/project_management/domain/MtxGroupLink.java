package com.tysystems.project_management.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "mtx_group_link")
@IdClass(GroupLinkKey.class)
public class MtxGroupLink {
    @Id
    private String user_code;
    @Id
    private String group_code;
    private Date create_date;
    private Date modify_date;
}
