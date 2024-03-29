package com.example.demo.controlador;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entidad.Especialidad;
import com.example.demo.repositorio.IEspecialidadRepository;

@Controller
@RequestMapping("especialidades") // nombre del controlador//
public class EspecialidadController {
	// repositorio para manipular los datos de la base//
	@Autowired
	IEspecialidadRepository respecialidad;

	@GetMapping(value="index")
    public String index() {
        return new String("views/Especialidad/especialidad");
    }
	
	@GetMapping(value = "all", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Especialidad> getAll() {
		return (List<Especialidad>) respecialidad.findAll();
	}

	@GetMapping(value = "save")
	@ResponseBody
	public HashMap<String, String> save(@RequestParam String especialidad) {
		Especialidad es = new Especialidad();// creando objeto de especialidad

		HashMap<String, String> jsonReturn = new HashMap<>();
		// asignado datos al objeto de especialidad
		es.setEspecialidad(especialidad);

		// manejando cualquier excepcion de error
		try {
			respecialidad.save(es);// guardando registro de doctor
			jsonReturn.put("estado", "OK");
			jsonReturn.put("mensaje", "Registro guardado");

			return jsonReturn;
		} catch (Exception e) {
			jsonReturn.put("estado", "ERROR");
			jsonReturn.put("mensaje", "Registro no guardado" + e.getMessage());
			return jsonReturn;
		}
	}

	// Eliminar
	@GetMapping(value = "delete/{id}")
	@ResponseBody
	public HashMap<String, String> delete(@PathVariable Integer id) {

		HashMap<String, String> jsonReturn = new HashMap<>();

		try {
			// buscando registro
			Especialidad es = respecialidad.findById(id).get();
			// eliminando registro
			respecialidad.delete(es);
			jsonReturn.put("estado", "OK");
			jsonReturn.put("mensaje", "Registro eliminado");
			return jsonReturn;

		} catch (Exception e) {
			jsonReturn.put("estado", "ERROR");
			jsonReturn.put("mensaje", "Registro no guardado" + e.getMessage());
			return jsonReturn;
		}
	}

	// actualizar
	@GetMapping(value = "update/{id}")
	@ResponseBody
	public HashMap<String, String> update(@RequestParam Integer id, @RequestParam String especialidad) {
		Especialidad es = new Especialidad();// creando objeto de doctor

		HashMap<String, String> jsonReturn = new HashMap<>();
		// asignado datos al objeto de doctor
		es.setId(id);
		es.setEspecialidad(especialidad);

		// manejando cualquier excepcion de error
		try {
			respecialidad.save(es);// guardando registro de doctor
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