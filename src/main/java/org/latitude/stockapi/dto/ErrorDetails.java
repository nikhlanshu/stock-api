package org.latitude.stockapi.dto;

public class ErrorDetails {
    private String errorCode;
    private String reason;
    public ErrorDetails(String errorCode, String reason) {
        super();
        this.errorCode = errorCode;
        this.reason = reason;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
