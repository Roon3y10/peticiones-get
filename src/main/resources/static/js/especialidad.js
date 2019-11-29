
$(document).ready(inicio);
function inicio(){
	cargarDatos();
}

//METODO PARA CARGAR DATOS CON UNA PETICION AJAX
//EN LA TABLA.
function cargarDatos() {
	// peticion AJAX al back-end
	$.ajax({
		url : "/especialidades/all",
		method : "Get",
		success : function(response) {
			// se procesa la respuesta del back-end o servidor
			// y se agregar los valores obtenidos en la respuesta
			// a la tabla, a su tbody
			// resetear datos de la tabla
			$("#tdatos").html("");
			// se recorren los elementos del array
			// retornado como respuesta
			for (let i = 0; i < response.length; i++) {

				$("#tdatos").append(
						"" + "<tr>" + "<td>" + response[i].id + "</td>"
								+ "<td>" + response[i].especialidad + "</td>"
								+ "</tr>");

			}
		},
		error : function() {
		}
	});
}
