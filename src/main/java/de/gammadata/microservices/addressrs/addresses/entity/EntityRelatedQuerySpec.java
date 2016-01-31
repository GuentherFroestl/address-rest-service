package de.gammadata.microservices.addressrs.addresses.entity;

/**
 *
 * @author gfr
 */
public class EntityRelatedQuerySpec extends BaseQuerySpecification {
  private long relatedId;

  /**
   *
   * @param relatedId long related Foreign Key
   */
  public EntityRelatedQuerySpec(long relatedId) {
    this.relatedId = relatedId;
  }

  /**
   *
   * @param relatedId long related Foreign Key
   * @param query String
   */
  public EntityRelatedQuerySpec(long relatedId, String query) {
    super(query);
    this.relatedId = relatedId;
  }

  /**
   *
   * @param relatedId long related Foreign Key
   * @param limit Integer
   * @param start Integer
   */
  public EntityRelatedQuerySpec(long relatedId, Integer limit, Integer start) {
    super(limit, start);
    this.relatedId = relatedId;
  }

  /**
   *
   * @param relatedId long related Foreign Key
   * @param limit Integer
   * @param start Integer
   * @param query String
   */
  public EntityRelatedQuerySpec(long relatedId, Integer limit, Integer start, String query) {
    super(limit, start, query);
    this.relatedId = relatedId;
  }


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
