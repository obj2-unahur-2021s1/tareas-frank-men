package ar.edu.unahur.obj2.tareas

class Tarea( val horasEstimadas: Int, val responsable: Empleado){
    val listaDeEmpleados = mutableListOf<Empleado>()

    fun horasNecesarias(){}
    fun costoDeTarea(){}
}

class Empleado {

}
