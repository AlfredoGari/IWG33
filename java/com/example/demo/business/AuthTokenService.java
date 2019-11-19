package com.example.demo.business;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.AuthToken;
import com.example.demo.persistence.AuthTokenRespository;

@Service
public class AuthTokenService implements IAuthTokenService {

	@Autowired
	private AuthTokenRespository authTokenDAO;

	@Override
	public AuthToken save(AuthToken at) throws BusinessException {
		try {
			return authTokenDAO.save(at);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public AuthToken load(String series) throws BusinessException, NotFoundException {
		Optional<AuthToken> atO;
		try {
			atO = authTokenDAO.findById(series);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		if (!atO.isPresent())
			throw new NotFoundException("No se encuentra el token de autenticación serie=" + series);
		return atO.get();
	}

	@Override
	public void delete(AuthToken at) throws BusinessException {
		try {
			authTokenDAO.delete(at);
		} catch (Exception e) {
			// throw new ServiceException(e);
		}

	}

	@Override
	public void purgeTokens() throws BusinessException {
		try {
			authTokenDAO.purgeToDate(new Date());
			authTokenDAO.purgeDefault(new Date());
			authTokenDAO.purgeFromToDate(new Date());
			authTokenDAO.purgeRequestLimit();
		} catch (Exception e) {
			throw new BusinessException(e);
		}

	}

}
