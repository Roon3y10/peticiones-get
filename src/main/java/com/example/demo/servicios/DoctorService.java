package com.example.demo.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entidad.Doctor;
import com.example.demo.entidad.Especialidad;
import com.example.demo.repositorio.IDoctorRepository;
import com.example.demo.repositorio.IEspecialidadRepository;

@Service
public class DoctorService {

	@Autowired
	IDoctorRepository rdoctor;
	@Autowired
	IEspecialidadRepository respecialidad;

	public List<Doctor> getAll() {
		return (List<Doctor>) rdoctor.findAll();
	}

	public Boolean SaveOrUpate(Doctor doctor) {
		try {
			rdoctor.save(doctor);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public Boolean Delete(Doctor Entity) {
		try {
			rdoctor.delete(Entity);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public Especialidad getEspecialidad(Integer id) {
		return respecialidad.findById(id).get();
	}

	public Doctor getDoctor(Integer id) {
		return rdoctor.findById(id).get();
	}
}
