package fr.viewpoint.client.storage.impl;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.AlreadyClosedException;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import fr.viewpoint.client.Configuration;
import fr.viewpoint.client.Indicator;
import fr.viewpoint.client.LiveWrapper;
import fr.viewpoint.client.storage.Storage;
import fr.viewpoint.client.storage.StorageException;

/**
 * Manage to send indicator to the app server
 * 
 * Viewpoint to rabbitMQ implementation.
 * 
 * In this storage the commit method has no effect since each call to a store
 * method will generate a call to rabbitMQ.
 * 
 * @author UFO
 * 
 */
public class LiveStorageImpl implements Storage {

    /**
     * Log
     */
    private static final Logger log = LoggerFactory
            .getLogger(LiveStorageImpl.class);

    /**
     * The configuration
     */
    private Configuration config = null;

    /**
     * The connection
     */
    private Connection connection = null;

    /**
     * The channel to use
     */
    private Channel channel = null;

    /**
     * Needed to initialiaze the channel
     */
    private boolean isInitialized = false;

    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * Constructor
     * 
     * @param config
     */
    public LiveStorageImpl(Configuration config) {
        this.config = config;

        String serverHost = config.getLiveServerHost();
        Integer serverPort = config.getLiveServerPort();
        String exchangeName = config.getLiveExchangeName();
        String projectName = config.getLiveProjectName();

        if (null == serverHost || "".equals(serverHost)) {
            throw new IllegalArgumentException("serverHost is empty");
        }
        if (null == serverPort) {
            throw new IllegalArgumentException("serverPort is empty");
        }
        if (null == exchangeName || "".equals(exchangeName)) {
            throw new IllegalArgumentException("exchangeName is empty");
        }
        if (null == projectName || "".equals(projectName)) {
            throw new IllegalArgumentException("projectName is empty");
        }

    }

    /**
     * Initialize the channel
     * 
     * @throws IOException
     */
    public void init() throws IOException {
        if (!isInitialized) {
            // Connect
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(config.getLiveServerHost());
            factory.setPort(config.getLiveServerPort());
            factory.setConnectionTimeout(config.getLiveConnectionTimeout());
            factory.setUsername(config.getLiveUsername());
            factory.setPassword(config.getLivePassword());

            // Declare channel
            connection = factory.newConnection();
            channel = connection.createChannel();
            channel.exchangeDeclare(config.getLiveExchangeName(), "topic", true);
            isInitialized = true;
        }
    }

    private Channel getChannel() throws IOException {
        init();
        return channel;
    }

    public void store(Indicator indicator, String userId) {
        WrapperData localWrapper = new WrapperData();
        localWrapper.setUserId(userId);
        store(Arrays.asList(indicator), localWrapper);

    }

    public void store(List<Indicator> indicators, String userId) {
        WrapperData localWrapper = new WrapperData();
        localWrapper.setUserId(userId);
        store(indicators, localWrapper);
    }

    public void store(List<Indicator> indicators, LiveWrapper liveWrapper) {
        store(indicators, new WrapperData(liveWrapper));
    }

    public void store(Indicator indicator) {
        store(Arrays.asList(indicator), new WrapperData());

    }

    public void store(List<Indicator> indicators) {
        store(indicators, new WrapperData());

    }

    /**
     * Store the indicator
     * 
     * @param indicators
     *            The indicator list
     * @param wrapper
     *            The wrapper information
     */
    public void store(List<Indicator> indicators, WrapperData wrapper) {

        if (null == indicators || 0 == indicators.size()) {
            return;
        }

        // Create a wrapper to transform the POJO
        WrapperData localWrapper = wrapper;
        if (null == localWrapper) {
            localWrapper = new WrapperData();
        }

        try {

            // Add the indicator list to the Map
            localWrapper.setEvents(indicators);
            localWrapper.setProject(config.getLiveProjectName());

            if (log.isDebugEnabled()) {
                log.debug("Send indicator to ActiveMQ ["
                        + mapper.writeValueAsString(localWrapper) + "]");
            }

            getChannel().basicPublish(config.getLiveExchangeName(), "WEBCARE",
                    null, mapper.writeValueAsString(localWrapper).getBytes());

        } catch (AlreadyClosedException e) {

            // If a
            // "clean connection shutdown; reason: Attempt to use closed channel"
            // happen
            // Reconnect
            isInitialized = false;

            // The message will be lost...

        } catch (IOException e) {
            throw new StorageException(e);
        }

    }

    public void commit() {
        return;
    }

    public void close() {

        if (log.isDebugEnabled()) {
            log.debug("Closing RabbitMQ Channel");
        }

        try {
            if (null != getChannel()) {
                getChannel().close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (log.isDebugEnabled()) {
                log.debug("Error during channel closing [" + e.getMessage()
                        + "]", e);
            }
        }

        if (log.isDebugEnabled()) {
            log.debug("RabbitMQ channel closed");
        }

        if (log.isDebugEnabled()) {
            log.debug("Closing RabbitMQ Connection");
        }

        if (null != connection) {

            if (log.isDebugEnabled()) {
                log.debug("Closing RabbitMQ Connection");
            }

            try {
                connection.close(100);
            } catch (Exception e) {
                e.printStackTrace();
                if (log.isDebugEnabled()) {
                    log.debug(
                            "Error during connection closing ["
                                    + e.getMessage() + "]", e);
                }
            }
        }

        if (log.isDebugEnabled()) {
            log.debug("RabbitMQ connection closed");
        }

    }

    /**
     * Common attributes send with the indicators
     * 
     * @author jpoirier
     * 
     */
    public static class WrapperData extends LiveWrapper {
        private List<Indicator> events;
        private Indicator event;
        private String project;
        private String appVersion;

        public WrapperData() {
            super();
        }

        public WrapperData(LiveWrapper copy) {
            super(copy);
        }

        public WrapperData(WrapperData copy) {
            super(copy);
            events = copy.getEvents();
            event = copy.getEvent();
            project = copy.getProject();
            appVersion = copy.getAppVersion();
        }

        /**
         * @return the appVersion
         */
        public String getAppVersion() {
            return appVersion;
        }

        /**
         * @param appVersion
         *            the appVersion to set
         */
        public void setAppVersion(String appVersion) {
            this.appVersion = appVersion;
        }

        /**
         * @return the events
         */
        public List<Indicator> getEvents() {
            return events;
        }

        /**
         * @param events
         *            the events to set
         */
        public void setEvents(List<Indicator> events) {
            this.events = events;
        }

        /**
         * @return the event
         */
        public Indicator getEvent() {
            return event;
        }

        /**
         * @param event
         *            the event to set
         */
        public void setEvent(Indicator event) {
            this.event = event;
        }

        /**
         * @return the project
         */
        public String getProject() {
            return project;
        }

        /**
         * @param project
         *            the project to set
         */
        public void setProject(String project) {
            this.project = project;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return "WrapperData [events=" + events + ", project=" + project
                    + "]";
        }
    }

    public void rawStore(String rawContent) {
        if (null == rawContent || "".equals(rawContent)) {
            return;
        }

        try {

            if (log.isDebugEnabled()) {
                log.debug("Send indicator to ActiveMQ ["
                        + config.getLiveExchangeName() + ", " + rawContent
                        + "]");
            }

            getChannel().basicPublish(config.getLiveExchangeName(), "WEBCARE",
                    null, rawContent.getBytes());

        } catch (AlreadyClosedException e) {

            // If a
            // "clean connection shutdown; reason: Attempt to use closed channel"
            // happen
            // Reconnect
            isInitialized = false;

            // The message will be lost...

        } catch (IOException e) {
            throw new StorageException(e);
        }

    }

    public void rawStore(List<String> rawContent) {
        if (null != rawContent) {
            for (String string : rawContent) {
                rawStore(string);
            }
        }
    }
}