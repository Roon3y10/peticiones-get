package com.example.demo.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entidad.Doctor;

@Repository
public interface IDoctorRepository extends CrudRepository<Doctor, Integer>{

}
