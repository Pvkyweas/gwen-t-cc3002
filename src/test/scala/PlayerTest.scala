package cl.uchile.dcc
package gwent

import gwent.Deck.{Deck, HandDeck}

import cl.uchile.dcc.gwent.Card.Effect.NoneEffect
import cl.uchile.dcc.gwent.Card.ICard
import cl.uchile.dcc.gwent.Card.Unity.{MeleeCard, RangeCard, SiegeCard}
import cl.uchile.dcc.gwent.Card.Weather.{AbstractCardWeather, ClearWeatherCard}
import munit.FunSuite

import scala.collection.mutable.ListBuffer


class PlayerTest extends FunSuite {
  // Jugador
  var jugador_porDefecto: IPlayer = _

  // Mazo
  var Mazo_prueba: Deck = _
  var Carta_prueba: ICard = _
  var Carta_prueba2: ICard = _
  var Carta_prueba3: ICard = _
  var Carta_pruebaClima: ICard = _
  var Lista_cartasPrueba: ListBuffer[ICard] = _

  override def beforeEach(context: BeforeEach): Unit = {
    Carta_prueba = new MeleeCard("Melee", new NoneEffect(), 3)
    Carta_prueba2 = new RangeCard("Range", new NoneEffect(), 3)
    Carta_prueba3 = new SiegeCard("Siege", new NoneEffect(), 3)
    Carta_pruebaClima = new ClearWeatherCard("Prueba Clima")
    Lista_cartasPrueba = ListBuffer(Carta_prueba, Carta_prueba2, Carta_prueba3,Carta_pruebaClima)
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

  test("Cuando pierde una ronda pierde y no tiene gemas deberia quedarse en 0") {
    jugador_porDefecto.lostRound()
    jugador_porDefecto.lostRound()
    assertEquals(jugador_porDefecto.get_gemCounter(), 0)
    jugador_porDefecto.lostRound()
    assertEquals(jugador_porDefecto.get_gemCounter(), 0)
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
    val carta_jugada: ICard = jugador_porDefecto.playCard(0).get
    // Del mazo se agregan en el orden de Carta_prueba1, ... , Carta_pruebaClima
    // Luego salen en el orden Carta_pruebaClima, Carta_prueba3, Carta_prueba2
    // y se agregan en el mismo orden a la mano
    assertEquals(carta_jugada, Carta_pruebaClima)
    assertEquals(jugador_porDefecto.numCards_hand(),2)
  }

  test("Tratar de jugar una carta cuando la mano esta vacia, retorna None"){
    assertEquals(jugador_porDefecto.playCard(1),None)
  }

  test("Orden en el que se agregan las cartas, la idea es probar el mismo test que el siguiente pero sin el shuffle"){
    jugador_porDefecto.drawCard(3)
    val comparacion: List[Boolean] = List(jugador_porDefecto.playCard(0).get == Lista_cartasPrueba(3),
      jugador_porDefecto.playCard(0).get == Lista_cartasPrueba(2),
      jugador_porDefecto.playCard(0).get == Lista_cartasPrueba(1))
    assert(!comparacion.contains(false))
  }

  test("se puede barajar"){
    jugador_porDefecto.shuffle()
    jugador_porDefecto.drawCard(3)
    // Del mazo se agregan en el orden de Carta_prueba1, ... , Carta_pruebaClima
    // Luego salen en el orden Carta_pruebaClima, Carta_prueba3, Carta_prueba2
    // y se agregan en el mismo orden a la mano
    val comparacion: List[Boolean] = List(jugador_porDefecto.playCard(0).get != Lista_cartasPrueba(3),
      jugador_porDefecto.playCard(0).get != Lista_cartasPrueba(2),
      jugador_porDefecto.playCard(0).get != Lista_cartasPrueba(1))

    assert(comparacion.contains(false))
  }

  test("equals") {
    val Carta_prueba_2 =new MeleeCard("Melee", new NoneEffect(), 1)
    val Carta_prueba2_2 = new MeleeCard("Melee", new NoneEffect(), 2)
    val Carta_prueba3_2 = new MeleeCard("Melee", new NoneEffect(), 3)
    val Carta_pruebaClima_2 = new ClearWeatherCard("Prueba Clima")
    val Lista_cartasPrueba_2: ListBuffer[ICard] = ListBuffer(Carta_prueba_2, Carta_prueba2_2, Carta_prueba3_2, Carta_pruebaClima_2)
    val Mazo_prueba_2 = new Deck(Lista_cartasPrueba_2)
    val player_2 = new Player(name = "jugador", section_board = "abajo", deck_cards = Mazo_prueba_2, hand_cards = new HandDeck())
    player_2.drawCard(2)
    jugador_porDefecto.drawCard(2)
    assert(jugador_porDefecto.equals(player_2))

    player_2.drawCard(1)
    assert(!jugador_porDefecto.equals(player_2))

    assert(!jugador_porDefecto.equals(Carta_prueba_2))

    assert(!Carta_pruebaClima_2.equals(Carta_prueba2_2))
    assert(!Carta_prueba2_2.equals(Carta_pruebaClima_2))
  }
}
