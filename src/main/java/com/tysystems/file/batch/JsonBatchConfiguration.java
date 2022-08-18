package com.tysystems.file.batch;

import java.time.LocalDate;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.tysystems.project_management.dto.PL_CUSTVO;

//import lombok.extern.slf4j.Slf4j;

//@Slf4j
@Configuration
@EnableBatchProcessing
public class JsonBatchConfiguration  {
    
    @Autowired
    public JobBuilderFactory jobBuilderFactory;
    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
        return jobBuilderFactory.get("importUserJob")
            .incrementer(new RunIdIncrementer())
            .listener(listener)
            .flow(step1)
            .end()
            .build();
    }
    @Bean
    public Step step1(JsonItemReader<PL_CUSTVO> reader, JdbcBatchItemWriter<PL_CUSTVO> writer) {
        return stepBuilderFactory.get("step1")
            .<PL_CUSTVO, PL_CUSTVO> chunk(10)
            .reader(reader)
            .processor(processor())
            .writer(writer)
            .build();
    }

    @Bean
    @StepScope
    public JsonItemReader<PL_CUSTVO> jsonFileReader() {

        String todayDataFilePath = "./filestorage/plcust_" + LocalDate.now() + ".json";

        JacksonJsonObjectReader<PL_CUSTVO> jsonObjectReader = new JacksonJsonObjectReader<>(PL_CUSTVO.class);

        return new JsonItemReaderBuilder<PL_CUSTVO>()
                .name("jsonFileReader")
                .resource(new ClassPathResource(todayDataFilePath))
                .jsonObjectReader(jsonObjectReader)
                .build();
    }

    @Bean
    public PersonItemProcessor processor() {
        return new PersonItemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<PL_CUSTVO> writer(DataSource dataSource) {

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
            .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
            .sql(query)
            .assertUpdates(false)
            .dataSource(dataSource)
            .build();
    }

}
