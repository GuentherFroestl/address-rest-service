package de.gammadata.microservices.addressrs.application.control;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator.Feature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.util.Date;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

/**
 * Create custom ObjectMapper wit Jackson Featurs and some custom serializers.
 */
@Provider
public class MyObjectMapperProvider implements ContextResolver<ObjectMapper> {
  /**
   * Create custom ObjectMapper wit Jackson Featurs and some custom serializers.
   *
   * @return ObjectMapper
   */
  private static ObjectMapper createDefaultMapper() {
    SimpleModule module = new SimpleModule("MyModule");
    module.addSerializer(Date.class, new JacksonZuluDateSerializer());
    final ObjectMapper result = new ObjectMapper();
    result.setSerializationInclusion(Include.NON_NULL)
            .configure(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS, true)
            .configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(Feature.IGNORE_UNKNOWN, true)
            .configure(Feature.WRITE_BIGDECIMAL_AS_PLAIN, true)
            .registerModule(module);
    return result;
  }

  final ObjectMapper defaultObjectMapper;
  SimpleModule module;

  /**
   *
   */
  public MyObjectMapperProvider() {
    defaultObjectMapper = createDefaultMapper();

  }

  /**
   *
   * @param type
   * @return
   */
  @Override
  public ObjectMapper getContext(final Class<?> type) {

    return defaultObjectMapper;

  }


}
