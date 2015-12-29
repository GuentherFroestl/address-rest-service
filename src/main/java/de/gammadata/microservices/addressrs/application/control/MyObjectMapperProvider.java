package de.gammadata.microservices.addressrs.application.control;

import com.fasterxml.jackson.core.JsonGenerator.Feature;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.util.Date;

/**
 * Create custom ObjectMapper wit Jackson Featurs and some custom serializers.
 */
@Provider
public class MyObjectMapperProvider implements ContextResolver<ObjectMapper> {

  final ObjectMapper defaultObjectMapper;
//  final ObjectMapper combinedObjectMapper;
  SimpleModule module;

  public MyObjectMapperProvider() {
    defaultObjectMapper = createDefaultMapper();
//    combinedObjectMapper = createCombinedObjectMapper();

  }

  @Override
  public ObjectMapper getContext(final Class<?> type) {

    return defaultObjectMapper;

  }

  /**
   * Create custom ObjectMapper wit Jackson Featurs and some custom serializers.
   *
   * @return ObjectMapper
   */
  private static ObjectMapper createDefaultMapper() {
    SimpleModule module = new SimpleModule("MyModule");
    module.addSerializer(Date.class, new JacksonZuluDateSerializer());
    final ObjectMapper result = new ObjectMapper();
    result.configure(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS, true)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(Feature.IGNORE_UNKNOWN, true)
            .configure(Feature.WRITE_BIGDECIMAL_AS_PLAIN, true)
            .registerModule(module);
    return result;
  }

  /**
   * Create custom ObjectMapper wit Jackson Featurs and some custom serializers.
   *
   * @return ObjectMapper
   */
//  private static ObjectMapper createCombinedObjectMapper() {
//    SimpleModule module = new SimpleModule("MyModule");
//    module.addSerializer(Date.class, new JacksonZuluDateSerializer());
//    return new ObjectMapper()
//            .configure(SerializationFeature.WRAP_ROOT_VALUE, true)
//            .configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true)
//            .setAnnotationIntrospector(createJaxbJacksonAnnotationIntrospector())
//            .registerModule(module);
//  }
//  private static AnnotationIntrospector createJaxbJacksonAnnotationIntrospector() {
//
//    final AnnotationIntrospector jaxbIntrospector = new JaxbAnnotationIntrospector(TypeFactory.defaultInstance());
//    final AnnotationIntrospector jacksonIntrospector = new JacksonAnnotationIntrospector();
//
//    return AnnotationIntrospector.pair(jacksonIntrospector, jaxbIntrospector);
//  }
}
