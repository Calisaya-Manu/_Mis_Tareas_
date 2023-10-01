<h3 align="center"> <a href="https://github.com/Calisaya-Manuel/Calisaya-Manuel/edit/main/README.md">Crear_Cuenta</a> </h3>       
<img align="left" src="https://github.com/Calisaya-Manuel/_Mis_Tareas_/blob/master/fotos/Screenshot_20230919_230621.png" alt="Texto Alternativo" width="120" height="220" />

       DESCRIPCION:
      Se encarga de crear una cuenta para un usuario ingresando nombre del usuario y contraseña 
      -Bajar la lista de todos los usuarios que fueron creados quedandose con su nombre
      (para despues compararlos y asi no se creen usuarios repeditos)
      -Verifica si las contraseñas ingresadas coincidan
      -Se crea al usuario y lo agrega a la lista para despues poner iniciar sesion
      -Utilize SharetPreferences para guardar la lista de forma persistente
      (combiertiendola primero en un objeto GSON)
      .
<br>
<br>
<h3 align="center">Iniciar_Sesion</h3>
<img align="right" src="https://github.com/Calisaya-Manuel/_Mis_Tareas_/blob/master/fotos/Screenshot_20230919_230453.png" alt="Texto Alternativo" width="120" height="220" />
     
      DESCRIPCION:
      Se encarga de Comprobar el nombre y contraseña de la lista de usuarios que fueron creados anteriormente
      -Baja la lista de todos los usuario
      -Compara los datos ingresados con los de la lista para ver si hay un usuario
      -Cuando se validan los datos en nombre del usuario mas la contraseña se convierten en una clave de sharedPreference
      (para asi mostrar la lista de tareas de cada usuario)


      .
      
<h3 align="center">MisNotas</h3>
<img align="left" src="https://github.com/Calisaya-Manuel/_Mis_Tareas_/blob/master/fotos/Screenshot_20230919_230513.png" alt="Texto Alternativo" width="120" height="220" />

      DESCRIPCION:
      Muestra la lista de Tareas del usuario con el cual inicio la sesion se puede agregar tareas e ir a las tareas realizadas
      -Se baja la lista de las Tareas mediante la clave que fue pasada por la clase anterior
      -Se muestra las Tareas utilizando una listView
      -Si no tenia Tareas cargadas muestra un mensaje
      -Al precsonar una Tarea va directamente al panel de editar
      (Asi se aprecia el contenido total de la tarea donde se puede "editar y borrar" la tareas)

      .
      
<h3 align="center">agregarTarea</h3>
<img align="right" src="https://github.com/Calisaya-Manuel/_Mis_Tareas_/blob/master/fotos/Screenshot_20230919_230529.png" alt="Texto Alternativo" width="120" height="220" />

      DESCRIPCION:
      Agrega una tarea en la lista de tareas del usuario
      -Crea una nueva tarea (con titulo y descripcion) ingresada los datos por teclado
      -Me bajo la lista de Tareas
      -Agrego la tarea a la lista
      -Guardo la lista de forma persistente



      .
      
<h3 align="center">editarNota</h3>
<img align="left" src="https://github.com/Calisaya-Manuel/_Mis_Tareas_/blob/master/fotos/Screenshot_20231001_204007.png" alt="Texto Alternativo" width="120" height="220" />

      DESCRIPCION:
      Se encarga de editar y borrar una tarea o agregarla al panel de Tareas Realizadas
      -Me bajo la lista de tareas
      (la lista porque asi si borro una tarea se refleja en el panel anterior)
      -Me guardo y muestro la tarea que se presiono anteriormente 
      -Si la edito la cargo de vuelta a la lista en la misma posicion
      -Si la marco como realizada elimino la tarea y la envio a tareas realizadas
      -Guardo la lista de forma persistente

      .
<h3 align="center">tareasREalizadas</h3>
<img align="right" src="https://github.com/Calisaya-Manuel/_Mis_Tareas_/blob/master/fotos/Screenshot_20230919_230551.png" alt="Texto Alternativo" width="120" height="220" />

      DESCRIPCION:
      se encarga de mostrar las tareas realizadas 
      -Me bajo la lista de tareas realizadas de cada usuario
      -muestro todas las tareas
      -si se recibio una nota para guardar le agrego a la lista y guardo la lista
      -si se presiona en una nota va directamente al panel editar



      .

<h4 align="center">
       "Cabe aclarar que la clase editarNota es utilizada por TareasRealizadas y MisNotas donde tuve que agregar un idenificador para saber
que actividad realizo la invocacion".
-En caso de realizarla tareasREalizadas se desabilito los botones guardar y agregar a tareas reaalizadas
</h4>
       
