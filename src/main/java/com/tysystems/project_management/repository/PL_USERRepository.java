package com.tysystems.project_management.repository;

import com.tysystems.project_management.domain.PL_USER;
import com.tysystems.project_management.domain.UserKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PL_USERRepository extends JpaRepository<PL_USER, UserKey> {
}
