package com.tysystems.file.plcust;

import java.util.List;

import com.tysystems.project_management.dto.PL_CUSTVO;

public class PLCUSTDataFormValidator {
    
    public Integer validateForm(List<PL_CUSTVO> list) {

        // 비어있는 파일일 경우
        if (list.size() == 0) return 1;

        // key값에 null이 들어있는 경우
        PL_CUSTVO firstOne = list.get(0);
        if (firstOne.getCust_name1() == null || firstOne.getCust_code() == null)
            return 2;

        // 정상 파일인 경우
        return 0;
    }
}
