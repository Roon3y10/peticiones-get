//METODO PARA CARGAR DATOS CON UNA PETICION AJAX
         //EN LA TABLA.
         function cargarDatos() {
             //peticion AJAX al back-end
         $.ajax({
                url:"/doctores/all",
                method:"Get",
                success:function(response){
                    //se procesa la respuesta del back-end o servidor
                    //y se agregar los valores obtenidos en la respuesta
                    //a la tabla, a su tbody
                    //resetear datos de la tabla
                    $("#tdatos").html("");
                    //se recorren los elementos del array 
                    //retornado como respuesta
         for (let i = 0; i < response.length; i++) {
                            
                        $("#tdatos").append(""
                        +"<tr>"
                            +"<td>"+response[i].id+"</td>"
                            +"<td>"+response[i].nombre+"</td>"
                            +"<td>"+response[i].direccion+"</td>"
                            +"<td>"+response[i].especialidad.especialidad+"</td>"
                            +"<td>"
                                +"<button onclick='cargarRegistro("+response[i].id+")'  type='button' class='btn btn-warning ml-4 mt-1' data-toggle='modal' data-target='#editar'>Editar</button>"
                                +"<button onclick='setIdPersona("+response[i].id+");' type='button' class='btn btn-danger ml-4 mt-1' data-toggle='modal' data-target='#eliminar'>Eliminar</button>"
                            +"</td>"
                        +"</tr>");
                            
                        }