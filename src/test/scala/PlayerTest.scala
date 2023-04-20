package cl.uchile.dcc
package gwent

import gwent.Card.{CardUnity, CardWeather}
import gwent.Deck.{Deck, HandDeck}
import munit.FunSuite


class PlayerTest extends FunSuite {
  // Jugador
  var jugador_porDefecto: IPlayer = _

  // Mazo
  var Mazo_prueba: Deck = _
  var Carta_prueba: ICard = _
  var Carta_prueba2: ICard = _
  var Carta_prueba3: ICard = _
  var Carta_pruebaClima: ICard = _
  var Lista_cartasPrueba: List[ICard] = _

  override def beforeEach(context: BeforeEach): Unit = {
    Carta_prueba = new CardUnity("Prueba", "tipoPrueba", 1, "no tiene")
    Carta_prueba2 = new CardUnity("Prueba2", "tipoPrueba", 2, "no tiene")
    Carta_prueba3 = new CardUnity("Prueba3", "tipoPrueba", 3, "no tiene")
    Carta_pruebaClima = new CardWeather("Prueba Clima", "efecto Prueba")
    Lista_cartasPrueba = List(Carta_prueba, Carta_prueba2, Carta_prueba3,Carta_pruebaClima)
    Mazo_prueba = new Deck(Lista_cartasPrueba)

    jugador_porDefecto = new Player(name = "jugador", section_board = "abajo", deck_cards = Mazo_prueba, hand_cards = new HandDeck())
  }

  test("Un jugador debe tener un nombre"){
    assertEquals(jugador_porDefecto.get_Name(),"jugador")
  }

  test("Un jugador tiene una seccion del tablero"){
    assertEquals(jugador_porDefecto.get_Section(), "abajo")
  }

  test("Un jugador debe tener por defecto 2 gemas") {
    assertEquals(jugador_porDefecto.get_gemCounter(), 2)
  }

  test("Cuando pierde una ronda pierde una gema"){
    jugador_porDefecto.lostRound()
    assertEquals(jugador_porDefecto.get_gemCounter(),1)
  }

  test("Puede obtener la cantidad de cartas del mazo"){
    assertEquals(jugador_porDefecto.numCards_deck(),4)
  }

  test("Puede obtener la cantidad de cartas en mano"){
    assertEquals(jugador_porDefecto.numCards_hand(),0)
  }

  test("Puede robar cartas del mazo y llevarlas a la mano"){
    jugador_porDefecto.drawCard(2)
    assertEquals(jugador_porDefecto.numCards_deck(),2)
    assertEquals(jugador_porDefecto.numCards_hand(), 2)
  }

  test("No puede robar más cartas de las que hay en el mazo") {
    jugador_porDefecto.drawCard(5)
    assertEquals(jugador_porDefecto.numCards_hand(), 4)
  }

  test("No puede robar cantidades negativas") {
    jugador_porDefecto.drawCard(-1)
    assertEquals(jugador_porDefecto.numCards_hand(),0)
  }
  
  test("Puede jugar una carta que tenga en la mano") {
    jugador_porDefecto.drawCard(3)
    assertEquals(jugador_porDefecto.numCards_hand(),3)
    val carta_jugada: ICard = jugador_porDefecto.playCard(0)
    // Del mazo se agregan en el orden de Carta_prueba1, ... , Carta_pruebaClima
    // Luego salen en el orden Carta_pruebaClima, Carta_prueba3, Carta_prueba2
    // y se agregan en el mismo orden a la mano
    assertEquals(carta_jugada, Carta_pruebaClima)
    assertEquals(jugador_porDefecto.numCards_hand(),2)
  }
}
