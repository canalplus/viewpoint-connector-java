package fr.viewpoint.client;

import org.junit.Assert;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import fr.viewpoint.client.StorageFactory.StorageType;
import fr.viewpoint.client.storage.Storage;
import fr.viewpoint.client.storage.impl.DevNullStorageImpl;
import fr.viewpoint.client.storage.impl.LiveStorageImpl;

public class WcStorageFactoryTest {

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Test
	public void testOldWay() {

		Configuration configuration = new Configuration();
		StorageFactory.init(configuration);
		StorageFactory.setType(StorageType.NULL);
		Storage s1 = StorageFactory.get();

		Assert.assertNotNull(s1);
		Assert.assertEquals(DevNullStorageImpl.class.getCanonicalName(),
				s1.getClass().getCanonicalName());

		Configuration configuration2 = new Configuration();
		configuration2.setLiveExchangeName("b");
		configuration2.setLivePassword("t");
		configuration2.setLiveProjectName("p");
		configuration2.setLiveServerHost("a");
		configuration2.setLiveUsername("a");
		StorageFactory.init(configuration2);
		StorageFactory.setType(StorageType.LIVE);
		Storage s2 = StorageFactory.get();

		Assert.assertNotNull(s2);
		Assert.assertEquals(LiveStorageImpl.class.getCanonicalName(), s2
				.getClass().getCanonicalName());
	}

	@Test
	public void testWithoutType() {

		expectedEx.expect(RuntimeException.class);
		expectedEx
				.expectMessage("Unable to create a storage without configuration");

		StorageFactory.get(null);

		expectedEx.expect(RuntimeException.class);
		expectedEx
				.expectMessage("Unable to create a storage without storage type");

		Configuration configuration = new Configuration();
		StorageFactory.get(configuration);

	}

	@Test
	public void testMultiInstances() {

		Configuration configuration0 = new Configuration();
		configuration0.setLiveExchangeName("b");
		configuration0.setLivePassword("t");
		configuration0.setLiveProjectName("p");
		configuration0.setLiveServerHost("a");
		configuration0.setLiveUsername("b");
		configuration0.setStorageType(StorageType.LIVE);

		Configuration configuration1 = new Configuration();
		configuration1.setLiveExchangeName("b");
		configuration1.setLivePassword("t");
		configuration1.setLiveProjectName("p");
		configuration1.setLiveServerHost("a");
		configuration1.setLiveUsername("a");
		configuration1.setStorageType(StorageType.LIVE);

		Configuration configuration2 = new Configuration();
		configuration2.setLiveExchangeName("b");
		configuration2.setLivePassword("t");
		configuration2.setLiveProjectName("p");
		configuration2.setLiveServerHost("a");
		configuration2.setLiveUsername("b");
		configuration2.setStorageType(StorageType.LIVE);

		Storage s0 = StorageFactory.get(configuration0);
		Storage s1 = StorageFactory.get(configuration1);
		Storage s2 = StorageFactory.get(configuration2);

		Assert.assertNotNull(s0);
		Assert.assertNotNull(s1);
		Assert.assertNotNull(s2);

		Assert.assertNotSame(s0, s1);
		Assert.assertEquals(s0, s2);

	}

}
