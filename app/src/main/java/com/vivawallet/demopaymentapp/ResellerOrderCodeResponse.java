
package com.vivawallet.demopaymentapp;

import com.google.gson.annotations.SerializedName;

public class ResellerOrderCodeResponse {

    @SerializedName("CorrelationId")
    private String correlationId;
    @SerializedName("ErrorCode")
    private Long errorCode;
    @SerializedName("ErrorText")
    private String errorText;
    @SerializedName("EventId")
    private Long eventId;
    @SerializedName("OrderCode")
    private Long orderCode;
    @SerializedName("Success")
    private Boolean success;
    @SerializedName("TimeStamp")
    private String timeStamp;

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public Long getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Long errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorText() {
        return errorText;
    }

    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Long getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(Long orderCode) {
        this.orderCode = orderCode;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

}
