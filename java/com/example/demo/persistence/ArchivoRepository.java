package com.example.demo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Archivo;

@Repository
public interface ArchivoRepository extends JpaRepository<Archivo, Integer> {

}
