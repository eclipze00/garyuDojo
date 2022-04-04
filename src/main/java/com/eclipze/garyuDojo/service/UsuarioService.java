package com.eclipze.garyuDojo.service;


import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.eclipze.garyuDojo.model.Usuario;
import com.eclipze.garyuDojo.model.UsuarioLogin;
import com.eclipze.garyuDojo.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	private String criptSenha (String senha) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(senha);
	}
	
	private boolean compararSenhas(String senhaDigitada, String senhaBanco) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		return encoder.matches(senhaDigitada, senhaBanco);
	}
	
	private String gerarBasicToken (String usuario, String senha) {
		String token = usuario + ":" + senha;
		byte[] tokenBase64 = Base64.encodeBase64(token.getBytes(Charset.forName("US-ASCII")));
		return "Basic " + new String (tokenBase64);
	}
	
	/**
	 * Cadastro de usuario
	 * @param email
	 * @return
	 */
	public Optional<Usuario> cadastrarUsuario(Usuario email){
		if (usuarioRepository.findByEmail(email.getEmail()).isPresent())
			return Optional.empty();
		
		email.setSenha(criptSenha(email.getSenha()));
		
		return Optional.of(usuarioRepository.save(email));
	}
	
	public Optional<Usuario> atualizarUsuario(Usuario email){
		if (usuarioRepository.findByEmail(email.getEmail()).isPresent()) {
			Optional<Usuario> buscarUsuario = usuarioRepository.findByEmail(email.getEmail());
			
			if((buscarUsuario.isPresent()) && (buscarUsuario.get().getId() != email.getId()))
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já Existe!", null);
			
			email.setSenha(criptSenha(email.getSenha()));
			
			return Optional.ofNullable(usuarioRepository.save(email));
		}
		
		return Optional.empty();
	}
	
	public Optional<UsuarioLogin> login (Optional<UsuarioLogin> emailLogin){
		
		Optional<Usuario> usuario = usuarioRepository.findByEmail(emailLogin.get().getEmail());
		
		if(usuario.isPresent()) {
			
			if(compararSenhas (emailLogin.get().getSenha(), usuario.get().getSenha())) {
				emailLogin.get().setId(usuario.get().getId());
				emailLogin.get().setNome(usuario.get().getNome());
				emailLogin.get().setFoto(usuario.get().getFoto());
				emailLogin.get().setToken(gerarBasicToken(emailLogin.get().getEmail(), emailLogin.get().getSenha()));
				emailLogin.get().setSenha(usuario.get().getSenha());
				
				return emailLogin;
			}
		}
		
		return Optional.empty();
	}

}
