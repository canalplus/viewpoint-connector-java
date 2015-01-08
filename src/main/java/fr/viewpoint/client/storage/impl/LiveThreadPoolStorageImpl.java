package fr.viewpoint.client.storage.impl;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import fr.viewpoint.client.Configuration;
import fr.viewpoint.client.Indicator;
import fr.viewpoint.client.LiveWrapper;
import fr.viewpoint.client.storage.Storage;

public class LiveThreadPoolStorageImpl implements Storage {

	private LiveStorageImpl storage = null;

	/**
	 * The storage
	 */
	private ExecutorService threadStoragePool = null;

	/**
	 * Default constructor
	 * 
	 * @param config
	 */
	public LiveThreadPoolStorageImpl(Configuration config,
			int maxThreadNumber) {
		storage = new LiveStorageImpl(config);
		threadStoragePool = Executors.newFixedThreadPool(maxThreadNumber);
	}

	public void store(final Indicator indicator, final String userId) {
		threadStoragePool.execute(new Runnable() {
			public void run() {
				storage.store(indicator, userId);
			}
		});
	}

	public void store(final List<Indicator> indicators, final String userId) {
		threadStoragePool.execute(new Runnable() {
			public void run() {
				storage.store(indicators);
			}
		});
	}

	public void store(final List<Indicator> indicators,
			final LiveWrapper liveWrapper) {
		threadStoragePool.execute(new Runnable() {
			public void run() {
				storage.store(indicators, liveWrapper);
			}
		});
	}

	public void store(final Indicator indicator) {
		threadStoragePool.execute(new Runnable() {
			public void run() {
				storage.store(indicator);
			}
		});
	}

	public void store(final List<Indicator> indicators) {
		threadStoragePool.execute(new Runnable() {
			public void run() {
				storage.store(indicators);
			}
		});
	}

	public void rawStore(final String rawContent) {
		threadStoragePool.execute(new Runnable() {
			public void run() {
				storage.rawStore(rawContent);
			}
		});
	}

	public void rawStore(final List<String> rawContent) {
		threadStoragePool.execute(new Runnable() {
			public void run() {
				storage.rawStore(rawContent);
			}
		});
	}

	public void commit() {
		storage.commit();

	}

	public void close() {
		storage.close();
	}
}
