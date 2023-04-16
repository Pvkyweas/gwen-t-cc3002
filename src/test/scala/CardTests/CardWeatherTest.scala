package cl.uchile.dcc
package CardTests

import gwent.Card.CardWeather
import gwent.ICard

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

  test("Una carta clima tiene como requisito la clasificacion según el efecto menos en clima despejado que no tiene"){
    val Cprueba_clima1 = new CardWeather("clima1", "Escarcha mordiente")
    val Cprueba_clima2 = new CardWeather("clima2", "Niebla impenetrable")
    val Cprueba_clima3 = new CardWeather("clima3", "Lluvia Torrencial")
    val Cprueba_clima4 = new CardWeather("clima4", "Clima despejado")

    assertEquals(Cprueba_clima1.get_effectCode(),"cla-c-sf-1")
    assertEquals(Cprueba_clima2.get_effectCode(),"cla-d-sf-1")
    assertEquals(Cprueba_clima3.get_effectCode(),"cla-a-sf-1")
    assertEquals(Cprueba_clima4.get_effectCode(),"all-rf")
  }

  test("La clasificacion de una carta clima es Clima"){
    assertEquals(carta_prueba.get_Classification(), "Clima")
  }

  test("Se puede obtener el efecto de la carta") {
    assertEquals(carta_prueba.get_Effect(), "efecto Prueba")
  }
}
