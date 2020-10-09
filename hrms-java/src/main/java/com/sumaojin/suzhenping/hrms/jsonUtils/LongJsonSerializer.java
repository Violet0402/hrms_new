package com.sumaojin.suzhenping.hrms.jsonUtils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
//给前端时时将Long转成字符串
public class LongJsonSerializer  extends JsonSerializer<Long> {
    @Override
    public void serialize(Long aLong, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        String text = (aLong == null ? null : String.valueOf(aLong));
        if (text != null) {
            jsonGenerator.writeString(text);
        }
    }
}
