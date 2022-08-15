package com.tysystems.project_management.repository;

import com.tysystems.project_management.domain.CompositeKey;
import com.tysystems.project_management.domain.PL_CUST;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;

@Repository
public interface PL_CUSTRepository extends JpaRepository<PL_CUST, CompositeKey> {

//    @Query(value = "SELECT * FROM PL_CUST WHERE company like '%' || :company || '%' and business_unit like '%' || :business_unit || '%' and cust_code like '%' || :cust_code || '%' and cust_name like '%' || :cust_name || '%'")
//    Page<PL_CUST> findCustsWithPK(@Param("company") String company, @Param("business_unit") String business_unit, @Param("cust_code") String cust_code, @Param("cust_name") String cust_name );
}
