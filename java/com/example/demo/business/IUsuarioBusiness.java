package com.example.demo.business;

import com.example.demo.model.Usuario;

public interface IUsuarioBusiness {

	public Usuario load(String username) throws BusinessException, NotFoundException;
}
