package com.example.demo.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entidad.Consulta;

@Repository
public interface IConsultaRepository extends CrudRepository<Consulta, Integer>{

}
