package com.example.demo.controlador;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entidad.Consulta;
import com.example.demo.repositorio.IConsultaRepository;

@Controller
@RequestMapping("consultas")// nombre del controlador//
public class ConsultaController {
	// repositorio para manipular los datos de la base//
	@Autowired
	IConsultaRepository consultaService;
	
	@GetMapping(value = "all", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Consulta>getAll(){
		return (List<Consulta>) consultaService.findAll();
	}
	@GetMapping(value = "save")
	@ResponseBody
	public HashMap<String, String> save(@RequestParam String consulta){
		Consulta co = new Consulta();//creando objeto de consulta
		
		HashMap<String, String> jsonReturn = new HashMap<>();
		// asignado datos al objeto de especialidad
		try {
			rconsulta.save(co);// guardando registro de consulta
			jsonReturn.put("estado", "OK");
			jsonReturn.put("mensaje", "Registro guardado");

			return jsonReturn;
		} catch (Exception e) {
			jsonReturn.put("estado", "ERROR");
			jsonReturn.put("mensaje", "Registro no guardado" + e.getMessage());
			return jsonReturn;
		}
		
	}
}
