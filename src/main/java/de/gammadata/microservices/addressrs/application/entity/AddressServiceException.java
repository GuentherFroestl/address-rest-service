package de.gammadata.microservices.addressrs.application.entity;

/**
 * Exception for usage throughout the service.
 *
 * @author gfr
 */
public class AddressServiceException extends RuntimeException {

  public enum Error {
    VALIDATION(400, "VALIDATION"),
    DATABASE(400, "DATABASE"),
    SYSTEM(500, "SYSTEM");

    private final int httpStatus;
    private final String errorCode;

    private Error(int httpStatus, String errorCode) {
      this.httpStatus = httpStatus;
      this.errorCode = errorCode;
    }

    public int getHttpStatus() {
      return httpStatus;
    }

    public String getErrorCode() {
      return errorCode;
    }
  }

  public AddressServiceException(Error error) {
    this.error = error;
  }

  public AddressServiceException(Error error, String message) {
    super(message);
    this.error = error;
  }

  public AddressServiceException(Error error, String message, Throwable cause) {
    super(message, cause);
    this.error = error;
  }

  public AddressServiceException(Error error, Throwable cause) {
    super(cause);
    this.error = error;
  }

  public AddressServiceException(Error error, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
    this.error = error;
  }

  private Error error;

  public Error getError() {
    return error;
  }

  public void setError(Error error) {
    this.error = error;
  }

}
