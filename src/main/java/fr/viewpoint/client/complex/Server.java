package fr.viewpoint.client.complex;

/**
 * Represent server information
 * 
 * @author xw
 *
 */
public class Server implements Complex {

    /**
     * Server name
     */
    private String server;

    /**
     * CPU Load
     */
    private Double load_time;

    private Long heapUsed;

    private Long heapTotal;

    private Long gcNbFull;

    private Double gcTime;

    private Long swapUsed;

    private Long swapSpace;

    /**
     * @return the server
     */
    public String getServer() {
        return server;
    }

    /**
     * @param server
     *            the server to set
     */
    public Server setServer(String server) {
        this.server = server;
        return this;
    }

    /**
     * @return the load_time
     */
    public Double getLoad_time() {
        return load_time;
    }

    /**
     * @param load_time
     *            the load_time to set
     */
    public Server setLoad_time(Double load_time) {
        this.load_time = load_time;
        return this;
    }

    /**
     * @return the heapUsed
     */
    public Long getHeapUsed() {
        return heapUsed;
    }

    /**
     * @param heapUsed
     *            the heapUsed to set
     */
    public Server setHeapUsed(Long heapUsed) {
        this.heapUsed = heapUsed;
        return this;
    }

    /**
     * @return the heapTotal
     */
    public Long getHeapTotal() {
        return heapTotal;
    }

    /**
     * @param heapTotal
     *            the heapTotal to set
     */
    public Server setHeapTotal(Long heapTotal) {
        this.heapTotal = heapTotal;
        return this;
    }

    /**
     * @return the gcNbFull
     */
    public Long getGcNbFull() {
        return gcNbFull;
    }

    /**
     * @param gcNbFull
     *            the gcNbFull to set
     */
    public Server setGcNbFull(Long gcNbFull) {
        this.gcNbFull = gcNbFull;
        return this;
    }

    /**
     * @return the gcTime
     */
    public Double getGcTime() {
        return gcTime;
    }

    /**
     * @param gcTime
     *            the gcTime to set
     */
    public Server setGcTime(Double gcTime) {
        this.gcTime = gcTime;
        return this;
    }

    /**
     * @return the swapUsed
     */
    public Long getSwapUsed() {
        return swapUsed;
    }

    /**
     * @param swapUsed
     *            the swapUsed to set
     */
    public Server setSwapUsed(Long swapUsed) {
        this.swapUsed = swapUsed;
        return this;
    }

    /**
     * @return the swapSpace
     */
    public Long getSwapSpace() {
        return swapSpace;
    }

    /**
     * @param swapSpace
     *            the swapSpace to set
     */
    public Server setSwapSpace(Long swapSpace) {
        this.swapSpace = swapSpace;
        return this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Server [server=" + server + ", load_time=" + load_time
                + ", heapUsed=" + heapUsed + ", heapTotal=" + heapTotal
                + ", gcNbFull=" + gcNbFull + ", gcTime=" + gcTime
                + ", swapUsed=" + swapUsed + ", swapSpace=" + swapSpace + "]";
    }

}
