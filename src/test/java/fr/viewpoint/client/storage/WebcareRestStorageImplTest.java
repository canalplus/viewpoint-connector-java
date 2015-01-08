package fr.viewpoint.client.storage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import fr.viewpoint.client.Configuration;
import fr.viewpoint.client.Indicator;
import fr.viewpoint.client.IndicatorType;
import fr.viewpoint.client.TechnicalIndicator;
import fr.viewpoint.client.Value;
import fr.viewpoint.client.storage.Storage;
import fr.viewpoint.client.storage.StorageException;
import fr.viewpoint.client.storage.impl.RestStorageImpl;
import fr.viewpoint.utils.rest.Client;

public class WebcareRestStorageImplTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test(expected = RuntimeException.class)
	public void testParamConfig() throws StorageException {

		Storage storage = new RestStorageImpl(null);
		storage.commit();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testParamServerUri() throws StorageException {

		Configuration config = new Configuration();
		Storage storage = new RestStorageImpl(config);
		storage.commit();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testParamPprojectName() throws StorageException {

		Configuration config = new Configuration();
		config.setProjectName("TEST");
		Storage storage = new RestStorageImpl(config);
		storage.commit();
	}

	@Test
	public void testNoIndicator() throws StorageException {

		// Mock retuned data
		Client.setInputStream(getClass().getResourceAsStream("returnOk.dat"));

		Configuration config = new Configuration();
		config.setProjectName("TEST");
		config.setServerUri("http://test");
		Storage storage = new RestStorageImpl(config);

		storage.commit();
	}

	@Test
	public void testKo() throws StorageException {

		thrown.expect(StorageException.class);
		thrown.expectMessage("Unable to store indicators [Can not deserialize instance of java.util.ArrayList out of START_OBJECT token");

		// Mock retuned data
		Client.setInputStream(getClass().getResourceAsStream("returnKo.dat"));

		Configuration config = new Configuration();
		config.setProjectName("TEST");
		config.setServerUri("http://test");
		Storage storage = new RestStorageImpl(config);
		storage.store(new Value("id1", 1., IndicatorType.COUNT));
		storage.commit();
	}

	@Test
	public void testOk() throws StorageException {

		// Mock retuned data
		Client.setInputStream(getClass().getResourceAsStream("returnOk.dat"));

		Configuration config = new Configuration();
		config.setProjectName("TEST");
		config.setServerUri("http://test");
		Storage storage = new RestStorageImpl(config);
		storage.store(new Value("id1", 1., IndicatorType.COUNT));
		storage.commit();
		
		Client.setInputStream(getClass().getResourceAsStream("returnOk.dat"));
		storage.store(new Value("id1", 1., IndicatorType.COUNT), "bob");
		storage.commit();

		// list
		Client.setInputStream(getClass().getResourceAsStream("returnOk.dat"));
		List<Indicator> list = new ArrayList<Indicator>();
		list.add(new Value("id1", 1., IndicatorType.COUNT));
		list.add(new Value("id2", 1., IndicatorType.COUNT));
		storage.store(list);
		storage.commit();

		// list
		Client.setInputStream(getClass().getResourceAsStream("returnOk.dat"));
		storage.store(list, "bob");
		storage.commit();
	}

	@Test
	public void testEmptyList() throws StorageException {

		// Mock retuned data
		Client.setInputStream(getClass().getResourceAsStream("returnOk.dat"));

		Configuration config = new Configuration();
		config.setProjectName("TEST");
		config.setServerUri("http://test");
		Storage storage = new RestStorageImpl(config);
		storage.store(new Value("id1", 1., "This is the message",
				IndicatorType.COUNT));
		storage.commit();
	}

	@Test
	public void testAutoIndicators() throws StorageException {

		// Mock retuned data
		Client.setInputStream(getClass().getResourceAsStream("returnOk.dat"));

		Configuration config = new Configuration();
		config.setProjectName("TEST");
		config.setServerUri("http://test");
		config.setProjectKeys(Arrays.asList((TechnicalIndicator) new Value("id1",
				1., IndicatorType.COUNT)));
		Storage storage = new RestStorageImpl(config);
		storage.commit();
	}
}
