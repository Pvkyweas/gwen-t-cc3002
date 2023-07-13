package cl.uchile.dcc

import cl.uchile.dcc.gwent.Card.ICard
import cl.uchile.dcc.gwent.Card.Weather.ClearWeatherCard
import cl.uchile.dcc.gwent.Deck.Deck
import cl.uchile.dcc.gwent.Game.GameController
import cl.uchile.dcc.gwent.Game.State.{EndState, GameState, RoundState, StartState, TurnState}
import cl.uchile.dcc.gwent.{IPlayer, Player}
import munit.FunSuite
import org.junit.Assert

import scala.collection.mutable.ListBuffer

class GameStateTest extends FunSuite {
  // Controler
  var control: GameController = _

  // Player for test
  var player: IPlayer = _

  // Card for test
  var card: ICard = _
  var listCard: ListBuffer[ICard] = _

  override def beforeEach(context: BeforeEach): Unit ={
    card = new ClearWeatherCard("Soleado")
    listCard = ListBuffer(card,card,card,card,card,card,card,card,card,card)
    player = new Player("player1", 2, new Deck(listCard))
    control = new GameController(player)
  }

  test("Estado base es StartState"){
    assert(control.getState == "Start")
  }

  test("Start solo transiciona a Round, además el jugador debe tener 10 despues de startGame"){
    val endError = Assert.assertThrows(classOf[Exception], () => control.endGame)
    assertEquals("Start can not transition to End", endError.getMessage)

    val turnError = Assert.assertThrows(classOf[Exception], () => control.startTurns)
    assertEquals("Start can not transition to Turn", turnError.getMessage)

    val passError = Assert.assertThrows(classOf[Exception], () => control.passTurn)
    assertEquals("Start can not transition to Turn or Round", passError.getMessage)

    control.startGame
    assert(control.getState == "Round")
    assertEquals(player.numCards_hand(), 10)
  }

  test("Round puede transicionar a End"){
    control.startGame
    control.endGame
    assert(control.getState == "End")
  }

  test("Round puede transicionar a Turn") {
    control.startGame
    control.startTurns
    assert(control.getState == "Turn of:") // Supongo que deberia decir de quien es el turno
  }

  test("Turn puede transicionar a Turn"){
    control.startGame
    control.startTurns
    assert(control.getState == "Turn of:")
    control.passTurn
    assert(control.getState == "Turn of:")
  }

  test("Despues de pasar turno 2 veces se vuelve a round"){
    control.startGame
    control.startTurns
    control.passTurn
    control.passTurn
    assert(control.getState == "Round")
  }

  test("Llamar metodos en el estado incorrecto provoca error"){
    val playCardError = Assert.assertThrows(classOf[Exception], () => control.playCard)
    assertEquals("Wrong State", playCardError.getMessage)

  }
}
