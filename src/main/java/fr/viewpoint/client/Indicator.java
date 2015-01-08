package fr.viewpoint.client;

import java.util.Date;

/**
 * The top indicator model
 * 
 * @author jpoirier
 * 
 */
public abstract class Indicator {

    /**
     * The indicator type
     */
    private IndicatorType type;

    /**
     * The indicator name
     */
    private String name;

    /**
     * The timestamp in seconde
     */
    private Integer date;

    /**
     * The timestamp in milli
     */
    private Long timestamp;

    public static String NAME_REGEX = "[^\\p{L}\\p{N}]";

    /**
     * Default constructor
     * 
     * @param type
     *            The indicator type
     * @param name
     *            The indicator name / type / group name
     */
    public Indicator(IndicatorType type, String name, Date date) {
        super();
        this.type = type;
        setName(name);

        if (null != date) {
            this.date = (int) (date.getTime() / 1000);
            this.timestamp = date.getTime();
        }
    }

    /**
     * @return the type
     */
    public IndicatorType getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(IndicatorType type) {
        this.type = type;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        if (null != name) {
            this.name = name.replaceAll(NAME_REGEX, "_");
        }
    }

    /**
     * @return the date
     */
    public Integer getDate() {
        return date;
    }

    /**
     * @param date
     *            the date to set
     */
    public void setDate(Date date) {
        if (null != date) {
            this.date = (int) (date.getTime() / 1000);
            this.timestamp = date.getTime();
        }
    }

    /**
     * @return the timestamp
     */
    public Long getTimestamp() {
        return timestamp;
    }

    /**
     * @param date
     *            the date to set
     */
    public void setDate(Integer date) {
        if (null != date) {
            this.date = date;
            this.timestamp = (long) (date * 1000);
        }
    }

    /**
     * @param timestamp
     *            the timestamp to set
     */
    public void setTimestamp(Long timestamp) {
        if (null != timestamp) {
            this.timestamp = timestamp;
            setDate((int) (timestamp / 1000));
        }
    }
}
