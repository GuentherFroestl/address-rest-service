package de.gammadata.microservices.addressrs.addresses.entity;

/**
 *
 * @author gfr
 */
public class EntityRelatedQuerySpec extends BaseQuerySpecification{

  /**
   *
   * @param relatedId
   */
  public EntityRelatedQuerySpec(long relatedId) {
    this.relatedId = relatedId;
  }

  /**
   *
   * @param relatedId
   * @param query
   */
  public EntityRelatedQuerySpec(long relatedId, String query) {
    super(query);
    this.relatedId = relatedId;
  }

  /**
   *
   * @param relatedId
   * @param limit
   * @param start
   */
  public EntityRelatedQuerySpec(long relatedId, Integer limit, Integer start) {
    super(limit, start);
    this.relatedId = relatedId;
  }

  /**
   *
   * @param relatedId
   * @param limit
   * @param start
   * @param query
   */
  public EntityRelatedQuerySpec(long relatedId, Integer limit, Integer start, String query) {
    super(limit, start, query);
    this.relatedId = relatedId;
  }
  
  
  
  private long relatedId;

  /**
   *
   * @return
   */
  public long getRelatedId() {
    return relatedId;
  }

  /**
   *
   * @param relatedId
   */
  public void setRelatedId(long relatedId) {
    this.relatedId = relatedId;
  }
  
  
  
}
