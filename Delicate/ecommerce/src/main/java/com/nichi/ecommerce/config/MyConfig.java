package com.nichi.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nichi.ecommerce.controller.OrderController;
import com.nichi.ecommerce.controller.ProxyTypeAdapter;

@Configuration
public class MyConfig {

    @Bean
    public Gson gson() {
        return new GsonBuilder()
                .registerTypeAdapter(OrderController.class, new ProxyTypeAdapter())
                .create();
    }

}
