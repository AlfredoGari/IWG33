package com.example.demo.business;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Producto;
import com.example.demo.persistence.ProductoRepository;

@Service
public class ProductoBusiness implements IProductoBusiness {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ProductoRepository productoDAO;

	public ProductoBusiness() {
	}

	@Override
	public List<Producto> list() throws BusinessException {
		try {
			return productoDAO.findAll();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BusinessException(e);
		}

	}

	@Override
	public Producto save(Producto producto) throws BusinessException {
		try {
			return productoDAO.save(producto);
		} catch (Exception e) {
			throw new BusinessException(e);
		}

	}

	@Override
	public void remove(int idProducto) throws BusinessException, NotFoundException {
		Optional<Producto> op = null;

		try {
			op = productoDAO.findById(idProducto);
		} catch (Exception e) {
			throw new BusinessException(e);
		}

		if (!op.isPresent())
			throw new NotFoundException("No se encuentra el producto con id=" + idProducto);
		try {
			productoDAO.deleteById(idProducto);
		} catch (Exception e) {
			throw new BusinessException(e);
		}

	}

	@Override
	public Producto load(int idProducto) throws BusinessException, NotFoundException {
		Optional<Producto> op = null;
		try {
			op = productoDAO.findById(idProducto);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		if (!op.isPresent())
			throw new NotFoundException("No se encuentra el producto con id=" + idProducto);
		return op.get();

	}

}
