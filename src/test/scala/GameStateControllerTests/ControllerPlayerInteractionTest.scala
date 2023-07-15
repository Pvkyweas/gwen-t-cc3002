package cl.uchile.dcc
package GameStateControllerTests

import cl.uchile.dcc.gwent.Card.ICard
import cl.uchile.dcc.gwent.Card.Weather.ClearWeatherCard
import cl.uchile.dcc.gwent.Deck.Deck
import cl.uchile.dcc.gwent.Game.GameController
import cl.uchile.dcc.gwent.{IPlayer, Player}
import munit.FunSuite

import scala.collection.mutable.ListBuffer

class ControllerPlayerInteractionTest extends FunSuite{
  // Controler
  var control: GameController = _

  // Player for test
  var player: IPlayer = _

  // Card for test
  var card: ICard = _
  var listCard: ListBuffer[ICard] = _

  override def beforeEach(context: BeforeEach): Unit = {
    card = new ClearWeatherCard("Soleado")
    listCard = ListBuffer(card, card, card, card, card, card, card, card, card, card)
    player = new Player("player1", 2, new Deck(listCard))
    control = new GameController(player)
  }

  test("Si el jugador juega una carta notifica al gamecontroller y vuelve a ser su turno"){
    control.startGame
    assert(control.getState == "Turn of: player1")
    player.playCard(0)
    assert(control.getState == "Turn of: player1")
  }

  test("Si el jugador pasa de turno notifica al gamecontroller y pasa a ser el turno de la computadora"){
    control.startGame
    player.passTurn()
    assert(control.getState == "Turn of: Computadora")
  }

  test("Round le quita gemas al jugador con menos fuerza (puede ser a ambos)"){
    control.startGame
    control.passTurn
    control.passTurn
    control.update()
    assert(player.get_gemCounter() == 1)
  }

  test("Si algún jugador pierde todas sus gemas, round pasa a endgame"){
    control.startGame

    control.passTurn
    control.passTurn
    control.update() // Ambos pasaron entonces ambos pierden una gema en round

    control.passTurn
    control.passTurn
    control.update() // Ambos pasaron entonces ambos pierden una gema en round

    assert(control.getState == "End")
  }

  test("update de computer turn hace cosas"){}
}
