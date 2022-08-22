package com.tysystems.project_management.service;

import com.tysystems.project_management.domain.OrgInfo;
import com.tysystems.project_management.repository.OrgInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OrgInfoService {

    @Autowired
    OrgInfoRepository repository;

    public OrgInfo getList(String org_name){
        return repository.findById(org_name).orElse(new OrgInfo());
    }
}
