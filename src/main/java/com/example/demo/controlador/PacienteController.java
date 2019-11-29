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

import com.example.demo.entidad.Paciente;
import com.example.demo.repositorio.IPacienteRepository;

@Controller
@RequestMapping("pacientes")// nombre del controlador//
public class PacienteController {
	@Autowired
	IPacienteRepository rpaciente;
	
	@GetMapping(value="index")
    public String index() {
        return new String("views/Paciente/paciente");
    }


	@GetMapping(value = "all", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Paciente> getAll(){
		return (List<Paciente>) rpaciente.findAll();
		
	}
	@GetMapping(value = "save")
	@ResponseBody
	public HashMap<String, String> save(
			@RequestParam String nombre,
			@RequestParam String direccion) {
		Paciente pa = new Paciente();// creando objeto de paciente

		HashMap<String, String> jsonReturn = new HashMap<>();
		// asignado datos al objeto de paciente
		pa.setNombre(nombre);
		pa.setDireccion(direccion);

		// manejando cualquier excepcion de error
		try {
			rpaciente.save(pa);// guardando registro de paciente
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
			Paciente pa = rpaciente.findById(id).get();
			// eliminando registro
			rpaciente.delete(pa);
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
		public HashMap<String, String> update(@RequestParam Integer id, 
		@RequestParam String nombre,
		@RequestParam String direccion) {
			Paciente pa = new Paciente();// creando objeto de paciente

			HashMap<String, String> jsonReturn = new HashMap<>();
			// asignado datos al objeto de paciente
			pa.setId(id);
			pa.setNombre(nombre);
			pa.setDireccion(direccion);

			// manejando cualquier excepcion de error
			try {
				rpaciente.save(pa);// guardando registro de paciente
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
