package fr.viewpoint.client;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.viewpoint.client.storage.Storage;
import fr.viewpoint.client.storage.impl.DevNullStorageImpl;
import fr.viewpoint.client.storage.impl.LiveStorageImpl;
import fr.viewpoint.client.storage.impl.RestStorageImpl;

/**
 * Manage storage providers
 * 
 * @author UFO
 * 
 */
public class StorageFactory {

	/**
	 * Log
	 */
	private static final Logger log = LoggerFactory
			.getLogger(StorageFactory.class);

	/**
	 * @deprecated old way to get storage
	 */
	private static boolean isInit = false;

	/**
	 * @deprecated old way to get storage
	 */
	private static Configuration config = null;

	/**
	 * Available storage type
	 * 
	 * @author jpoirier
	 * 
	 */
	public enum StorageType {
		REST, LIVE, NULL
	};

	/**
	 * @deprecated old way to get storage
	 */
	private static StorageType type = StorageType.NULL;

	/**
	 * The instances of storages
	 */
	private static Map<Configuration, Storage> storages = new LinkedHashMap<Configuration, Storage>();

	/**
	 * Initialize the storage factory
	 * 
	 * @deprecated Use get(WcConfiguration)
	 * @param config
	 */
	public static void init(Configuration configuration) {
		config = configuration;
		isInit = true;
	}

	/**
	 * Return the default indicator data storage provider
	 * 
	 * @deprecated Use get(WcConfiguration)
	 * @return
	 */
	public static Storage get() {

		if (!isInit) {
			throw new RuntimeException(
					"Storage factory should be initialized before calling get");
		}

		if (log.isInfoEnabled()) {
			log.info("Using the old get storage way doesn't manage multiple configuration. Consider to use the get(WcConfiguration)");
		}

		if (log.isDebugEnabled()) {
			log.debug("Returning a new instance of Storage");
		}

		config.setStorageType(type);
		return get(config);
	}

	/**
	 * Return the storage instance corresponding to the given configuration. If
	 * two configuration are equals the same instance of the storage will be
	 * returned
	 * 
	 * @param configuration
	 * @return
	 */
	public static Storage get(Configuration configuration) {
		if (null == configuration) {
			throw new RuntimeException(
					"Unable to create a storage without configuration");
		}

		if (null == configuration.getStorageType()) {
			throw new RuntimeException(
					"Unable to create a storage without storage type");
		}

		// Looking for an already existing instance
		for (Entry<Configuration, Storage> alreadyExistingConfig : storages
				.entrySet()) {
			if (configuration.equals(alreadyExistingConfig.getKey())) {
				return alreadyExistingConfig.getValue();
			}
		}

		// Create a new instance
		Storage storage = null;
		switch (configuration.getStorageType()) {
		case LIVE:
			storage = new LiveStorageImpl(configuration);
			break;
		case NULL:
			storage = new DevNullStorageImpl(configuration);
			break;
		case REST:
			storage = new RestStorageImpl(configuration);
			break;
		default:
			storage = new DevNullStorageImpl(configuration);
			break;
		}

		storages.put(configuration, storage);
		return storage;

	}

	/**
	 * @param type
	 *            the type to set
	 * 
	 * @deprecated Use get(WcConfiguration)
	 */
	public static void setType(StorageType type) {
		StorageFactory.type = type;
	}
}
