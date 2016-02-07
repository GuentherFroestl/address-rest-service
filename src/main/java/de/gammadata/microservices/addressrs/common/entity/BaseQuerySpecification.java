package de.gammadata.microservices.addressrs.common.entity;

/**
 * Base Class for CRUD Query Specifications.
 *
 * @author gfr
 */
public class BaseQuerySpecification {

  private Integer limit;
  private Integer start;
  private String query;

  /**
   *
   */
  public BaseQuerySpecification() {
  }

  /**
   *
   * @param query
   */
  public BaseQuerySpecification(String query) {
    this.query = query;
  }

  /**
   *
   * @param limit
   * @param start
   */
  public BaseQuerySpecification(Integer limit, Integer start) {
    this.limit = limit;
    this.start = start;
  }

  /**
   *
   * @param limit
   * @param start
   * @param query
   */
  public BaseQuerySpecification(Integer limit, Integer start, String query) {
    this.limit = limit;
    this.start = start;
    this.query = query;
  }

  /**
   *
   * @return
   */
  public Integer getLimit() {
    return limit;
  }

  /**
   *
   * @param limit
   */
  public void setLimit(Integer limit) {
    this.limit = limit;
  }

  /**
   *
   * @return
   */
  public Integer getStart() {
    return start;
  }

  /**
   *
   * @param start
   */
  public void setStart(Integer start) {
    this.start = start;
  }

  /**
   *
   * @return
   */
  public String getQuery() {
    return query;
  }

  /**
   *
   * @param query
   */
  public void setQuery(String query) {
    this.query = query;
  }

}
