package com.example.demo.controlador;

import java.sql.Date;
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
import com.example.demo.entidad.Consulta;

import com.example.demo.servicios.ConsultaService;

@Controller
@RequestMapping("consultas")// nombre del controlador//
public class ConsultaController {
	
	// repositorio para manipular los datos de la base//
	@Autowired
	ConsultaService consultaService;
	
	@GetMapping(value = "all", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Consulta>getAllConsultas(){
		return (List<Consulta>) consultaService.getAll();
	}
	@GetMapping(value = "save")
	@ResponseBody
	public HashMap<String, String> save(
			@RequestParam  Date fecha,
			@RequestParam String sintomas,
			@RequestParam String diagnostico,
			@RequestParam Integer idDoctor){
		Consulta co = new Consulta();//creando objeto de consulta
		
		HashMap<String, String> jsonReturn = new HashMap<>();
		// asignado datos al objeto de consulta
		co.setFecha(fecha);
		co.setSintomas(sintomas);
		co.setDiagnostico(diagnostico);
		co.setDoctor(consultaService.getDoctor(idDoctor));
		try {
			consultaService.SaveOrUpate(co);// guardando registro de consulta
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
			Consulta co = consultaService.getConsulta(id);
			// eliminando registro
			consultaService.Delete(co);
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
		public HashMap<String, String> update(
	            @RequestParam Integer id,
	            @RequestParam Date fecha,
				@RequestParam String sintomas,
				@RequestParam String diagnostico,
				@RequestParam Integer idDoctor) {
			Consulta co = new Consulta();// creando objeto de doctor

			HashMap<String, String> jsonReturn = new HashMap<>();
			// asignado datos al objeto de coonsulta
	        co.setId(id);
			co.setFecha(fecha);
			co.setSintomas(sintomas);
			co.setDiagnostico(diagnostico);
			co.setDoctor(consultaService.getDoctor(idDoctor));

			// manejando cualquier excepcion de error de la peticion
			try {
				consultaService.SaveOrUpate(co);// guardando registro de doctor
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
