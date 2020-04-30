package com.henan;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.henan.service.DataService;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main(String[] args)
    {
        ApplicationContext ctx =
            new ClassPathXmlApplicationContext("classpath:spring-context.xml");
        
        DataService dataService = (DataService)ctx.getBean("dataService");
        dataService.getLicenseData();
        System.out.println("Hello World!");
    }
}
