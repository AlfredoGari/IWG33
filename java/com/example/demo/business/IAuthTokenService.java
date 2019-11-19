package com.example.demo.business;

import com.example.demo.model.AuthToken;

public interface IAuthTokenService {
	public AuthToken save(AuthToken at) throws BusinessException;

	public AuthToken load(String series) throws BusinessException, NotFoundException;

	public void delete(AuthToken at) throws BusinessException;
	
	public void purgeTokens() throws BusinessException;

}
