package fr.viewpoint.client;

/**
 * In live mode some information are common to every indicators of a
 * transmition. This wrapper class allow user to define these common data
 * 
 * @author jpoirier
 * 
 */
public class LiveWrapper {
    private String userAgent;
    private String userId;
    private String referer;
    private String uri;
    private String requestId;
    private String sessionId;
    private String ip;
    private String method;

    /**
     * Default constructor
     */
    public LiveWrapper() {

    }

    /**
     * Session oriented constructor
     * 
     * @param userId
     *            The user id
     * @param requestId
     *            The request id
     * @param sessionId
     *            The internal session id
     */
    public LiveWrapper(String userId, String requestId, String sessionId) {
        super();
        this.userId = userId;
        this.requestId = requestId;
        this.sessionId = sessionId;
    }

    /**
     * Default constructor
     * 
     * @param userAgent
     *            The userAgent used
     * @param userId
     *            The user id
     * @param referer
     *            The referer
     * @param uri
     *            The current uri
     * @param requestId
     *            The request id
     * @param sessionId
     *            The session id
     * @param ip
     *            The user IP address
     * @param method
     *            The method (GET/POST/DELETE/...)
     */
    public LiveWrapper(String userAgent, String userId, String referer,
            String uri, String requestId, String sessionId, String ip,
            String method) {
        super();
        this.userAgent = userAgent;
        this.userId = userId;
        this.referer = referer;
        this.uri = uri;
        this.requestId = requestId;
        this.sessionId = sessionId;
        this.ip = ip;
        this.method = method;
    }

    /**
     * Copy Constructor
     * 
     * @param copy
     */
    public LiveWrapper(LiveWrapper copy) {
        userAgent = copy.getUserAgent();
        userId = copy.getUserId();
        referer = copy.getReferer();
        uri = copy.getUri();
        requestId = copy.getRequestId();
        sessionId = copy.getSessionId();
        ip = copy.getIp();
        method = copy.getMethod();
    }

    /**
     * @return the userAgent
     */
    public String getUserAgent() {
        return userAgent;
    }

    /**
     * @param userAgent
     *            the userAgent to set
     */
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     *            the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the referer
     */
    public String getReferer() {
        return referer;
    }

    /**
     * @param referer
     *            the referer to set
     */
    public void setReferer(String referer) {
        this.referer = referer;
    }

    /**
     * @return the uri
     */
    public String getUri() {
        return uri;
    }

    /**
     * @param uri
     *            the uri to set
     */
    public void setUri(String uri) {
        this.uri = uri;
    }

    /**
     * @return the requestId
     */
    public String getRequestId() {
        return requestId;
    }

    /**
     * @param requestId
     *            the requestId to set
     */
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    /**
     * @return the sessionId
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * @param sessionId
     *            the sessionId to set
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    /**
     * @return the ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip
     *            the ip to set
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * @return the method
     */
    public String getMethod() {
        return method;
    }

    /**
     * @param method
     *            the method to set
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "LiveWrapper [userAgent=" + userAgent + ", userId=" + userId
                + ", referer=" + referer + ", uri=" + uri + ", requestId="
                + requestId + ", sessionId=" + sessionId + ", ip=" + ip
                + ", method=" + method + "]";
    }

}
