package ar.edu.unahur.obj2.tareas

abstract class Tarea(val horasEstimadas: Int, val responsable: Empleado){
    val listaDeEmpleados = mutableListOf<Empleado>()
    fun horasNecesarias() = horasEstimadas / listaDeEmpleados.size
    fun costoDeTarea(){}
}

class Simple(val tiempoTotal: Int, val encargado: Empleado): Tarea(horasEstimadas = tiempoTotal,responsable = encargado){

}
class Integracion(val tiempoTotal: Int, val encargado: Empleado): Tarea(horasEstimadas = tiempoTotal,responsable = encargado){

}



class Empleado(val costoPorHora: Int) {
}
