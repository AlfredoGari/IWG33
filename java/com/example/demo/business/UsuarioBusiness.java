package com.example.demo.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Usuario;
import com.example.demo.persistence.UsuarioRepository;


@Service
public class UsuarioBusiness implements IUsuarioBusiness {


	@Autowired
	private UsuarioRepository usuarioDAO;
	
	@Override
	public Usuario load(String username) throws BusinessException, NotFoundException {
		List<Usuario> ou;
		try {
			ou=usuarioDAO.findByUsernameOrEmail(username, username);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		if(ou.size()==0)
			throw new NotFoundException("No se encuentra el usuario con nombre o email "+username);
		return ou.get(0);
	}

}
