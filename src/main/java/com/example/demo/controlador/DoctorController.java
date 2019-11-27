package com.example.demo.controlador;

import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entidad.Doctor;
import com.example.demo.repositorio.IDoctorRepository;
import com.example.demo.servicios.DoctorService;

@Controller
@RequestMapping("doctores") // nombre del controlador//
public class DoctorController {

	// repositorio para manipular los datos de la base//
	@Autowired
	DoctorService doctorService;

	@GetMapping(value = "all", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Doctor> getAllDoctors() {
		return (List<Doctor>) doctorService.getAll();
	}

	// METODOD ALMACENAR ELEMENTOS COMO UNA ARREGLO//
	@GetMapping(value = "save")
	@ResponseBody
	public HashMap<String, String> save(@RequestParam String nombre,
			@RequestParam String direccion, @RequestParam Integer idEspecialidad) {
		Doctor doctor = new Doctor();// se crea el objeto doctor

		HashMap<String, String> jsonReturn = new HashMap<>();
		// asignado datos al objeto de doctor

		doctor.setNombre(nombre);
		doctor.setDireccion(direccion);
		doctor.setEspecialidad(doctorService.getEspecialidad(idEspecialidad));

		// manejando cualquier excepcion de error

		try {
			doctorService.SaveOrUpate(doctor);// guardando registro de doctor
			jsonReturn.put("estado", "OK");
			jsonReturn.put("mensaje", "Registro guardado");

			return jsonReturn;
		} catch (Exception e) {
			jsonReturn.put("estado", "ERROR");
			jsonReturn.put("mensaje", "Registro no guardado" + e.getMessage());
			return jsonReturn;
		}
	}

//Eliminar
	@GetMapping(value = "delete/{id}")
	@ResponseBody
	public HashMap<String, String> delete(@PathVariable Integer id) {

		HashMap<String, String> jsonReturn = new HashMap<>();

		try {
			// buscando registro
			Doctor doctor = doctorService.getDoctor(id);
			// eliminando registro
			doctorService.Delete(doctor);
			jsonReturn.put("estado", "OK");
			jsonReturn.put("mensaje", "Registro eliminado");
			return jsonReturn;

		} catch (Exception e) {
			jsonReturn.put("estado", "ERROR");
			jsonReturn.put("mensaje", "Registro no guardado" + e.getMessage());
			return jsonReturn;
		}
	}

//actualizar
	@GetMapping(value = "update/{id}")
	@ResponseBody
	public HashMap<String, String> update( @RequestParam Integer id, @RequestParam String nombre,
			@RequestParam String direccion, @RequestParam Integer idEspecialidad) {
		Doctor doctor = new Doctor();// creando objeto de doctor

		HashMap<String, String> jsonReturn = new HashMap<>();
		// asignado datos al objeto de doctor
		doctor.setId(id);
		doctor.setNombre(nombre);
		doctor.setDireccion(direccion);
		doctor.setEspecialidad(doctorService.getEspecialidad(idEspecialidad));

		// manejando cualquier excepcion de error
		try {
			doctorService.SaveOrUpate(doctor);// guardando registro de doctor
			jsonReturn.put("estado", "OK");
			jsonReturn.put("mensaje", "Registro actualizado");
			return jsonReturn;

		} catch (Exception e) {
			jsonReturn.put("estado", "ERROR");
			jsonReturn.put("mensaje", "Registro no actualizado" + e.getMessage());
			return jsonReturn;
		}
	}
}
