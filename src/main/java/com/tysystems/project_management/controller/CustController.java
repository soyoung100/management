package com.tysystems.project_management.controller;

import com.tysystems.project_management.domain.PL_CUST;
import com.tysystems.project_management.repository.PL_CUSTRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
public class CustController {

    private final PL_CUSTRepository pl_custRepository;

    public CustController(PL_CUSTRepository pl_custRepository) {
        this.pl_custRepository = pl_custRepository;
    }

    @GetMapping("/restcust")
    public Page<PL_CUST> getAllCusts(@RequestParam("page") Integer page, @RequestParam("size") Integer size){
        PageRequest pageRequest = PageRequest.of(page, size);
        return pl_custRepository.findAll(pageRequest);
    }

}
