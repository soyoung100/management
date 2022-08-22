package com.tysystems.project_management.service;

import com.tysystems.project_management.domain.PL_USER;
import com.tysystems.project_management.domain.UserKey;
import com.tysystems.project_management.repository.PL_USERRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PL_USERService {

    @Autowired
    PL_USERRepository repository;

    // 전체조회
    public List<PL_USER> getLists(){
        return  repository.findAll();
    }

    // Key값으로 해당 유저 조회
    public PL_USER getList(UserKey userKey){
        return repository.findById(userKey).orElse(new PL_USER());
    }

    // update, insert
    public PL_USER save(PL_USER pl_user){
        return repository.save(pl_user);
    }

    // delete by key
    public void delete(UserKey userKey){
        repository.deleteById(userKey);
    }

}
