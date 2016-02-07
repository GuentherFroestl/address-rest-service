package de.gammadata.microservices.addressrs.application.entity;

/**
 * Exception for usage throughout the service.
 *
 * @author gfr
 */
public class AddressServiceException extends RuntimeException {

  private Error error;

  /**
   *
   * @param error
   * @param message
   */
  public AddressServiceException(Error error, String message) {
    super(message);
    this.error = error;
  }

  /**
   *
   * @param error
   * @param message
   * @param cause
   */
  public AddressServiceException(Error error, String message, Throwable cause) {
    super(message, cause);
    this.error = error;
  }

  /**
   *
   * @return
   */
  public Error getError() {
    return error;
  }

  /**
   *
   * @param error
   */
  public void setError(Error error) {
    this.error = error;
  }
  /**
   *
   */
  public enum Error {
    
    /**
     *
     */
    VALIDATION(400, "VALIDATION"),
    
    /**
     *
     */
    DATABASE(400, "DATABASE"),
    
    /**
     *
     */
    SYSTEM(500, "SYSTEM");
    
    private final int httpStatus;
    private final String errorCode;
    
    private Error(int httpStatus, String errorCode) {
      this.httpStatus = httpStatus;
      this.errorCode = errorCode;
    }
    
    /**
     *
     * @return
     */
    public int getHttpStatus() {
      return httpStatus;
    }
    
    /**
     *
     * @return
     */
    public String getErrorCode() {
      return errorCode;
    }
  }

}
