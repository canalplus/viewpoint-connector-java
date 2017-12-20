package fr.viewpoint.client;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;

import mockit.Deencapsulation;
import mockit.Mock;
import mockit.MockUp;
import mockit.NonStrictExpectations;

import org.junit.Assert;
import org.junit.Test;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;

import fr.viewpoint.client.storage.Storage;
import fr.viewpoint.client.storage.impl.LiveStorageImpl;


public class WcBusinessTest {

	public static class MyBusiness extends BusinessIndicator {
		private Integer orderId;
		private String customerId;
		private Date orderCreationDate;

		public MyBusiness(String origin, String name, Date date,
				Integer orderId, String customerId, Date orderCreationDate) {
			super(origin, name, date);
			this.orderId = orderId;
			this.customerId = customerId;
			this.orderCreationDate = orderCreationDate;
		}

		/**
		 * @return the orderId
		 */
		public Integer getOrderId() {
			return orderId;
		}

		/**
		 * @param orderId
		 *            the orderId to set
		 */
		public void setOrderId(Integer orderId) {
			this.orderId = orderId;
		}

		/**
		 * @return the customerId
		 */
		public String getCustomerId() {
			return customerId;
		}

		/**
		 * @param customerId
		 *            the customerId to set
		 */
		public void setCustomerId(String customerId) {
			this.customerId = customerId;
		}

		/**
		 * @return the orderCreationDate
		 */
		public Date getOrderCreationDate() {
			return orderCreationDate;
		}

		/**
		 * @param orderCreationDate
		 *            the orderCreationDate to set
		 */
		public void setOrderCreationDate(Date orderCreationDate) {
			this.orderCreationDate = orderCreationDate;
		}
	}

	@Test
	public void testConstructors() throws ParseException {

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
				result = channel;
			}
		};

		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss:S");
		String dateInString = "03-02-2001 03:04:05:0";
		Date date = sdf.parse(dateInString);

		MyBusiness myB = new MyBusiness("FROM_ST", "ORDER", date, 1, "root1", date);

		storage.store(myB);

		Assert.assertEquals(1, result.size());
		Assert.assertEquals(
				"{\"userAgent\":null,\"userId\":null,\"referer\":null,\"uri\":null,\"requestId\":null,\"sessionId\":null,\"ip\":null,\"method\":null,\"events\":[{\"type\":\"BUSINESS\",\"name\":\"ORDER\",\"date\":981165845,\"timestamp\":981165845000,\"origin\":\"FROM_ST\",\"orderId\":1,\"customerId\":\"root1\",\"orderCreationDate\":981165845000}],\"event\":null,\"project\":\"project\",\"appVersion\":null,\"server\":null}",
				result.get(0));

	}

}
