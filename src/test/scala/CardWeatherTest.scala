package cl.uchile.dcc
package gwent

import munit.FunSuite

class CardWeatherTest extends FunSuite  {
  var carta_prueba: ICard = _

  override def beforeEach(context: BeforeEach): Unit = {
    carta_prueba = new CardWeather("Prueba","efecto Prueba")
  }
  
  test("una carta clima tiene nombre"){
    assertEquals(carta_prueba.get_Name(),"Prueba")
  }

  test("La fuerza de una carta de clima es 0"){
    assertEquals(carta_prueba.get_Force(),0)
  }

  test("Una carta clima no tiene requisito para aplicar un efecto a si misma"){
    assertEquals(carta_prueba.get_Requirement(), "No tiene")
  }

  test("La clasificacion de una carta clima es Clima"){
    assertEquals(carta_prueba.get_Classification(), "Clima")
  }

  test("Se puede obtener el efecto de la carta") {
    assertEquals(carta_prueba.get_Effect(), "efecto Prueba")
  }
}
