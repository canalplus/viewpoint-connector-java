package fr.viewpoint.client;

/**
 * Define indicator type
 * 
 * @author jpoirier
 * 
 */
public enum IndicatorType {

    /**
     * WebService event
     */
    WEB_SERVICE(0),

    /**
     * Page event
     */
    PAGE(1),

    /**
     * Generic time indicator
     */
    TIME(2),

    /**
     * Server related events
     */
    SERVER_LOAD(3),

    /**
     * A generic one
     */
    GENERIC(5),

    /**
     * Ok ko count
     */
    OKKO(6),

    /**
     * Transformation rate
     */
    TRANSFO(7),

    /**
     * Count
     */
    COUNT(8),

    /**
     * Service event
     */
    SERVICE(10),

    /**
     * Function event
     */
    FUNCTION(11),

    /**
     * Business indicator
     */
    BUSINESS(12),

    /**
     * Server technical data
     */
    SERVER(13),

    /**
     * Internal Webcare indicator Node heart beat
     */
    WEBCARE_NODE_HB(100);

    private final int value;

    /**
     * 
     * @param value
     */
    private IndicatorType(int value) {
        this.value = value;
    }

    /**
     * 
     * @return
     */
    public int getValue() {
        return value;
    }
}
