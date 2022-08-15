package com.tysystems.project_management.service;

import com.tysystems.project_management.domain.CompositeKey;
import com.tysystems.project_management.domain.PL_CUST;
import com.tysystems.project_management.repository.PL_CUSTRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PL_CUSTService {

    @Autowired
    PL_CUSTRepository pl_custRepository;

    // 전체조회
    public Page<PL_CUST> getLists(PageRequest pageRequest) {
        return pl_custRepository.findAll(pageRequest);
    }

    public PL_CUST getList(CompositeKey compositeKey) {
        return pl_custRepository.findById(compositeKey).orElseThrow(NoSuchElementException::new);
    }

    public PL_CUST save(PL_CUST pl_cust) {
        return pl_custRepository.save(pl_cust);
    }

    public void delete(CompositeKey compositeKey) {
        pl_custRepository.deleteById(compositeKey);
    }


//    public Page<PL_CUST> getListsLike(PageRequest pageRequest, CompositeKey compositeKey) {
//        return pl_custRepository.findCustsWithPK(compositeKey.getCompany(), compositeKey.getBusiness_unit(), compositeKey.getCust_code(), compositeKey.getCust_name());
//    }

}
