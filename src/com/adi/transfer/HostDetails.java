package com.adi.transfer;



public class HostDetails {

	private static final String LOCALHOST = "127.0.0.1";
	private static final int HTTP_PORT = 80;



	private String ip;
	private int port;
	
	
	
	public HostDetails() {
		
		setIP(LOCALHOST);
		setPort(HTTP_PORT);
	}
	
	
	
	public HostDetails(String ip) {
		
		setIP(ip);
		setPort(HTTP_PORT);
	}
	
	
	
	public HostDetails(int port) {
	
		setIP(LOCALHOST);
		setPort(port);
	} 


	public HostDetails(String ip, int port) {
		
		setIP(ip);
		setPort(port);
	}
	
	
	
	public void setIP(String ip) {
	
		this.ip = ip;
	}
	
	
	
	public void setPort(int port) {
	
		this.port = port;
	}
	
	
	
	public String getIP() {
	
		return ip;
	}
	
	
	
	public int getPort() {
	
		return port;
	}
	
	
	
	@Override
	public String toString() {
	
		return String.format("%s:%d", ip, port);
	}
}
