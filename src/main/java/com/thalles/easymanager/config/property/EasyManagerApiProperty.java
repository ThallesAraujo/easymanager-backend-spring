package com.thalles.easymanager.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("easymanager")
public class EasyManagerApiProperty {
	
	private String origemPermitida = "http://localhost:8000";
	
	public String getOrigemPermitida() {
		return origemPermitida;
	}

	public void setOrigemPermitida(String origemPermitida) {
		this.origemPermitida = origemPermitida;
	}

	private final Seguranca seguranca = new Seguranca();
	
	public Seguranca getSeguranca() {
		return seguranca;
	}

	public static class Seguranca{
		
		private boolean enableHttp;

		public boolean isEnableHttp() {
			return enableHttp;
		}

		public void setEnableHttp(boolean enableHttp) {
			this.enableHttp = enableHttp;
		}
		
		
		
	}

}
