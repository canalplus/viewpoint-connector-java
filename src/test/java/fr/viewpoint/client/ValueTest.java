package fr.viewpoint.client;

import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;

import fr.viewpoint.client.complex.NodeHB;

public class ValueTest {

	@Before
	public void before() {
		StorageFactory.setType(StorageFactory.StorageType.NULL);
	}

	@Test
	public void testConstructors() {

		NodeHB testMap = new NodeHB();

		TechnicalIndicator id = null;

		// Basic constructor
		id = new Value("id1", testMap, IndicatorType.GENERIC);
		Assert.assertEquals("id1", id.getName());
		Assert.assertEquals(testMap, id.getComplexValue());
		Assert.assertEquals(IndicatorType.GENERIC, id.getType());

		id = new Value("id1", 0.1, IndicatorType.GENERIC);
		Assert.assertEquals("id1", id.getName());
		Assert.assertEquals(0.1f, id.getValue(), 5);
		Assert.assertEquals(IndicatorType.GENERIC, id.getType());

		// Basic constructor + Msg
		id = new Value("id1", testMap, "this is the message",
				IndicatorType.GENERIC);
		Assert.assertEquals("id1", id.getName());
		Assert.assertEquals(testMap, id.getComplexValue());
		Assert.assertEquals(IndicatorType.GENERIC, id.getType());
		Assert.assertEquals("this is the message", id.getMsg());

		id = new Value("id1", 0.1, "this is the message",
				IndicatorType.GENERIC);
		Assert.assertEquals("id1", id.getName());
		Assert.assertEquals(0.1f, id.getValue(), 5);
		Assert.assertEquals(IndicatorType.GENERIC, id.getType());
		Assert.assertEquals("this is the message", id.getMsg());

		// Basic constructor + Msg + Detail
		id = new Value("id1", testMap, "this is the message",
				IndicatorType.GENERIC, "This is the detail");
		Assert.assertEquals("id1", id.getName());
		Assert.assertEquals(testMap, id.getComplexValue());
		Assert.assertEquals(IndicatorType.GENERIC, id.getType());
		Assert.assertEquals("this is the message", id.getMsg());
		Assert.assertEquals("This is the detail", id.getDetail());

		id = new Value("id1", 0.1, "this is the message",
				IndicatorType.GENERIC, "This is the detail");
		Assert.assertEquals("id1", id.getName());
		Assert.assertEquals(0.1f, id.getValue(), 5);
		Assert.assertEquals(IndicatorType.GENERIC, id.getType());
		Assert.assertEquals("this is the message", id.getMsg());
		Assert.assertEquals("This is the detail", id.getDetail());

		// Basic constructor + Msg + Detail + Status
		id = new Value("id1", testMap, "this is the message",
				IndicatorType.GENERIC, true, "This is the detail");
		Assert.assertEquals("id1", id.getName());
		Assert.assertEquals(testMap, id.getComplexValue());
		Assert.assertEquals(IndicatorType.GENERIC, id.getType());
		Assert.assertEquals("this is the message", id.getMsg());
		Assert.assertEquals("This is the detail", id.getDetail());
		Assert.assertEquals(true, id.isOk().booleanValue());

		id = new Value("id1", 0.1, "this is the message",
				IndicatorType.GENERIC, false, "This is the detail");
		Assert.assertEquals("id1", id.getName());
		Assert.assertEquals(0.1f, id.getValue(), 5);
		Assert.assertEquals(IndicatorType.GENERIC, id.getType());
		Assert.assertEquals("this is the message", id.getMsg());
		Assert.assertEquals("This is the detail", id.getDetail());
		Assert.assertEquals(false, id.isOk().booleanValue());

		// Test errorType
		id = new Value("id1", 0.1, "this is the message",
				IndicatorType.GENERIC, false, IndicatorErrorType.TECHNICAL,
				"This is the detail");
		Assert.assertEquals("id1", id.getName());
		Assert.assertEquals(0.1f, id.getValue(), 5);
		Assert.assertEquals(IndicatorType.GENERIC, id.getType());
		Assert.assertEquals("this is the message", id.getMsg());
		Assert.assertEquals("This is the detail", id.getDetail());
		Assert.assertEquals(IndicatorErrorType.TECHNICAL, id.getErrorType());
		Assert.assertEquals(false, id.isOk().booleanValue());
	}
}
