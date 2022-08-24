package com.tysystems.project_management.service;

import com.tysystems.project_management.domain.MtxUser;
import com.tysystems.project_management.repository.MtxUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MtxUserService {
    @Autowired
    MtxUserRepository repository;

    public MtxUser getList(String user_code){
        return repository.findById(user_code).orElse(new MtxUser());
    }

    public MtxUser update(MtxUser mtxUser){
        return repository.save(mtxUser);
    }
}
