package fr.viewpoint.client;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.viewpoint.client.complex.Complex;

/**
 * Define the structure of a technical indicator
 * 
 * @author jpoirier
 * 
 */
public abstract class TechnicalIndicator extends Indicator {

    private String msg;
    private String detail;
    private Double value = 0.;
    private Complex complexValue;
    
    @JsonProperty("isOk")
    private Boolean isOk = true;
    private String userId;
    private IndicatorErrorType errorType;

    /**
     * 
     * Constructor
     * 
     * @param name
     *            The indicator name
     * @param date
     *            The indicator date
     * @param msg
     *            A short message to send
     * @param detail
     *            A string detail long message
     * @param value
     *            The value
     * @param complexValue
     *            Complex Value structure if needed
     * @param type
     *            The indicator type
     * @param isOk
     *            If it's reprenting a ok or a ko
     */
    public TechnicalIndicator(String name, Date date, String msg,
            String detail, Double value, Complex complexValue,
            IndicatorType type, Boolean isOk) {
        this(name, date, msg, detail, value, complexValue, type, isOk,
                IndicatorErrorType.SUCCESS);
    }

    /**
     * 
     * Constructor
     * 
     * @param name
     *            The indicator name
     * @param date
     *            The indicator date
     * @param msg
     *            A short message to send
     * @param detail
     *            A string detail long message
     * @param value
     *            The value
     * @param complexValue
     *            Complex Value structure if needed
     * @param type
     *            The indicator type
     * @param isOk
     *            If it's reprenting a ok or a ko
     */
    public TechnicalIndicator(String name, Date date, String msg,
            String detail, Double value, Complex complexValue,
            IndicatorType type, Boolean isOk, IndicatorErrorType errorType) {
        super(type, name, date);
        this.msg = msg;
        this.detail = detail;
        this.value = value;
        this.complexValue = complexValue;
        this.isOk = isOk;
        this.errorType = errorType;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg
     *            the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @return the detail
     */
    public String getDetail() {
        return detail;
    }

    /**
     * @param detail
     *            the detail to set
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }

    /**
     * @return the value
     */
    public Double getValue() {
        return value;
    }

    /**
     * @param value
     *            the value to set
     * @deprecated Use setValue(Double) instead
     */
    public void setValue(Float value) {
        this.value = new Double(value);
    }

    /**
     * @param value
     *            the value to set
     */
    public void setValue(Double value) {
        this.value = value;
    }

    /**
     * @return the complexValue
     */
    public Complex getComplexValue() {
        return complexValue;
    }

    /**
     * @param complexValue
     *            the complexValue to set
     */
    public void setComplexValue(Complex complexValue) {
        this.complexValue = complexValue;
    }

    /**
     * @return the isOk
     */
    @JsonProperty("isOk")
    public Boolean isOk() {
        return isOk;
    }

    /**
     * @param isOk
     *            the isOk to set
     */
    public void setOk(Boolean isOk) {
        this.isOk = isOk;
    }

    /**
     * @return the errorType
     */
    public IndicatorErrorType getErrorType() {
        return errorType;
    }

    /**
     * @param errorType
     *            the errorType to set
     */
    public void setErrorType(IndicatorErrorType errorType) {
        this.errorType = errorType;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "WcTechnicalIndicator [msg=" + msg + ", detail=" + detail
                + ", value=" + value + ", complexValue=" + complexValue
                + ", isOk=" + isOk + ", userId=" + userId + ", errorType="
                + errorType + ", getType()=" + getType() + ", getName()="
                + getName() + ", getDate()=" + getDate() + ", getTimestamp()="
                + getTimestamp() + "]";
    }
}
