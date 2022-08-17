package com.tysystems.project_management.controller;

import com.tysystems.project_management.domain.PL_USER;
import com.tysystems.project_management.domain.UserKey;
import com.tysystems.project_management.service.PL_USERService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class PL_USERController {

    @Autowired
    PL_USERService service;

    @GetMapping
    public List<PL_USER> getLists(){
        return service.getLists();
    }

    @PostMapping
    public PL_USER getList(UserKey userKey){
        return service.getList(userKey);
    }
}
