package com.tysystems.project_management.repository;

import com.tysystems.project_management.domain.GroupLinkKey;
import com.tysystems.project_management.domain.MtxGroupLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface MtxGroupLinkRepository extends JpaRepository<MtxGroupLink, GroupLinkKey> {

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM mtx_group_link m WHERE m.user_code = :user_code", nativeQuery = true)
    void deleteInBulkByUser_code(@Param("user_code") String user_code);
}
