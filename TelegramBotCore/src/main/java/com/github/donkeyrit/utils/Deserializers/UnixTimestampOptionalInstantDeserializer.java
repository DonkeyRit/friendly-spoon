package com.github.donkeyrit.utils.Deserializers;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.core.JsonParser;

import java.time.ZoneOffset;
import java.time.Instant;
import java.io.IOException;
import java.util.Optional;

public class UnixTimestampOptionalInstantDeserializer extends JsonDeserializer<Optional<Instant>> 
{

  @Override
  public Optional<Instant> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException 
  {
    JsonNode node = p.getCodec().readTree(p);
    return node.isNull()
        ? Optional.empty()
        : Optional
            .of(Instant.ofEpochSecond(node.longValue())
            .atZone(ZoneOffset.UTC).toInstant());
  }
}

