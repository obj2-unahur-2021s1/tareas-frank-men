package ar.edu.unahur.obj2.tareas

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf

class TareaTest : DescribeSpec({
  describe("Una tarea simple"){
    it("se puede crear"){
      val eduardoResponsable = Empleado(200)
      val tareaMantenimiento = TareaSimple(13000,16,eduardoResponsable)
      tareaMantenimiento.shouldBeInstanceOf<TareaSimple>()
    }
    it("tiene horas necesarias para terminar el projecto."){
      //horas estimadas dividido cantidad de empleados (sin responsable.)
      val eduardoResponsable = Empleado(200)
      val pepeLaburante = Empleado(100)
      val mariaLaburante = Empleado(100)
      val tareaMantenimiento = TareaSimple(13000,16,eduardoResponsable)
      tareaMantenimiento.listaDeEmpleado.add(pepeLaburante)
      tareaMantenimiento.listaDeEmpleado.add(mariaLaburante)
      tareaMantenimiento.horasNecesarias().shouldBe(8)
    }
    it("tiene un costo."){
      val eduardoResponsable = Empleado(300) //4800
      val pepeLaburante = Empleado(100) //400
      val sofiaLaburante = Empleado(150) //600
      val juanLaburante = Empleado(100) //400
      val lucilaLaburante = Empleado(150) //600
      val tareaMantenimiento = TareaSimple(13000,16,eduardoResponsable) //13k
      tareaMantenimiento.listaDeEmpleado.add(pepeLaburante)
      tareaMantenimiento.listaDeEmpleado.add(sofiaLaburante)
      tareaMantenimiento.listaDeEmpleado.add(juanLaburante)
      tareaMantenimiento.listaDeEmpleado.add(lucilaLaburante)
      tareaMantenimiento.horasNecesarias().shouldBe(4)
      tareaMantenimiento.costoDeLaTarea().shouldBe(19800)
    }
  }
  describe("Una tarea de integracion"){
    //2 tareas para usar dentro de los tests:
    val eduardoResponsable = Empleado(300)
    val pepeLaburante = Empleado(100)
    val sofiaLaburante = Empleado(150)
    val juanLaburante = Empleado(100)
    val lucilaLaburante = Empleado(150)
    val tareaMantenimiento = TareaSimple(13000,16,eduardoResponsable)
    tareaMantenimiento.listaDeEmpleado.add(pepeLaburante)
    tareaMantenimiento.listaDeEmpleado.add(sofiaLaburante)
    tareaMantenimiento.listaDeEmpleado.add(juanLaburante)
    tareaMantenimiento.listaDeEmpleado.add(lucilaLaburante)
    //---
    val pepitoResponsable = Empleado(150)
    val pipoLaburante = Empleado(50)
    val candyLaburante = Empleado(75)
    val agusLaburante = Empleado(50)
    val antoLaburante = Empleado(75)
    val tareaDesayuno = TareaSimple(6500,32,pepitoResponsable)
    tareaDesayuno.listaDeEmpleado.add(pipoLaburante)
    tareaDesayuno.listaDeEmpleado.add(candyLaburante)
    tareaDesayuno.listaDeEmpleado.add(agusLaburante)
    tareaDesayuno.listaDeEmpleado.add(antoLaburante)
    //-------------------------------------------------------------------------
    it("Se puede crear"){
      val tareaDeIntegracion = TareaDeIntegracion(eduardoResponsable)
      tareaDeIntegracion.listaDeSubtareas.add(tareaMantenimiento)
      tareaDeIntegracion.shouldBeInstanceOf<TareaDeIntegracion>()
    }
    it("Tiene horas necesarias para realizarse"){
      val jesusResponsable = Empleado(1000)
      val tareaDeMantenerDesayunos = TareaDeIntegracion(jesusResponsable)
      tareaDeMantenerDesayunos.listaDeSubtareas.add(tareaMantenimiento)
      tareaDeMantenerDesayunos.listaDeSubtareas.add(tareaDesayuno)
      //(8 de desayuno + 4 de mantenimiento) + 1 hora por cada 8 horas de trabajo
      tareaDeMantenerDesayunos.horasNecesarias().shouldBe(13)
    }
    it("tiene un costo"){
      val jesusResponsable = Empleado(1000)
      val tareaDeMantenerDesayunos = TareaDeIntegracion(jesusResponsable)
      tareaDeMantenerDesayunos.listaDeSubtareas.add(tareaMantenimiento) //costo = 19800
      tareaDeMantenerDesayunos.listaDeSubtareas.add(tareaDesayuno) //costo = 13300
      tareaMantenimiento.costoDeLaTarea().shouldBe(19800)
      tareaDesayuno.costoDeLaTarea().shouldBe(13300)
      //costoTarea = 19800 + 13300 = (33100) + 33100 * 0.03(993)
      tareaDeMantenerDesayunos.costoDeLaTarea().shouldBe(34093)
    }
    it("tiene una nomina de empleados"){
      val jesusResponsable = Empleado(1000)
      val tareaDeMantenerDesayunos = TareaDeIntegracion(jesusResponsable)
      tareaDeMantenerDesayunos.listaDeSubtareas.add(tareaMantenimiento) //costo = 19800
      tareaDeMantenerDesayunos.listaDeSubtareas.add(tareaDesayuno) //costo = 13300
      tareaDeMantenerDesayunos.nominaDeEmpleados().shouldBe(11)
    }
  }
})
