package com.algaworks.algafood.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("notifier.email")
public class NotifierProperties {
	private String hostServer;
	private Integer serverPort;

	public String getHostServer() {
		return hostServer;
	}
	public void setHostServer(String hostServer) {
		this.hostServer = hostServer;
	}
	public Integer getServerPort() {
		return serverPort;
	}
	public void setServerPort(Integer serverPort) {
		this.serverPort = serverPort;
	}
}