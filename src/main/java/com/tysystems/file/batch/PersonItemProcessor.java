package com.tysystems.file.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.tysystems.project_management.dto.PL_CUSTVO;

@Component
public class PersonItemProcessor implements ItemProcessor<PL_CUSTVO, PL_CUSTVO> {

  private static final Logger log = LoggerFactory.getLogger(PersonItemProcessor.class);

  @Override
  public PL_CUSTVO process(PL_CUSTVO pL_CUSTVO) throws Exception {

    String custCode = pL_CUSTVO.getCust_code();
    String newCustCode = custCode.replaceAll("-", "");

    pL_CUSTVO.setCust_code(newCustCode);

    log.info("Converting from (" + custCode + ") to (" + newCustCode + ")");

    return pL_CUSTVO;
  }

}