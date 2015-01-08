package fr.viewpoint.client.storage;

import java.util.List;

import fr.viewpoint.client.Indicator;
import fr.viewpoint.client.LiveWrapper;

/**
 * Manage to save indicator to Webcare System
 * 
 * @author UFO
 * 
 */
public interface Storage {

	/**
	 * Store the given indicator and wait for the next commit to send it to the
	 * server
	 * 
	 * @param indicator
	 *            The indicator to store
	 * @param userId
	 *            The attached userId
	 */
	public abstract void store(Indicator indicator, String userId);

	/**
	 * Store the given indicator list and wait for the next commit to send it to
	 * the server
	 * 
	 * @param indicators
	 *            The indicator list to store
	 * @param userId
	 *            The attached userId
	 */
	public abstract void store(List<Indicator> indicators, String userId);

	/**
	 * Store the given indicator list and wait for the next commit to send it to
	 * the server. The liveWrapper should be use to specify common data
	 * 
	 * @param indicators
	 *            The indicator list to store
	 * @param liveWrapper
	 *            The wrapper
	 */
	public abstract void store(List<Indicator> indicators,
			LiveWrapper liveWrapper);

	/**
	 * Store the given indicator and wait for the next commit to send it to the
	 * server
	 * 
	 * @param indicator
	 * @throws StorageException
	 */
	public abstract void store(Indicator indicator);

	/**
	 * Store a list of indicator
	 * 
	 * @param indicators
	 *            The indicator list to store
	 */
	public abstract void store(List<Indicator> indicators);

	/**
	 * In certain cases, the structure is already JSON encoded. This function
	 * allow users to use the connector as a proxy
	 * 
	 * @param rawContent
	 */
	public abstract void rawStore(String rawContent);

	/**
	 * In certain cases, the structure is already JSON encoded. This function
	 * allow users to use the connector as a proxy
	 * 
	 * @param rawContent
	 */
	public abstract void rawStore(List<String> rawContent);

	/**
	 * Send indicators througth the configurated interface
	 * 
	 * @throws StorageException
	 * 
	 */
	public void commit();

	/**
	 * Notify the storage to close
	 */
	public void close();
}