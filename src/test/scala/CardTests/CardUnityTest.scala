package cl.uchile.dcc
package CardTests

import gwent.Card.CardUnity
import gwent.ICard

import munit.FunSuite

class CardUnityTest extends FunSuite {
  var carta_prueba: ICard = _
  var carta_prueba2: ICard = _

  override def beforeEach(context: BeforeEach): Unit = {
    carta_prueba = new CardUnity("Prueba","tipoPrueba",3, "Refuerzo moral")
    carta_prueba2 = new CardUnity("Prueba2","tipoPrueba2",1, "Vinculo estrecho")
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
    assertEquals(carta_prueba.get_effectCode(), "cla-tipoPrueba-addf-1") // Para refuerzo moral es la clasificacion
    assertEquals(carta_prueba2.get_effectCode(), "nam-Prueba2-mult-2") // Para vinculo estrecho es el nombre
  }

}
