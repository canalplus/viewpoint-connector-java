package fr.viewpoint.client.storage;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import org.junit.Test;

import fr.viewpoint.client.Configuration;
import fr.viewpoint.client.Indicator;
import fr.viewpoint.client.IndicatorType;
import fr.viewpoint.client.Value;
import fr.viewpoint.client.storage.impl.DevNullStorageImpl;

public class WebcareDevNullStorageImplTest {

	@Test
	public void testOk() throws StorageException {

		Configuration config = new Configuration();
		final Storage storage = new DevNullStorageImpl(config);

		// Store an indicator
		storage.store(new Value("id1", 1., IndicatorType.COUNT));
		Assert.assertEquals(true, true);

		// with user
		storage.store(new Value("id1", 1., IndicatorType.COUNT), "jacques");
		Assert.assertEquals(true, true);

		// Store a list of indicator
		List<Indicator> list = new ArrayList<Indicator>();
		list.add(new Value("id1", 1., IndicatorType.COUNT));
		list.add(new Value("id2", 1., IndicatorType.COUNT));
		storage.store(list);
		Assert.assertEquals(true, true);

		// with user
		storage.store(list, "bobo");
		Assert.assertEquals(true, true);

		// with nothing
		storage.store(new ArrayList<Indicator>());
		Assert.assertEquals(true, true);

	}
}
