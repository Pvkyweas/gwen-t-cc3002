package cl.uchile.dcc
package gwent

import cl.uchile.dcc.gwent.Card.{CardUnity, CardWeather}
import munit.FunSuite

class DeckTest extends FunSuite {
  var Mazo_prueba1: IDeck = _
  var Mazo_prueba2: IDeck = _
  var Carta_prueba: ICard = _
  var Carta_prueba2: ICard = _
  var Carta_prueba3: ICard = _
  var Carta_pruebaClima: ICard = _
  var Lista_cartasPrueba: List[ICard]= _

  override def beforeEach(context: BeforeEach): Unit = {
    Mazo_prueba1 = new Deck()
    Carta_prueba = new CardUnity("Prueba","tipoPrueba",1, "no tiene")
    Carta_prueba2 = new CardUnity("Prueba2","tipoPrueba",2, "no tiene")
    Carta_prueba3 = new CardUnity("Prueba3","tipoPrueba",3, "no tiene")
    Lista_cartasPrueba = List(Carta_prueba, Carta_prueba2, Carta_prueba3)
    Mazo_prueba2 = new Deck(Lista_cartasPrueba)
    Carta_pruebaClima = new CardWeather("Prueba Clima", "efecto Prueba")
  }

  test("Un mazo vacio debe retornar true al usar .isEmpty") {
    assertEquals(Mazo_prueba1.isEmpty, true)
  }

  test("Robar cartas a un mazo vacio deberia retornar None") {
    assertEquals(Mazo_prueba1.draw_Card(), None)
  }

  test("A un mazo se le deberia poder agregar cartas") {
    assertEquals(Mazo_prueba1.get_Size(), 0)
    Mazo_prueba1.addCard(Carta_prueba)
    assertEquals(Mazo_prueba1.get_Size(), 1)
  }
  test("A un mazo se le deberia poder agregar cartas de unidad y clima") {
    Mazo_prueba1.addCard(Carta_prueba)
    Mazo_prueba1.addCard(Carta_pruebaClima)
  }

  test("A un mazo se le deberian poder quitar cartas"){
    Mazo_prueba1.addCard(Carta_prueba)
    val carta_robada: ICard = Mazo_prueba1.draw_Card().get
    assertEquals(Mazo_prueba1.get_Size(), 0)
    assertEquals(carta_robada,Carta_prueba)
  }

  test("Al sacar una carta, se deberia obtener la ultima agregada"){
    Mazo_prueba1.addCard(Carta_prueba)
    Mazo_prueba1.addCard(Carta_prueba2)
    Mazo_prueba1.addCard(Carta_prueba3)

    var carta_robada: ICard = Mazo_prueba1.draw_Card().get
    assertEquals(carta_robada,Carta_prueba3)

    carta_robada = Mazo_prueba1.draw_Card().get
    assertEquals(carta_robada, Carta_prueba2)

    carta_robada = Mazo_prueba1.draw_Card().get
    assertEquals(carta_robada, Carta_prueba)

  }

  test("Se puede robar una carta por la posición de esta") {
    val carta_robada = Mazo_prueba2.draw_Card(2).get
    // Por como se agregan las cartas, el orden es Carta_prueba3, Carta_prueba2 y Carta_prueba
    assertEquals(carta_robada,Carta_prueba)
    assertEquals(Mazo_prueba2.get_Size(),2)
  }

  test("No puede jugar una carta de una posicion que no existe, por lo que retornaria None") {
    assertEquals(Mazo_prueba2.draw_Card(-1), None)
    assertEquals(Mazo_prueba2.draw_Card(10), None)
  }

  test("Un mazo deberia poder barajarse"){
    val Mazo_prueba2 = new Deck(Lista_cartasPrueba)
    val Mazo_prueba3 = new Deck(Lista_cartasPrueba)
    Mazo_prueba3.shuffle()
    val comparacion: List[Boolean] = List(Mazo_prueba2.draw_Card() != Mazo_prueba3.draw_Card(),
      Mazo_prueba2.draw_Card() != Mazo_prueba3.draw_Card(),
      Mazo_prueba2.draw_Card() != Mazo_prueba3.draw_Card())

    assert(comparacion.contains(false))
  }

}
