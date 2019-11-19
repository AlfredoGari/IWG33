package com.example.demo.business;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Archivo;
import com.example.demo.persistence.ArchivoRepository;

@Service
public class ArchivoBusiness implements IArchivoBusiness {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ArchivoRepository archivoDAO;
	
	
	@Override
	public Archivo load(int id) throws BusinessException, NotFoundException {
		Optional<Archivo> op = null;
		try {
			op = archivoDAO.findById(id);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		if (!op.isPresent())
			throw new NotFoundException("No se encuentra el archivo con id=" + id);
		return op.get();
	}

	@Override
	public Archivo save(Archivo archivo) throws BusinessException {
		try {
			return archivoDAO.save(archivo);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public void delete(int id) throws BusinessException, NotFoundException {
		Optional<Archivo> op = null;

		try {
			op = archivoDAO.findById(id);
		} catch (Exception e) {
			throw new BusinessException(e);
		}

		if (!op.isPresent())
			throw new NotFoundException("No se encuentra el archivo con id=" + id);
		try {
			archivoDAO.deleteById(id);
		} catch (Exception e) {
			throw new BusinessException(e);
		}

	}

	@Override
	public List<Archivo> list() throws BusinessException {
		try {
			return archivoDAO.findAll();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BusinessException(e);
		}
	}

	@Override
	public Archivo upload(MultipartFile file) throws BusinessException {
		Archivo archivo=new Archivo();
		try {
			archivo.setContenido(file.getBytes());
		} catch (IOException e) {
			log.error(e.getMessage(),e);
			throw new BusinessException(e.getMessage(),e);
		}
		archivo.setLength(archivo.getContenido().length);
		archivo.setFecha(new Date());
		archivo.setNombre(StringUtils.cleanPath(file.getOriginalFilename()));
		archivo.setMime(file.getContentType());
		return save(archivo);
	}

}
