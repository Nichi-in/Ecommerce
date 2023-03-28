package com.nichi.ecommerce.exceptions;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class ProxyTypeAdapter extends TypeAdapter<Proxy> {
	@Override
	public void write(JsonWriter out, Proxy value) throws IOException {
	    out.beginObject();
	    out.name("type").value(value.type().toString());
	    out.name("host").value(value.address().toString());
	    out.endObject();
	}
	@Override
	public Proxy read(JsonReader in) throws IOException {
	    String type = "";
	    String host = "";
	    in.beginObject();
	    while (in.hasNext()) {
	        String name = in.nextName();
	        if (name.equals("type")) {
	            type = in.nextString();
	        } else if (name.equals("host")) {
	            host = in.nextString();
	        } else {
	            in.skipValue();
	        }
	    }
	    in.endObject();
	    Proxy.Type proxyType = Proxy.Type.valueOf(type.toUpperCase());
	    return new Proxy(proxyType, new InetSocketAddress(host, 0));
	}
}
