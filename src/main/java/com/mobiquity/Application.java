package com.mobiquity;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.mobiquity.exception.APIException;
import com.mobiquity.packer.Packer;
import com.mobiquity.utils.Constants;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws APIException, IOException {
    	ApplicationContext applicationContext = SpringApplication.run(Application.class, args);
    	Packer packer = applicationContext.getBean(Packer.class);
        System.out.println(packer.pack(Constants.EXAMPLE_INPUT_FILE_PATH));        
    }
}
