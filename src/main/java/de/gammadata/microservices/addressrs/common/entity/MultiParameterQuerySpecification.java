package de.gammadata.microservices.addressrs.common.entity;

import java.util.Map;

/**
 * Multi parameter query spec.
 *
 * @author gfr
 */
public class MultiParameterQuerySpecification extends BaseQuerySpecification {

  /**
   * Map with parameters fo the query.
   */
  private Map<String, Object> parameterMap;

  public MultiParameterQuerySpecification(BaseQuerySpecification bqs) {
    super(bqs);
  }

  public MultiParameterQuerySpecification(Map<String, Object> parameterMap, BaseQuerySpecification bqs) {
    super(bqs);
    this.parameterMap = parameterMap;
  }

  public MultiParameterQuerySpecification(Map<String, Object> parameterMap, Integer limit, Integer start) {
    super(limit, start);
    this.parameterMap = parameterMap;
  }

  public MultiParameterQuerySpecification(Map<String, Object> parameterMap) {
    this.parameterMap = parameterMap;
  }

  public MultiParameterQuerySpecification() {
  }

  public Map<String, Object> getParameterMap() {
    return parameterMap;
  }

  public void setParameterMap(Map<String, Object> parameterMap) {
    this.parameterMap = parameterMap;
  }

}
