package com.thalles.easymanager.event.listener;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.thalles.easymanager.event.RecursoCriadoEvent;

@Component
public class RecursoCriadoListener implements ApplicationListener<RecursoCriadoEvent> {

	@Override
	public void onApplicationEvent(RecursoCriadoEvent event) {
		HttpServletResponse resposta = event.getResponse();
		Long codigo = event.getCodigo();
		
		adicionarHeaderLocation(resposta, codigo);
		
	}

	private void adicionarHeaderLocation(HttpServletResponse resposta, Long codigo) {
		//informar no header da requisição a URI de localização (usada para recuperar) do recurso
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}").buildAndExpand(codigo).toUri();
		resposta.setHeader("Location", uri.toASCIIString());
	}

}
