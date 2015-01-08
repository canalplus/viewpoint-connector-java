package fr.viewpoint.client.storage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import mockit.Deencapsulation;
import mockit.Mock;
import mockit.MockUp;
import mockit.NonStrictExpectations;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import fr.viewpoint.client.Configuration;
import fr.viewpoint.client.Indicator;
import fr.viewpoint.client.IndicatorType;
import fr.viewpoint.client.LiveWrapper;
import fr.viewpoint.client.Value;
import fr.viewpoint.client.storage.impl.LiveStorageImpl;
import fr.viewpoint.utils.rest.Client;

public class WebcareLiveStorageImplTest {

	private static final Logger log = LoggerFactory
			.getLogger(WebcareLiveStorageImplTest.class);

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testConfig() throws StorageException {

		// Exchange
		boolean hasBeenCalled = false;
		try {
			Configuration config = new Configuration();
			config.setLiveProjectName("project");
			config.setLiveServerHost("http://test");
			config.setLiveServerPort(5672);
			Storage storage = new LiveStorageImpl(config);
		} catch (IllegalArgumentException e) {
			if ("exchangeName is empty".equals(e.getMessage())) {
				hasBeenCalled = true;
			}
		}

		Assert.assertEquals(true, hasBeenCalled);

		// project
		hasBeenCalled = false;
		try {
			Configuration config = new Configuration();
			config.setLiveExchangeName("test");
			config.setLiveServerHost("http://test");
			config.setLiveServerPort(5672);
			Storage storage = new LiveStorageImpl(config);
		} catch (IllegalArgumentException e) {
			if ("projectName is empty".equals(e.getMessage())) {
				hasBeenCalled = true;
			}
		}

		Assert.assertEquals(true, hasBeenCalled);

		// host
		hasBeenCalled = false;
		try {
			Configuration config = new Configuration();
			config.setLiveExchangeName("test");
			config.setLiveProjectName("project");
			Storage storage = new LiveStorageImpl(config);
		} catch (IllegalArgumentException e) {
			if ("serverHost is empty".equals(e.getMessage())) {
				hasBeenCalled = true;
			}
		}

		Assert.assertEquals(true, hasBeenCalled);

	}

	@Test
	public void testOk() throws StorageException {

		// Mock retuned data
		Client.setInputStream(getClass().getResourceAsStream("returnOk.dat"));

		Configuration config = new Configuration();
		config.setLiveExchangeName("test");
		config.setLiveProjectName("project");
		config.setLiveServerHost("http://test");
		config.setLiveServerPort(5672);
		final Storage storage = new LiveStorageImpl(config);
		final List<String> result = new ArrayList<String>();

		// Mock storage
		final Channel channel = new MockUp<Channel>() {

			@Mock
			void basicPublish(String exchange, String routingKey,
					BasicProperties props, byte[] body) throws IOException {
				result.add(new String(body));
			}

			@Mock
			void basicPublish(String exchange, String routingKey,
					boolean mandatory, BasicProperties props, byte[] body)
					throws IOException {
				result.add(new String(body));
			}

			@Mock
			void basicPublish(String exchange, String routingKey,
					boolean mandatory, boolean immediate,
					BasicProperties props, byte[] body) throws IOException {
				result.add(new String(body));
			}
		}.getMockInstance();

		new NonStrictExpectations(storage) {
			{ // <== note the argument here
			    Deencapsulation.invoke(storage, "getChannel");
				returns(channel);
			}
		};

		// Store an indicator
		storage.store(new Value("id5", 1., IndicatorType.COUNT));
		Assert.assertEquals(1, result.size());
		log.error("{\"events\":[{\"name\":\"id5\",\"timestamp\":0,\"msg\":null,\"detail\":null,\"value\":1.0,\"complexValue\":null,\"type\":\"COUNT\",\"start\":0.0,\"isOk\":true}],\"userAgent\":null,\"userId\":null,\"referer\":null,\"uri\":null,\"requestId\":null,\"project\":\"project\",\"sessionId\":null,\"ip\":null,\"method\":null}");
		log.error(result.get(0).toString());
		Assert.assertEquals(
				"store indicator",
				"{\"userAgent\":null,\"userId\":null,\"referer\":null,\"uri\":null,\"requestId\":null,\"sessionId\":null,\"ip\":null,\"method\":null,\"events\":[{\"type\":\"COUNT\",\"name\":\"id5\",\"date\":null,\"timestamp\":null,\"msg\":null,\"detail\":null,\"value\":1.0,\"complexValue\":null,\"isOk\":true,\"errorType\":\"SUCCESS\"}],\"event\":null,\"project\":\"project\",\"appVersion\":null}",
				result.get(0).toString());
		result.clear();

		// with user
		storage.store(new Value("id1", 1., IndicatorType.COUNT), "jacques");
		Assert.assertEquals(1, result.size());
		Assert.assertEquals(
				"store indicator with userid",
				"{\"userAgent\":null,\"userId\":\"jacques\",\"referer\":null,\"uri\":null,\"requestId\":null,\"sessionId\":null,\"ip\":null,\"method\":null,\"events\":[{\"type\":\"COUNT\",\"name\":\"id1\",\"date\":null,\"timestamp\":null,\"msg\":null,\"detail\":null,\"value\":1.0,\"complexValue\":null,\"isOk\":true,\"errorType\":\"SUCCESS\"}],\"event\":null,\"project\":\"project\",\"appVersion\":null}",
				result.get(0).toString());
		result.clear();

		// Store a list of indicator
		List<Indicator> list = new ArrayList<Indicator>();
		list.add(new Value("id1", 1., IndicatorType.COUNT));
		list.add(new Value("id2", 1., IndicatorType.COUNT));
		storage.store(list);
		Assert.assertEquals(1, result.size());
		Assert.assertEquals(
				"store list",
				"{\"userAgent\":null,\"userId\":null,\"referer\":null,\"uri\":null,\"requestId\":null,\"sessionId\":null,\"ip\":null,\"method\":null,\"events\":[{\"type\":\"COUNT\",\"name\":\"id1\",\"date\":null,\"timestamp\":null,\"msg\":null,\"detail\":null,\"value\":1.0,\"complexValue\":null,\"isOk\":true,\"errorType\":\"SUCCESS\"},{\"type\":\"COUNT\",\"name\":\"id2\",\"date\":null,\"timestamp\":null,\"msg\":null,\"detail\":null,\"value\":1.0,\"complexValue\":null,\"isOk\":true,\"errorType\":\"SUCCESS\"}],\"event\":null,\"project\":\"project\",\"appVersion\":null}",
				result.get(0).toString());
		result.clear();

		// with user
		storage.store(list, "bobo");
		Assert.assertEquals(1, result.size());
		Assert.assertEquals(
				"store list with userid",
				"{\"userAgent\":null,\"userId\":\"bobo\",\"referer\":null,\"uri\":null,\"requestId\":null,\"sessionId\":null,\"ip\":null,\"method\":null,\"events\":[{\"type\":\"COUNT\",\"name\":\"id1\",\"date\":null,\"timestamp\":null,\"msg\":null,\"detail\":null,\"value\":1.0,\"complexValue\":null,\"isOk\":true,\"errorType\":\"SUCCESS\"},{\"type\":\"COUNT\",\"name\":\"id2\",\"date\":null,\"timestamp\":null,\"msg\":null,\"detail\":null,\"value\":1.0,\"complexValue\":null,\"isOk\":true,\"errorType\":\"SUCCESS\"}],\"event\":null,\"project\":\"project\",\"appVersion\":null}",
				result.get(0).toString());
		result.clear();

		// with wrapper
		LiveWrapper liveWrapper = new LiveWrapper();
		liveWrapper.setRequestId("REQUESTID");
		liveWrapper.setSessionId("SESSIONID");
		liveWrapper.setIp("192.168.0.1");
		liveWrapper.setMethod("GET");
		liveWrapper.setReferer("http://referer");
		liveWrapper.setUri("http://uri");
		liveWrapper.setUserAgent("USERAGENT");
		liveWrapper.setUserId("bobo");
		storage.store(list, liveWrapper);
		Assert.assertEquals(1, result.size());
		Assert.assertEquals(
				"store list with userid",
				"{\"userAgent\":\"USERAGENT\",\"userId\":\"bobo\",\"referer\":\"http://referer\",\"uri\":\"http://uri\",\"requestId\":\"REQUESTID\",\"sessionId\":\"SESSIONID\",\"ip\":\"192.168.0.1\",\"method\":\"GET\",\"events\":[{\"type\":\"COUNT\",\"name\":\"id1\",\"date\":null,\"timestamp\":null,\"msg\":null,\"detail\":null,\"value\":1.0,\"complexValue\":null,\"isOk\":true,\"errorType\":\"SUCCESS\"},{\"type\":\"COUNT\",\"name\":\"id2\",\"date\":null,\"timestamp\":null,\"msg\":null,\"detail\":null,\"value\":1.0,\"complexValue\":null,\"isOk\":true,\"errorType\":\"SUCCESS\"}],\"event\":null,\"project\":\"project\",\"appVersion\":null}",
				result.get(0).toString());

		// with nothing
		result.clear();
		storage.store(new ArrayList<Indicator>());
		Assert.assertEquals(0, result.size());

	}

	@Test
	public void testPublishFailed() throws StorageException {

		// Mock retuned data
		Client.setInputStream(getClass().getResourceAsStream("returnOk.dat"));

		Configuration config = new Configuration();
		config.setLiveExchangeName("test");
		config.setLiveProjectName("project");
		config.setLiveServerHost("http://test");
		config.setLiveServerPort(5672);
		final Storage storage = new LiveStorageImpl(config);

		// Mock storage
		final Channel channel = new MockUp<Channel>() {

			@Mock
			void basicPublish(String exchange, String routingKey,
					BasicProperties props, byte[] body) throws IOException {
				throw new IOException("test");
			}

			@Mock
			void basicPublish(String exchange, String routingKey,
					boolean mandatory, BasicProperties props, byte[] body)
					throws IOException {
				throw new IOException("test");
			}

			@Mock
			void basicPublish(String exchange, String routingKey,
					boolean mandatory, boolean immediate,
					BasicProperties props, byte[] body) throws IOException {
				throw new IOException("test");
			}
		}.getMockInstance();

		new NonStrictExpectations(storage) {
			{ // <== note the argument here
			    Deencapsulation.invoke(storage, "getChannel");
				result = channel;
			}
		};

		// Store an indicator
		boolean hasBeenCalled = false;
		try {
			storage.store(new Value("id1", 1., IndicatorType.COUNT));
		} catch (StorageException e) {
			if ("java.io.IOException: test".equals(e.getMessage())) {
				hasBeenCalled = true;
			}
		}
		Assert.assertEquals(true, hasBeenCalled);
	}

	@Test
	public void testInit() {

		// Mock retuned data
		Client.setInputStream(getClass().getResourceAsStream("returnOk.dat"));

		Configuration config = new Configuration();
		config.setLiveExchangeName("test");
		config.setLiveProjectName("project");
		config.setLiveServerHost("http://test");
		config.setLiveServerPort(5672);
		final Storage storage = new LiveStorageImpl(config);

		// Mock storage
		new MockUp<ConnectionFactory>() {

			@Mock
			public Connection newConnection() throws IOException {
				throw new IOException("test");
			}
		};

		// Store an indicator
		boolean hasBeenCalled = false;
		try {
			storage.store(new Value("id1", 1., IndicatorType.COUNT));
		} catch (StorageException e) {
			if ("java.io.IOException: test".equals(e.getMessage())) {
				hasBeenCalled = true;
			}
		}
		Assert.assertEquals(true, hasBeenCalled);
	}
}
