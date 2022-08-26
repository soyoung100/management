package com.tysystems.batch.plcust;

import java.io.File;
import java.time.LocalDate;

import javax.sql.DataSource;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;


import com.tysystems.project_management.dto.PL_CUSTVO;

@Configuration
@Component
public class PLCUSTItems {

    @Autowired
    public DataSource dataSource;

    @Bean
    @StepScope
    public JsonItemReader<PL_CUSTVO> jsonFileReader(){

        String todayDataFilePath = "./filestorage/plcust/plcust_" + LocalDate.now() + ".json";
        FileSystemResource  excelFilePath = new FileSystemResource(new File(todayDataFilePath));
        
        JacksonJsonObjectReader<PL_CUSTVO> jsonObjectReader = new JacksonJsonObjectReader<>(PL_CUSTVO.class);

        return new JsonItemReaderBuilder<PL_CUSTVO>()
                .name("jsonFileReader")
                .resource(excelFilePath)
                .jsonObjectReader(jsonObjectReader)
                .build();
    }

    @Bean
    public PLCUSTItemProcessor processor() {
        return new PLCUSTItemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<PL_CUSTVO> writer() {

        String query = "INSERT INTO pl_cust (" + 
                "company, business_unit, cust_code, cust_name, cust_desc, " + 
                "status, grade, reg_num, cust_rename, cust_reno, " + 
                "res_no, business_stat, business_item, tel_no, tax_address, " + 
                "cust_kind, nation, distribution, cust_eng_name, fax_no, " +
                "address, eng_address, email_address, homepage, attached_file, " + 
                "cust_explain, cust_charge, modify_date)" + 
                "VALUES(" + 
                "'TSS', 'TSSBU', :cust_code, :cust_name1, :cust_desc, " + 
                ":status, :grade, :res_no, :cust_rename, :cust_reno, " + 
                ":res_no, :business_stat, :business_item, :tel_no, :tax_address, " + 
                ":cust_kind, :nation, :distribution, :cust_eng_name, :fax_no, " + 
                ":address, :eng_address, :email_address, :homepage, :attached_file, " + 
                ":cust_explain, :cust_charge, :modify_date) " + 
                "ON CONFLICT ON CONSTRAINT  xpkpl_cust " + 
                "DO NOTHING";

        return new JdbcBatchItemWriterBuilder<PL_CUSTVO>()
            .assertUpdates(false)
            .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
            .sql(query)
            .dataSource(dataSource)
            .build();
    }
}
