package com.tysystems.project_management.service;

import com.tysystems.project_management.domain.GroupLinkKey;
import com.tysystems.project_management.domain.MtxGroupLink;
import com.tysystems.project_management.repository.MtxGroupLinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MtxGroupLinkService {
    @Autowired
    MtxGroupLinkRepository repository;

    public MtxGroupLink getList(GroupLinkKey key){
        return repository.findById(key).orElse(new MtxGroupLink());
    }

    public MtxGroupLink save(MtxGroupLink mtxGroupLink){
        return repository.save(mtxGroupLink);
    }

    public void delete(String user_code){
        repository.deleteInBulkByUser_code(user_code);
    }
}
