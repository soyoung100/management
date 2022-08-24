package com.tysystems.project_management.controller;

import com.tysystems.project_management.domain.MtxUser;
import com.tysystems.project_management.domain.OrgInfo;
import com.tysystems.project_management.domain.PL_USER;
import com.tysystems.project_management.domain.UserKey;
import com.tysystems.project_management.service.MtxGroupLinkService;
import com.tysystems.project_management.service.MtxUserService;
import com.tysystems.project_management.service.OrgInfoService;
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

@Controller
@RequiredArgsConstructor
public class SampleController {

    @Autowired
    PL_USERService service;
    @Autowired
    OrgInfoService orgInfoService;
    @Autowired
    MtxUserService mtxUserService;
    @Autowired
    MtxGroupLinkService mtxGroupLinkService;

    @RequestMapping(method = RequestMethod.GET, path = "/compare")
    public void compare(Model model){
        model.addAttribute("users", service.getLists());
    }

    /**
     * 재직자는 추가해주고 퇴사자는 end_date와 live_gb 컬럼을 업데이트 해주는 컨트롤러
     * @param user_codes 사번 리스트
     * @param user_names 사원명 리스트
     * @param org_nms 부서명 리스트
     * @param start_dates 입사일 리스트
     * @param user_dates 입력 날짜 리스트
     * @param end_dates 퇴사 날짜 리스트
     * @param user_descs 직책 리스트
     * @return 다시 파일을 선택할 수 있는 화면으로 redirect
     * @throws ParseException
     */
    @RequestMapping(value = "/user/update", method = RequestMethod.POST)
    public String updateUser(@RequestParam(value = "user_code") String[] user_codes,
                             @RequestParam(value = "user_name") String[] user_names,
                             @RequestParam(value = "org_nm") String[] org_nms,
                             @RequestParam(value = "start_date") String[] start_dates,
                             @RequestParam(value = "user_date") String[] user_dates,
                             @RequestParam(value = "end_date") String[] end_dates,
                             @RequestParam(value = "user_desc") String[] user_descs) throws ParseException {

        for (int i = 1; i < user_codes.length; i++){
            if (user_names[i].isEmpty()){ // 퇴사자일경우

                System.out.println("=====[end user info]=====");
                System.out.println("user_code: " + user_codes[i]);
                System.out.println("user_date: " + user_dates[i]);
                System.out.println("end_date: " + end_dates[i]);
                System.out.println("=====================");

                // pl_user update: 퇴사자일경우 live_gb를 'N'로 변경, end_date를 퇴사일로 변경
                UserKey userKey = new UserKey();
                userKey.setUser_date(user_dates[i]);
                userKey.setUser_code(user_codes[i]);
                userKey.setCompany("TSS");
                userKey.setBusiness_unit("TSSBU");

                PL_USER pl_user = service.getList(userKey);
                System.out.println(pl_user.getUser_name());

                // 존재하지 않는 사람이면 에러나니까 예외처리
                if (pl_user.getUser_name() == null){
                    System.out.println("no exist user");
                    continue;
                }
                pl_user.setLive_gb("N");
                pl_user.setEnd_date(new SimpleDateFormat("yyyy-MM-dd").parse(end_dates[i]));

                service.save(pl_user);

                System.out.println("pl_user table update");

                // mtx_user update: 퇴사자일경우 lock_flag를 'Y'로 변경
                MtxUser mtxUser = mtxUserService.getList(user_codes[i]);
                mtxUser.setLock_flag("Y");

                mtxUserService.update(mtxUser);

                System.out.println("mtx_user table update");

                // mtx_group_link delete: 퇴사자일경우 권한관련 행들을 삭제
                mtxGroupLinkService.delete(user_codes[i]);

                System.out.println("mtx_group_link table delete");
            } else { // 입사자일경우
                System.out.println("*****[start user info]*****");
                System.out.println("user_code: " + user_codes[i]);
                System.out.println("org_name: " + org_nms[i]);

                PL_USER pl_user = new PL_USER();
                OrgInfo orgInfo = orgInfoService.getList(org_nms[i]);

                System.out.println("org_code: " + orgInfo.getOrg_code());
                System.out.println("**********************");


                pl_user.setUser_date(start_dates[i].replace("-", ""));
                pl_user.setUser_code(user_codes[i]);
                pl_user.setUser_name(user_names[i]);
                pl_user.setOrg_nm(org_nms[i]);
                if (!user_descs[i].equals(" "))
                    pl_user.setUser_desc(user_descs[i]);
                pl_user.setStart_date(new SimpleDateFormat("yyyy-MM-dd").parse(start_dates[i]));
                pl_user.setEnd_date(new SimpleDateFormat("yyyy-MM-dd").parse(end_dates[i]));
                pl_user.setJoin_date(start_dates[i].replace("-", ""));
                pl_user.setCompany("TSS");
                pl_user.setBusiness_unit("TSSBU");
                pl_user.setLive_gb("Y");
                pl_user.setOrg_code(orgInfo.getOrg_code());
                pl_user.setDeptid_s(orgInfo.getParent_org_code());
                pl_user.setDeptid_s_nm(orgInfo.getParent_org_name());
                pl_user.setDeptid_b(orgInfo.getRoot_org_code());
                pl_user.setDeptid_b_nm(orgInfo.getRoot_org_name());
                service.save(pl_user);
            }
        }
        return "redirect:/compare";
    }
}
