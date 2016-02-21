package de.gammadata.microservices.addressrs.common.entity;

/**
 * Base Class for CRUD Query Specifications.
 *
 * @author gfr
 */
public class SimpleQuerySpecification extends BaseQuerySpecification {

  private String query;

  /**
   *
   */
  public SimpleQuerySpecification() {
  }

  /**
   *
   * @param query
   */
  public SimpleQuerySpecification(String query) {
    this.query = query;
  }

  /**
   *
   * @param limit
   * @param start
   */
  public SimpleQuerySpecification(Integer limit, Integer start) {
    this.limit = limit;
    this.start = start;
  }

  /**
   *
   * @param limit
   * @param start
   * @param query
   */
  public SimpleQuerySpecification(Integer limit, Integer start, String query) {
    this.limit = limit;
    this.start = start;
    this.query = query;
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
