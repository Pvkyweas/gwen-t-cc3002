package cl.uchile.dcc
package CardTests

import cl.uchile.dcc.gwent.Card.Effect.NoneEffect
import cl.uchile.dcc.gwent.Card.ICard
import cl.uchile.dcc.gwent.Card.Unity.{ICardUnity, MeleeCard, RangeCard, SiegeCard}
import cl.uchile.dcc.gwent.Card.Weather.{BitingFrostWeatherCard, ClearWeatherCard, ImpenetrableFogWeatherCard, TorrentialRainWeatherCard}
import munit.FunSuite

class BasicCardTest extends FunSuite {
  var carta_prueba: ICardUnity = _
  var carta_prueba2: ICardUnity = _
  var carta_prueba3: ICardUnity = _
  
  var clima1: ICard = _
  var clima2: ICard = _
  var clima3: ICard = _
  var clima4: ICard = _

  override def beforeEach(context: BeforeEach): Unit = {
    carta_prueba = new MeleeCard("Melee", new NoneEffect(), 3)
    carta_prueba2 = new RangeCard("Range",new NoneEffect(),3)
    carta_prueba3 = new SiegeCard("Siege", new NoneEffect(), 3)
    
    clima1 = new BitingFrostWeatherCard("a")
    clima2 = new ClearWeatherCard("b")
    clima3 = new ImpenetrableFogWeatherCard("c")
    clima4 = new TorrentialRainWeatherCard("d")
  }
  
  test("Deberia poder obtenerse el nombre"){
    assertEquals(carta_prueba.get_Name(),"Melee")
    assertEquals(carta_prueba2.get_Name(),"Range")
    assertEquals(carta_prueba3.get_Name(),"Siege")

    assertEquals(clima1.get_Name(), "a")
    assertEquals(clima2.get_Name(), "b")
    assertEquals(clima3.get_Name(), "c")
    assertEquals(clima4.get_Name(), "d")
  }

  test("Para las cartas de unidad deberia poder obtenerse la fuerza") {
    assertEquals(carta_prueba.get_Force(), 3)
    assertEquals(carta_prueba2.get_Force(), 3)
    assertEquals(carta_prueba3.get_Force(), 3)
  }

  test("Deberia poder obtenerse el effecto"){
    assertEquals(carta_prueba.get_Effect(),"No tiene efecto")
    assertEquals(carta_prueba2.get_Effect(),"No tiene efecto")
    assertEquals(carta_prueba3.get_Effect(),"No tiene efecto")

    assertEquals(clima1.get_Effect(), "Escarcha Mordiente")
    assertEquals(clima2.get_Effect(), "Clima despejado")
    assertEquals(clima3.get_Effect(), "Niebla impenetrable")
    assertEquals(clima4.get_Effect(), "Lluvia torrencial")
  }

  test("Equals de las cartas que no se probaron en el test equals de PlayerTest"){
    assert(!carta_prueba.equals(carta_prueba3))
    assert(!clima1.equals(carta_prueba))
    assert(!clima3.equals(clima4))
  }
}
