package com.tysystems.project_management.domain;

import groovy.transform.Immutable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Immutable
@Subselect("select t.org_code, t.org_name,\n" +
        "t.parent_org_code, (select org_name from pl_org where org_code = t.parent_org_code and live_gb = 'Y') as parent_org_name,\n" +
        "(select parent_org_code from pl_org where org_code = t.parent_org_code and live_gb = 'Y') as root_org_code,\n" +
        "(select org_name from pl_org where org_code = (select parent_org_code from pl_org where org_code = t.parent_org_code and live_gb = 'Y') and live_gb = 'Y') as root_org_name\n" +
        "from pl_org t\n" +
        "where live_gb = 'Y'")
@Getter
public class OrgInfo {

    private String org_code;
    @Id
    private String org_name;

    private String parent_org_code;

    private String parent_org_name;

    private String root_org_code;

    private String root_org_name;
}
