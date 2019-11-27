package com.example.demo.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entidad.Especialidad;

@Repository
public interface IEspecialidadRepository extends CrudRepository<Especialidad, Integer>{

}
