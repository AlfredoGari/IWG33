package com.example.demo.business;

import java.util.List;

import com.example.demo.model.Producto;

public interface IProductoBusiness {

	public List<Producto> list() throws BusinessException;

	public Producto load(int idProducto) throws BusinessException, NotFoundException;

	public Producto save(Producto producto) throws BusinessException;

	public void remove(int idProducto) throws BusinessException, NotFoundException;

}
