package de.gammadata.microservices.addressrs.addresses.entity;

/**
 * Base Class for CRUD Query Specifications.
 *
 * @author gfr
 */
public class BaseQuerySpecification {

  private Integer limit;
  private Integer start;
  private String query;

  public BaseQuerySpecification() {
  }

  public BaseQuerySpecification(String query) {
    this.query = query;
  }

  public BaseQuerySpecification(Integer limit, Integer start) {
    this.limit = limit;
    this.start = start;
  }

  public BaseQuerySpecification(Integer limit, Integer start, String query) {
    this.limit = limit;
    this.start = start;
    this.query = query;
  }

  public Integer getLimit() {
    return limit;
  }

  public void setLimit(Integer limit) {
    this.limit = limit;
  }

  public Integer getStart() {
    return start;
  }

  public void setStart(Integer start) {
    this.start = start;
  }

  public String getQuery() {
    return query;
  }

  public void setQuery(String query) {
    this.query = query;
  }

}
