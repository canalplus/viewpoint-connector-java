package fr.viewpoint.client.storage.impl.rest;

/**
 * API Rest POJO
 * 
 * @author UFO
 * 
 */
public class AggregateDetail {
	private String id;
	private long count;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the count
	 */
	public long getCount() {
		return count;
	}

	/**
	 * @param count
	 *            the count to set
	 */
	public void setCount(long count) {
		this.count = count;
	}

	public AggregateDetail() {
		super();
	}

	public AggregateDetail(String id, long count) {
		super();
		this.id = id;
		this.count = count;
	}
}
