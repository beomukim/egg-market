package com.memo.test;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@Controller
public class TestController {
	  @ResponseBody
	    @RequestMapping("/test1")
	    public String helloWorld() {
	        return "Hello world!";
	    }
}
