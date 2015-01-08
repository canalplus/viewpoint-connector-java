package fr.viewpoint.client;

/**
 * Represent a Timer
 * 
 * Usage one time transaction:
 * 
 * <pre>
 * Time timeIndic = new Time("indicatorId", IndicatorType.WEB_SERVICE);
 * timeIndic.start();
 * 
 * <Do operations><br>
 * <...>
 * timeIndic.stopAndStore();
 * </pre>
 * 
 * Usage two time transaction:
 * 
 * <pre>
 * Time timeIndic = new Time("indicatorId", IndicatorType.WEB_SERVICE);
 * timeIndic.start();
 * 
 * <Do operations><br>
 * <...>
 * timeIndic.stopNoStore();
 * 
 * <Do check operation>
 * 
 * </pre>
 * 
 * timeIndic.setMsg("Bad response from") timeIndic.doStore(false);
 * 
 * 
 * @author jpoirier
 * 
 */
public class Time extends TechnicalIndicator {

    private Long start = null;

    /**
     * Constructor
     * 
     * @param name
     *            The indicator name
     * @param type
     *            The indicator type
     */
    public Time(String name, IndicatorType type) {
        super(name, null, null, null, null, null, type, true);
    }

    public void start() {
        start = System.nanoTime();
    }

    /**
     * Stop and no store the indicator
     * 
     */
    public void stop() {
        Double t = new Double(System.nanoTime() - start);
        t = t / 1000000000;
        setValue(t);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "WcTime [start=" + start + " " + super.toString() + "]";
    }

}
