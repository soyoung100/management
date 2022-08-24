package com.tysystems.project_management.repository;

import com.tysystems.project_management.domain.MtxUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MtxUserRepository extends JpaRepository<MtxUser, String> {

}
