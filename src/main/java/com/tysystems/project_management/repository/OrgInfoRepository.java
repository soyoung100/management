package com.tysystems.project_management.repository;

import com.tysystems.project_management.domain.OrgInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrgInfoRepository extends JpaRepository<OrgInfo, String> {
}
