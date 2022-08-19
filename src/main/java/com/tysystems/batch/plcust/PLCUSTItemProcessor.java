package com.tysystems.batch.plcust;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.tysystems.project_management.dto.PL_CUSTVO;

@Component
public class PLCUSTItemProcessor implements ItemProcessor<PL_CUSTVO, PL_CUSTVO> {

    @Override
    public PL_CUSTVO process(PL_CUSTVO pL_CUSTVO) throws Exception {

        String custCode = pL_CUSTVO.getCust_code();
        String newCustCode = custCode.replaceAll("-", "");
        pL_CUSTVO.setCust_code(newCustCode);

        return pL_CUSTVO;
    }

}