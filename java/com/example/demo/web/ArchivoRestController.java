package com.example.demo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.business.BusinessException;
import com.example.demo.business.IArchivoBusiness;
import com.example.demo.business.NotFoundException;
import com.example.demo.model.Archivo;

@RestController
@RequestMapping(Constantes.URL_BASE_ARCHIVOS)
public class ArchivoRestController extends BaseRestController{

	@Autowired
	private IArchivoBusiness archivoBusiness;

	@GetMapping("")
	public ResponseEntity<List<Archivo>> list() {
		try {
			return new ResponseEntity<List<Archivo>>(archivoBusiness.list(), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<List<Archivo>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping(value = "")
	public ResponseEntity<String> update(@RequestBody Archivo archivo) {
		try {
			archivoBusiness.save(archivo);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") int id) {
		try {
			archivoBusiness.delete(id);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(value = "")
	public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
		try {
			Archivo archivo = archivoBusiness.upload(file);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("location", Constantes.URL_BASE_ARCHIVOS + "/" + archivo.getId());
			return new ResponseEntity<String>(responseHeaders, HttpStatus.CREATED);
		} catch (BusinessException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<byte[]> download(@PathVariable("id") int id) {
		try {
			Archivo archivo = archivoBusiness.load(id);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.setContentType(MediaType.parseMediaType(archivo.getMime()));
			responseHeaders.add(HttpHeaders.CONTENT_DISPOSITION,
					"attachment; filename=\"" + archivo.getNombre() + "\"");
			return new ResponseEntity<byte[]>(archivo.getContenido(), responseHeaders, HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);
		}
	}

}
