package com.mattmcnew.privatessn;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class PrivateSSNSerializer extends JsonSerializer<PrivateSSN>{
    @Override
    public void serialize(PrivateSSN value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        NoReallyIWantToReadThePrivateSSN noReallyIWantToReadThePrivateSSN = new NoReallyIWantToReadThePrivateSSN();

        gen.writeString(noReallyIWantToReadThePrivateSSN.iActuallyWantToReadSSN(value));
    }
}
