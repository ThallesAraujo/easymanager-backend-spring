package com.thalles.easymanager.cors;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter{
	
	//TODO: criar uma página simples para realizar os testes feitos no curso
	
	private String origemPermitida = "http://localhost:8000"; //TODO: Configurar para diferentes ambientes (produção, testes...)

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		
		//os headers a seguir estão fora da condicional por necessitarem serem enviados em todas as requisições,
		//independentemente do método HTTP que seja chamado
		response.setHeader("Access-Control-Allow-Origin", origemPermitida);
		
		//Suporte ao Cookie do RefreshToken
		response.setHeader("Access-Control-Allow-Credentials", "true");
		
		if(request.getMethod().equals("OPTIONS") && request.getHeader("Origin").equals(origemPermitida)) {
			
			response.setHeader("Access-Control-Allow-Methods", "GET, PUT, DELETE, POST, OPTIONS");
			response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, Accept");
			response.setHeader("Access-Control-Max-Age", "3600");
			
			response.setStatus(HttpServletResponse.SC_OK);
			
		}else {
			chain.doFilter(req, resp);
		}
		
	}
	
	@Override
	public void destroy() {
	}


	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
