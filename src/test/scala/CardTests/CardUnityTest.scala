package cl.uchile.dcc
package CardTests

import gwent.Card.CardUnity
import gwent.ICard

import munit.FunSuite

class CardUnityTest extends FunSuite {
  var carta_prueba: ICard = _
  var carta_prueba2: ICard = _
  var cMele_prueba: ICard = _
  var cDistancia_prueba: ICard = _
  var cAsedio_prueba: ICard = _

  override def beforeEach(context: BeforeEach): Unit = {
    carta_prueba = new CardUnity("Prueba","tipoPrueba",3, "Refuerzo moral")
    carta_prueba2 = new CardUnity("Prueba2","tipoPrueba2",1, "Vinculo estrecho")
    cMele_prueba = new CardUnity("meleprueba","cuerpo a cuerpo",2)
    cDistancia_prueba = new CardUnity("distanciaprueba", "distancia", 2)
    cAsedio_prueba = new CardUnity("asedioprueba", "asedio", 2)
  }
  
  test("Deberia poder obtenerse el nombre"){
    assertEquals(carta_prueba.get_Name(),"Prueba")
  }

  test("Deberia poder obtenerse el valor de fuerza de la carta") {
    assertEquals(carta_prueba.get_Force(),3)
  }

  test("Deberia poder obtenerse el efecto de la carta") {
    assertEquals(carta_prueba.get_Effect(), "Refuerzo moral")
  }

  test("Deberia poder obtenerse la clasificacion de la carta") {
    assertEquals(carta_prueba.get_Classification(), "tipoPrueba")
  }

  test("Deberia poder obtenerse el requisito correcto según el efecto") {
    assertEquals(carta_prueba.get_Requirement(), "tipoPrueba") // Para refuerzo moral es la clasificacion
    assertEquals(carta_prueba2.get_Requirement(), "Prueba2") // Para vinculo estrecho es el nombre
  }

}
