package ar.edu.unahur.obj2.tareas

interface Tarea{
    val responsable: Empleado
    fun horasNecesarias(): Int
    fun costoDeLaTarea(): Int
    fun nominaDeEmpleados(): Int
}

class TareaSimple(val costoDeInfraestructura: Int, val horasEstmadas: Int, override val responsable: Empleado): Tarea {
    val listaDeEmpleado = mutableListOf<Empleado>()

    override fun horasNecesarias(): Int {
        check(listaDeEmpleado.size == 0){
            "No hay empleados para repartir tareas"
        }
        return horasEstmadas / listaDeEmpleado.size
    }
    override fun costoDeLaTarea() =
        listaDeEmpleado.sumBy { it.sueldo(this.horasNecesarias()) } +
                this.responsable.sueldo(horasEstmadas) +
                this.costoDeInfraestructura

    override fun nominaDeEmpleados() = listaDeEmpleado.size + 1 //responsable
}

class TareaDeIntegracion(val encargado: Empleado, override val responsable: Empleado): Tarea{
    val listaDeSubtareas = mutableListOf<TareaSimple>()
    fun horas() = listaDeSubtareas.sumBy { it.horasNecesarias() }
    override fun horasNecesarias() = this.horas() + this.horas()/8

    fun costos() = listaDeSubtareas.sumBy { it.costoDeLaTarea() }
    override fun costoDeLaTarea() = costos() + costos()* 3 / 100

    override fun nominaDeEmpleados() = listaDeSubtareas.sumBy { it.nominaDeEmpleados() } + 1 // Encargado
}

class Empleado(val costoPorHora: Int) {
    fun sueldo(horasDeTrabajo: Int) = costoPorHora * horasDeTrabajo
}
