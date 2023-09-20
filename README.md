# Proyecto "Mis_Tareas"
      Este Proyecto llamado "Mis Tareas" esta realizada con el lenguaje KOTLIN en el entorno de desarrollo ANDROID STUDIO.
      "Cabe aclarar que no soy diseñador por eso la interfaz no es tan llamativa"
      
Resumen:

      Es una app donde podes:
      🖱️Crear una cuenta
      🖱️Loguearte(iniciar sesion,cerrar sesion)
      🖱️Crear tareas
      🖱️Editar tareas
      🖱️Marcarlas como realizadas
      🖱️Visualizar tus tareas realizadas
      🖱️Eliminar las tareas (realizadas o no)
      🖱️Editar las tareas no realizadas

Resumen de las clases utilizadas:

      Breve descripcion de las clases utilizadas y como realize dicho proyecto 

Crear_Cuenta

      Link clase:
      https://github.com/Calisaya-Manu/_Mis_Tareas_/blob/master/app/src/main/java/com/example/listadetareas/Crear_Cuenta.kt
      Link Layout vinculada:

      DESCRIPCION:
      Se encarga de crear una cuenta para un usuario ingresando nombre del usuario y contraseña 
      -Bajar la lista de todos los usuarios que fueron creados quedandose con su nombre
      (para despues compararlos y asi no se creen usuarios repeditos)
      -Verifica si las contraseñas ingresadas coincidan
      -Se crea al usuario y lo agrega a la lista para despues poner iniciar sesion
      -Utilize SharetPreferences para guardar la lista de forma persistente
      (combiertiendola primero en un objeto GSON)
      
      
