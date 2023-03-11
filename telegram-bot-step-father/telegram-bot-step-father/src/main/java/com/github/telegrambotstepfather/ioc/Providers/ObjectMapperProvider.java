package com.github.telegrambotstepfather.ioc.Providers;

import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Provider;

public class ObjectMapperProvider implements Provider<ObjectMapper> 
{
    @Override
    public ObjectMapper get() 
    {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new Jdk8Module());
        return objectMapper;
    }
}
