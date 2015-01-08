package fr.viewpoint.client;

import fr.viewpoint.client.complex.Complex;

public class Value extends TechnicalIndicator {

    /**
     * 
     * @param name
     *            The indicator name
     * @param value
     *            The value
     * @param msg
     *            A short message to send
     * @param type
     *            The indicator type
     * @param isOk
     *            If it's reprenting a ok or a ko
     * @param detail
     *            A string detail long message
     */
    public Value(String name, Double value, String msg, IndicatorType type,
            boolean isOk, IndicatorErrorType errorType, String detail) {
        super(name, null, msg, detail, value, null, type, isOk, errorType);
    }

    /**
     * 
     * @param name
     *            The indicator name
     * @param value
     *            The value
     * @param msg
     *            A short message to send
     * @param type
     *            The indicator type
     * @param isOk
     *            If it's reprenting a ok or a ko
     * @param detail
     *            A string detail long message
     */
    public Value(String name, Double value, String msg, IndicatorType type,
            boolean isOk, String detail) {
        super(name, null, msg, detail, value, null, type, isOk);
    }

    /**
     * 
     * @param name
     *            The indicator name
     * @param value
     *            The value
     * @param msg
     *            A short message to send
     * @param type
     *            The indicator type
     * @param detail
     *            A string detail long message
     */
    public Value(String name, Double value, String msg, IndicatorType type,
            String detail) {
        super(name, null, msg, detail, value, null, type, true);
    }

    /**
     * 
     * @param name
     *            The indicator name
     * @param value
     *            The value
     * @param msg
     *            A short message to send
     * @param type
     *            The indicator type
     */
    public Value(String name, Double value, String msg, IndicatorType type) {
        super(name, null, msg, null, value, null, type, true);
    }

    /**
     * 
     * @param name
     *            The indicator name
     * @param value
     *            The value
     * @param type
     *            The indicator type
     */
    public Value(String name, Double value, IndicatorType type) {
        super(name, null, null, null, value, null, type, true);
    }

    /**
     * 
     * @param name
     *            The indicator name
     * @param type
     *            The indicator type
     */
    public Value(String name, IndicatorType type) {
        super(name, null, null, null, null, null, type, true);
    }

    /**
     * 
     * @param name
     *            The indicator name
     * @param value
     *            The value
     * @param msg
     *            A short message to send
     * @param type
     *            The indicator type
     * @param isOk
     *            If it's reprenting a ok or a ko
     * @param detail
     *            A string detail long message
     */
    public Value(String name, Complex value, String msg, IndicatorType type,
            boolean isOk, String detail) {
        super(name, null, msg, detail, null, value, type, isOk);
    }

    /**
     * 
     * @param name
     *            The indicator name
     * @param value
     *            The value
     * @param msg
     *            A short message to send
     * @param type
     *            The indicator type
     * @param detail
     *            A string detail long message
     */
    public Value(String name, Complex value, String msg, IndicatorType type,
            String detail) {
        super(name, null, msg, detail, null, value, type, true);
    }

    /**
     * 
     * @param name
     *            The indicator name
     * @param value
     *            The value
     * @param msg
     *            A short message to send
     * @param type
     *            The indicator type
     */
    public Value(String name, Complex value, String msg, IndicatorType type) {
        super(name, null, msg, null, null, value, type, true);
    }

    /**
     * 
     * @param name
     *            The indicator name
     * @param value
     *            The value
     * @param type
     *            The indicator type
     */
    public Value(String name, Complex value, IndicatorType type) {
        super(name, null, null, null, null, value, type, true);

    }
}
