package com.example.demo.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entidad.Consulta;
import com.example.demo.entidad.Doctor;
import com.example.demo.repositorio.IConsultaRepository;
import com.example.demo.repositorio.IDoctorRepository;

@Service
public class ConsultaService {
	
	@Autowired
	IDoctorRepository rdoctor;
	
	@Autowired
	IConsultaRepository rconsulta;
	
	public List<Consulta> getAll(){
		return (List<Consulta>) rconsulta.findAll();
	}
	
	
	public Boolean SaveOrUpate(Consulta consulta) {
	try {
		rconsulta.save(consulta);
		return true;
	}catch (Exception e) {
		return  false;
	}
	
	}
	
	public Boolean Delete(Consulta Entity) {
		try {
			rconsulta.delete(Entity);
			return true;
		}catch (Exception e) {
			return  false;
		}
		
		}
	public Consulta getConsulta(Integer id) {
		return rconsulta.findById(id).get();
	}

	public Doctor getDoctor(Integer id) {
		return rdoctor.findById(id).get();
	}	
}
