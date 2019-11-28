package com.example.demo.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entidad.Paciente;

@Repository
public interface IPacienteRepository extends CrudRepository<Paciente, Integer>{

}
