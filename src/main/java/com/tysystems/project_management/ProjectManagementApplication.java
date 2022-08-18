package com.tysystems.project_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.tysystems.file.FileBase;

@SpringBootApplication
@ComponentScan(basePackageClasses = {ProjectManagementBase.class, FileBase.class})
public class ProjectManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectManagementApplication.class, args);
	}

}
