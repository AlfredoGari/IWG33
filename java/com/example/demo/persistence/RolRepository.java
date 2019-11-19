package com.example.demo.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {

	public List<Rol> findByRol(String rol); 
}
