package ar.edu.unahur.obj2.tareas

interface Tarea{
    val responsable: Empleado
    fun horasNecesarias(): Int
    fun costoDeLaTarea(): Int
    fun nominaDeEmpleados(): Int
}

class TareaSimple(val costoDeInfraestructura: Int, val horasEstimadas: Int, override val responsable: Empleado): Tarea {
    val listaDeEmpleado = mutableListOf<Empleado>()

    override fun horasNecesarias(): Int {
        check(listaDeEmpleado.size != 0){ //el check funciona al revez, si esta en == no anda.
            "No hay empleados para repartir tareas"
        }
        return horasEstimadas / listaDeEmpleado.size
    }
    override fun costoDeLaTarea() =
        listaDeEmpleado.sumBy { it.sueldo(this.horasNecesarias()) } +
                this.responsable.sueldo(horasEstimadas) +
                this.costoDeInfraestructura

    override fun nominaDeEmpleados() = listaDeEmpleado.size + 1
}

class TareaDeIntegracion(override val responsable: Empleado): Tarea{ //encargado innecesario
    val listaDeSubtareas = mutableListOf<Tarea>()

    fun horasDeSubtareas() = listaDeSubtareas.sumBy { it.horasNecesarias() }
    override fun horasNecesarias() = this.horasDeSubtareas() + (this.horasDeSubtareas() / 8)


    fun costos() = listaDeSubtareas.sumBy { it.costoDeLaTarea() }
    override fun costoDeLaTarea() = costos() + (costos()* 0.03).toInt() //mas sencillo


    override fun nominaDeEmpleados() = listaDeSubtareas.sumBy { it.nominaDeEmpleados() } + 1 // Encargado
}

class Empleado(val costoPorHora: Int) {
    fun sueldo(horasDeTrabajo: Int) = costoPorHora * horasDeTrabajo
}
