package com.tysystems.project_management.controller;

import com.tysystems.project_management.domain.PL_USER;
import com.tysystems.project_management.domain.UserKey;
import com.tysystems.project_management.service.PL_USERService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@Controller
@RequiredArgsConstructor
public class SampleController {

    @Autowired
    PL_USERService service;

    @RequestMapping(method = RequestMethod.GET, path = "/compare")
    public void compare(Model model){
        model.addAttribute("users", service.getLists());
    }

    @RequestMapping( value = "/user/delete", method = RequestMethod.GET)
    public String deleteUser(@RequestParam String user_code, @RequestParam String user_date){
        UserKey userKey = new UserKey();
        userKey.setUser_code(user_code);
        userKey.setUser_date(user_date);
        userKey.setCompany("TSS");
        userKey.setBusiness_unit("TSSBU");

        service.delete(userKey);

        return "redirect:/compare";
    }

    @RequestMapping(value = "/user/save", method = RequestMethod.GET)
    public String saveUser(Model model, @RequestParam String user_code, @RequestParam String user_date,
                           @RequestParam String user_name, @RequestParam String org_nm, @RequestParam String user_desc,
                           @RequestParam Date start_date, @RequestParam String join_date, @RequestParam String org_code,
                           @RequestParam String deptid_s, @RequestParam String deptid_s_nm, @RequestParam String deptid_b,
                           @RequestParam String deptid_b_nm
    ) throws ParseException {
        PL_USER pl_user = new PL_USER("TSS", "TSSBU", user_date, user_code, user_name, org_code, org_nm, user_desc, deptid_s, deptid_s_nm, deptid_b, deptid_b_nm, start_date, new SimpleDateFormat("yyyyMMdd").parse("20991231"), new Date(), null, null, null, "Y", join_date);
        service.save(pl_user);

        return "redirect:/compare";
    }

    // 퇴사자 업데이트
    @RequestMapping(value = "/user/update", method = RequestMethod.GET)
    public String updateUser(@RequestParam String user_code, @RequestParam String user_date, @RequestParam String end_date) throws ParseException {
        UserKey userKey = new UserKey();
        userKey.setUser_date(user_date);
        userKey.setUser_code(user_code);
        userKey.setCompany("TSS");
        userKey.setBusiness_unit("TSSBU");

        PL_USER pl_user = service.getList(userKey);
        pl_user.setLive_gb("N");
        pl_user.setEnd_date(new SimpleDateFormat("yyyyMMdd").parse(end_date));

        service.save(pl_user);

        return "redirect:/compare";
    }
}
