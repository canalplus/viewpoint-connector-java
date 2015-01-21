package fr.viewpoint.client;

import java.util.Date;

import fr.viewpoint.client.complex.Complex;

/**
 * Server Load indicator
 * 
 * @author xw
 *
 */
public class ServerLoad extends TechnicalIndicator {

    public ServerLoad(String name, Complex complexValue) {
        super(name, new Date(), null, null, null, complexValue,
                IndicatorType.SERVER_LOAD, true);
    }

    public ServerLoad(String name, Date date, String msg, String detail,
            Double value, Complex complexValue, Boolean isOk, String serverName) {
        super(name, date, msg, detail, value, complexValue,
                IndicatorType.SERVER_LOAD, isOk);
    }

    public ServerLoad(String name, Date date, String msg, String detail,
            Double value, Complex complexValue, Boolean isOk,
            IndicatorErrorType errorType, String serverName) {
        super(name, date, msg, detail, value, complexValue,
                IndicatorType.SERVER_LOAD, isOk, errorType);
    }

}
