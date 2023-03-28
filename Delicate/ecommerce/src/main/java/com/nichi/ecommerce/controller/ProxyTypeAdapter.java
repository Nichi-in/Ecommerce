package com.nichi.ecommerce.controller;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

public class ProxyTypeAdapter extends TypeAdapter<Proxy> {

    @Override
    public void write(JsonWriter out, Proxy value) throws IOException {
        if (value == null) {
            out.nullValue();
            return;
        }
        out.beginObject();
        out.name("type").value(value.type().name());
        out.name("address").value(value.address().toString());
        out.endObject();
    }

    @Override
    public Proxy read(JsonReader in) throws IOException {
        if (in.peek() == JsonToken.NULL) {
            in.nextNull();
            return null;
        }
        in.beginObject();
        String type = null;
        InetSocketAddress address = null;
        while (in.hasNext()) {
            String name = in.nextName();
            if (name.equals("type")) {
                type = in.nextString();
            } else if (name.equals("address")) {
                String addressStr = in.nextString();
                address = new InetSocketAddress(addressStr.substring(1), Integer.parseInt(addressStr.substring(addressStr.indexOf(':') + 1)));
            } else {
                in.skipValue();
            }
        }
        in.endObject();
        if (type == null || address == null) {
            throw new IOException("Missing required fields in Proxy JSON");
        }
        return new Proxy(Type.valueOf(type), address);
    }
}