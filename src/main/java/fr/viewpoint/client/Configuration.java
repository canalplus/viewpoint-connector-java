package fr.viewpoint.client;

import java.util.List;

import com.rabbitmq.client.ConnectionFactory;

import fr.viewpoint.client.StorageFactory.StorageType;
import fr.viewpoint.utils.rest.Config;

/**
 * 
 * 
 * Rest
 * 
 * serverUri : The Webcare REST API<br>
 * projectName : The project name<br>
 * 
 * Live<br>
 * 
 * liveServerHost : The RabbitMQ webcare server<br>
 * liveServerPort : The RabbitMQ webcare port<br>
 * liveQueueName : The queue name<br>
 * liveProjectName : The project name<br>
 * liveUser : The username <br>
 * livePassword : The password <br>
 * liveConnectionTimeout : the connection time out<br>
 * 
 * @author jpoirier
 * 
 */
public class Configuration {

	/**
	 * Rest CONFIGURATION
	 */

	/**
	 * The Webcare REST API
	 */
	private String serverUri;

	/**
	 * The Webcare REST API Config
	 */
	private Config restConfig;

	/**
	 * The project name
	 */
	private String projectName;

	/**
	 * The indicator list that should be generated at each commit
	 */
	private List<TechnicalIndicator> projectKeys;

	private String liveServerHost;
	private int liveServerPort;
	private String liveExchangeName;
	private String liveProjectName;
	private String liveUserName = ConnectionFactory.DEFAULT_USER;
	private String livePassword = ConnectionFactory.DEFAULT_PASS;
	private int liveConnectionTimeout = ConnectionFactory.DEFAULT_CONNECTION_TIMEOUT;
	private boolean disableHeartBeat = false;

	/**
	 * The type of storage to use
	 */
	private StorageType storageType;

	/**
	 * @return the serverUri
	 */
	public String getServerUri() {
		return serverUri;
	}

	/**
	 * @param serverUri
	 *            the serverUri to set
	 */
	public void setServerUri(String serverUri) {
		this.serverUri = serverUri;
	}

	/**
	 * @return the projectName
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * @param projectName
	 *            the projectName to set
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/**
	 * @return the liveServerHost
	 */
	public String getLiveServerHost() {
		return liveServerHost;
	}

	/**
	 * @param liveServerHost
	 *            the liveServerHost to set
	 */
	public void setLiveServerHost(String liveServerHost) {
		this.liveServerHost = liveServerHost;
	}

	/**
	 * @return the liveServerPort
	 */
	public int getLiveServerPort() {
		return liveServerPort;
	}

	/**
	 * @param liveServerPort
	 *            the liveServerPort to set
	 */
	public void setLiveServerPort(int liveServerPort) {
		this.liveServerPort = liveServerPort;
	}

	/**
	 * @return the liveExchangeName
	 */
	public String getLiveExchangeName() {
		return liveExchangeName;
	}

	/**
	 * @param liveExchangeName
	 *            the liveExchangeName to set
	 */
	public void setLiveExchangeName(String liveExchangeName) {
		this.liveExchangeName = liveExchangeName;
	}

	/**
	 * @return the liveProjectName
	 */
	public String getLiveProjectName() {
		return liveProjectName;
	}

	/**
	 * @param liveProjectName
	 *            the liveProjectName to set
	 */
	public void setLiveProjectName(String liveProjectName) {
		this.liveProjectName = liveProjectName;
	}

	/**
	 * @return the projectKeys
	 */
	public List<TechnicalIndicator> getProjectKeys() {
		return projectKeys;
	}

	/**
	 * @param projectKeys
	 *            The indicator list that should be generated at each commit
	 */
	public void setProjectKeys(List<TechnicalIndicator> projectKeys) {
		this.projectKeys = projectKeys;
	}

	/**
	 * @return the disableHeartBeat
	 */
	public boolean isDisableHeartBeat() {
		return disableHeartBeat;
	}

	/**
	 * @param disableHeartBeat
	 *            the disableHeartBeat to set
	 */
	public void setDisableHeartBeat(boolean disableHeartBeat) {
		this.disableHeartBeat = disableHeartBeat;
	}

	/**
	 * @return the restConfig
	 */
	public Config getRestConfig() {
		return restConfig;
	}

	/**
	 * @param restConfig
	 *            the restConfig to set
	 */
	public void setRestConfig(Config restConfig) {
		this.restConfig = restConfig;
	}

	/**
	 * @return the livePassword
	 */
	public String getLivePassword() {
		return livePassword;
	}

	/**
	 * @param livePassword
	 *            the livePassword to set
	 */
	public void setLivePassword(String livePassword) {
		this.livePassword = livePassword;
	}

	/**
	 * @return the liveConnectionTimeout
	 */
	public int getLiveConnectionTimeout() {
		return liveConnectionTimeout;
	}

	/**
	 * @param liveConnectionTimeout
	 *            the liveConnectionTimeout to set
	 */
	public void setLiveConnectionTimeout(int liveConnectionTimeout) {
		this.liveConnectionTimeout = liveConnectionTimeout;
	}

	/**
	 * @return the liveUser
	 */
	public String getLiveUsername() {
		return liveUserName;
	}

	/**
	 * @param liveUser
	 *            the liveUser to set
	 */
	public void setLiveUsername(String liveUser) {
		this.liveUserName = liveUser;
	}

	/**
	 * @return the storageType
	 */
	public StorageType getStorageType() {
		return storageType;
	}

	/**
	 * @param storageType
	 *            the storageType to set
	 */
	public void setStorageType(StorageType storageType) {
		this.storageType = storageType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (disableHeartBeat ? 1231 : 1237);
		result = prime * result + liveConnectionTimeout;
		result = prime
				* result
				+ ((liveExchangeName == null) ? 0 : liveExchangeName.hashCode());
		result = prime * result
				+ ((livePassword == null) ? 0 : livePassword.hashCode());
		result = prime * result
				+ ((liveProjectName == null) ? 0 : liveProjectName.hashCode());
		result = prime * result
				+ ((liveServerHost == null) ? 0 : liveServerHost.hashCode());
		result = prime * result + liveServerPort;
		result = prime * result
				+ ((liveUserName == null) ? 0 : liveUserName.hashCode());
		result = prime * result
				+ ((projectKeys == null) ? 0 : projectKeys.hashCode());
		result = prime * result
				+ ((projectName == null) ? 0 : projectName.hashCode());
		result = prime * result
				+ ((restConfig == null) ? 0 : restConfig.hashCode());
		result = prime * result
				+ ((serverUri == null) ? 0 : serverUri.hashCode());
		result = prime * result
				+ ((storageType == null) ? 0 : storageType.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Configuration other = (Configuration) obj;
		if (disableHeartBeat != other.disableHeartBeat)
			return false;
		if (liveConnectionTimeout != other.liveConnectionTimeout)
			return false;
		if (liveExchangeName == null) {
			if (other.liveExchangeName != null)
				return false;
		} else if (!liveExchangeName.equals(other.liveExchangeName))
			return false;
		if (livePassword == null) {
			if (other.livePassword != null)
				return false;
		} else if (!livePassword.equals(other.livePassword))
			return false;
		if (liveProjectName == null) {
			if (other.liveProjectName != null)
				return false;
		} else if (!liveProjectName.equals(other.liveProjectName))
			return false;
		if (liveServerHost == null) {
			if (other.liveServerHost != null)
				return false;
		} else if (!liveServerHost.equals(other.liveServerHost))
			return false;
		if (liveServerPort != other.liveServerPort)
			return false;
		if (liveUserName == null) {
			if (other.liveUserName != null)
				return false;
		} else if (!liveUserName.equals(other.liveUserName))
			return false;
		if (projectKeys == null) {
			if (other.projectKeys != null)
				return false;
		} else if (!projectKeys.equals(other.projectKeys))
			return false;
		if (projectName == null) {
			if (other.projectName != null)
				return false;
		} else if (!projectName.equals(other.projectName))
			return false;
		if (restConfig == null) {
			if (other.restConfig != null)
				return false;
		} else if (!restConfig.equals(other.restConfig))
			return false;
		if (serverUri == null) {
			if (other.serverUri != null)
				return false;
		} else if (!serverUri.equals(other.serverUri))
			return false;
		if (storageType != other.storageType)
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "WcConfiguration [serverUri=" + serverUri + ", restConfig="
				+ restConfig + ", projectName=" + projectName
				+ ", projectKeys=" + projectKeys + ", liveServerHost="
				+ liveServerHost + ", liveServerPort=" + liveServerPort
				+ ", liveExchangeName=" + liveExchangeName
				+ ", liveProjectName=" + liveProjectName + ", liveUserName="
				+ liveUserName + ", livePassword=" + livePassword
				+ ", liveConnectionTimeout=" + liveConnectionTimeout
				+ ", disableHeartBeat=" + disableHeartBeat + ", storageType="
				+ storageType + "]";
	}
}
