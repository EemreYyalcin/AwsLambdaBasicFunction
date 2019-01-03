package com.app;


import com.bean.ConfigBean;
import com.model.basic.flow.BasicRequest;
import com.model.basic.flow.BasicResponse;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.function.context.FunctionRegistration;
import org.springframework.cloud.function.context.FunctionType;
import org.springframework.cloud.function.context.FunctionalSpringApplication;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.GenericApplicationContext;

import java.util.function.Function;

@SpringBootApplication
@EnableConfigurationProperties(ConfigBean.class)
public class LambdaApp implements ApplicationContextInitializer<GenericApplicationContext> {

    private ConfigBean configBean;

    public LambdaApp(){

    }

    public LambdaApp(ConfigBean configBean){
        this.configBean = configBean;
    }


    @Bean
    public Function<String, String> testDummyFunction() { return e -> e.toUpperCase() + " XX" ;}

    @Bean
    public Function<BasicRequest, BasicResponse> basicLambdaFunction(){
        return basicRequest -> new BasicResponse().setValue(basicRequest.getValue1().toUpperCase() + " " + basicRequest.getValue2().toLowerCase() + " " + configBean.getDummyValue());
    }


    @Override
    public void initialize(GenericApplicationContext context) {
        configBean = new ConfigBean();
        context.registerBean(ConfigBean.class, () -> configBean);
        context.registerBean("function", FunctionRegistration.class, () -> new FunctionRegistration(basicLambdaFunction()).type(FunctionType.from(BasicRequest.class).to(BasicResponse.class)));
    }


    public static void main(String[] args) {
        FunctionalSpringApplication.run(LambdaApp.class, args);
    }






}
