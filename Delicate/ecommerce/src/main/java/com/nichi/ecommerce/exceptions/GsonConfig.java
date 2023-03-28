package com.nichi.ecommerce.exceptions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.net.Proxy;

public class GsonConfig {
	public static Gson getGson() {
        return new GsonBuilder()
            .registerTypeAdapter(Proxy.class, new ProxyTypeAdapter())
            .create();
    }
    
}
