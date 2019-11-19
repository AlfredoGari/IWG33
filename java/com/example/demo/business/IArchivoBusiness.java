package com.example.demo.business;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Archivo;

public interface IArchivoBusiness {

	public Archivo load(int id) throws BusinessException, NotFoundException;

	public Archivo save(Archivo archivo) throws BusinessException;

	public void delete(int id) throws BusinessException, NotFoundException;

	public List<Archivo> list() throws BusinessException;

	public Archivo upload(MultipartFile file) throws BusinessException;

}
