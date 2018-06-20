package com.thalles.easymanager.config.token;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import com.thalles.easymanager.model.Usuario;
import com.thalles.easymanager.repository.UsuarioRepository;


public class CustomTokenEnhancer implements TokenEnhancer {
	
	@Autowired
	private UsuarioRepository repositorio;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		
		Optional<Usuario> user = repositorio.findByEmail(authentication.getName());
		
		Map <String, Object> addInfo = new HashMap<>();
		
		addInfo.put("nome", user.get().getNome());
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(addInfo);
		return accessToken;
	}

}
