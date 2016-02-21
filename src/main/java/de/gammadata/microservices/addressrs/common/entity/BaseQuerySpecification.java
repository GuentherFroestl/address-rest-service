package de.gammadata.microservices.addressrs.common.entity;

/**
 *
 * @author gfr
 */
public class BaseQuerySpecification {

  protected Integer limit;
  protected Integer start;
/**
 * constructor.
 */
  public BaseQuerySpecification() {
  }
/**
 * constructor.
 * @param limit Integer
 * @param start Integer
 */
  public BaseQuerySpecification(Integer limit, Integer start) {
    this.limit = limit;
    this.start = start;
  }
/**
 * constructor.
 * @param bqs BaseQuerySpecification
 */
  public BaseQuerySpecification(BaseQuerySpecification bqs) {
    if (bqs != null) {
      this.limit = bqs.getLimit();
      this.start = bqs.getStart();
    }
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

}
