package cl.uchile.dcc
package CardTests

import gwent.ICard

import gwent.Card.CardUnity
import gwent.Card.CardWeather
import munit.FunSuite

class CardApplyEffectTest extends FunSuite{
  // Unity Card
  var Cprueba_mele: ICard = _
  var Cprueba_distancia: ICard = _
  var Cprueba_asedio: ICard = _

  // Weather Card
  var Cprueba_clima1: ICard = _
  var Cprueba_clima2: ICard = _
  var Cprueba_clima3: ICard = _
  var Cprueba_clima4: ICard = _

  override def beforeEach(context: BeforeEach): Unit = {
    Cprueba_mele = new CardUnity("pruebaM","c",
      2,"No tiene")
    Cprueba_distancia = new CardUnity("pruebaD","d",
      3, "Refuerzo moral")
    Cprueba_asedio = new CardUnity("pruebaA","a",
      4, "Vinculo estrecho")

    Cprueba_clima1 = new CardWeather("clima1","Escarcha mordiente")
    Cprueba_clima2 = new CardWeather("clima2","Niebla impenetrable")
    Cprueba_clima3 = new CardWeather("clima3","Lluvia torrencial")
    Cprueba_clima4 = new CardWeather("clima4","Clima despejado")
  }

  test("Se les puede aplicar el efecto: Refuerzo moral si son de la misma fila") {
    val Cprueba_efecto = new CardUnity("prueba","d",2)
    Cprueba_efecto.effectApply(Cprueba_distancia)
    Cprueba_mele.effectApply(Cprueba_distancia)
    
    assertEquals(Cprueba_efecto.get_Force(), 3)
    assertEquals(Cprueba_mele.get_Force(), 2)
  }

  test("Se les puede aplicar el efecto: Vinculo estrecho si tienen el mismo nombre") {
    val Cprueba_efecto = new CardUnity("pruebaA", "a",3)
    val Cprueba_efecto2 = new CardUnity("pruebaB", "a",3)
    Cprueba_efecto.effectApply(Cprueba_asedio)
    Cprueba_efecto2.effectApply(Cprueba_asedio)

    assertEquals(Cprueba_efecto.get_Force(), 6)
    assertEquals(Cprueba_efecto2.get_Force(), 3)
  }

  test("Se les puede aplicar los efectos de las cartas de clima, si se cambia el clima se recupera el valor anterior de fuerza") {
    Cprueba_mele.effectApply(Cprueba_clima1)
    Cprueba_distancia.effectApply(Cprueba_clima1)
    Cprueba_asedio.effectApply(Cprueba_clima1)

    assertEquals(Cprueba_mele.get_Force(), 1)
    assertEquals(Cprueba_distancia.get_Force(), 3)
    assertEquals(Cprueba_asedio.get_Force(), 4)

    Cprueba_mele.effectApply(Cprueba_clima2)
    Cprueba_distancia.effectApply(Cprueba_clima2)
    Cprueba_asedio.effectApply(Cprueba_clima2)

    assertEquals(Cprueba_mele.get_Force(), 2)
    assertEquals(Cprueba_distancia.get_Force(), 1)
    assertEquals(Cprueba_asedio.get_Force(), 4)

    Cprueba_mele.effectApply(Cprueba_clima3)
    Cprueba_distancia.effectApply(Cprueba_clima3)
    Cprueba_asedio.effectApply(Cprueba_clima3)

    assertEquals(Cprueba_mele.get_Force(), 2)
    assertEquals(Cprueba_distancia.get_Force(), 3)
    assertEquals(Cprueba_asedio.get_Force(), 1)

    Cprueba_mele.effectApply(Cprueba_clima4)
    Cprueba_distancia.effectApply(Cprueba_clima4)
    Cprueba_asedio.effectApply(Cprueba_clima4)

    assertEquals(Cprueba_mele.get_Force(), 2)
    assertEquals(Cprueba_distancia.get_Force(), 3)
    assertEquals(Cprueba_asedio.get_Force(), 4)
  }
}
