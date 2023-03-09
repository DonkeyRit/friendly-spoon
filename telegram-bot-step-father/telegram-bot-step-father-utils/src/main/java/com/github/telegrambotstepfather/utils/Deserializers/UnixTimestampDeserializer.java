package com.github.telegrambotstepfather.utils.Deserializers;

import java.time.ZoneOffset;
import java.time.Instant;
import java.io.IOException;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.core.JsonParser;

public class UnixTimestampDeserializer extends JsonDeserializer<Instant> 
{
    @Override
    public Instant deserialize(JsonParser p, DeserializationContext ctxt) throws IOException 
    {
        int unixTimestamp = p.getValueAsInt();
        if(unixTimestamp == 0)
        {
            return Instant.MAX;
        }
        return Instant.ofEpochSecond(unixTimestamp).atZone(ZoneOffset.UTC).toInstant();
    }
}