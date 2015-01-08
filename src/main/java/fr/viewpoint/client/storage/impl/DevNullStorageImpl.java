package fr.viewpoint.client.storage.impl;

import java.util.List;

import fr.viewpoint.client.Configuration;
import fr.viewpoint.client.Indicator;
import fr.viewpoint.client.LiveWrapper;
import fr.viewpoint.client.storage.Storage;

/**
 * Store nothing
 * 
 * @author jpoirier
 * 
 */
public class DevNullStorageImpl implements Storage {

	/**
	 * Default constructor
	 * 
	 * @param config
	 */
	public DevNullStorageImpl(Configuration config) {

	}

	public void store(Indicator indicator, String userId) {
		// Empty

	}

	public void store(List<Indicator> indicators, String userId) {
		// Empty

	}

	public void store(Indicator indicator) {
		// Empty

	}

	public void store(List<Indicator> indicators) {
		// Empty

	}

	public void commit() {
		// Empty

	}

	public void close() {
		return;
	}

	public void store(List<Indicator> indicators, LiveWrapper liveWrapper) {
		// TODO Auto-generated method stub
	}

	public void rawStore(String rawContent) {
		// TODO Auto-generated method stub

	}

	public void rawStore(List<String> rawContent) {
		// TODO Auto-generated method stub

	}

}
