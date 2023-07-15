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

  test("Start solo transiciona al turno del primer jugador, además el jugador debe tener 10 despues de startGame"){
    val endError = Assert.assertThrows(classOf[Exception], () => control.endGame)
    assertEquals("Start can not transition to End", endError.getMessage)

    control.startGame
    assert(control.getState == "Turn of: player1")
    assertEquals(player.numCards_hand(), 10)
  }

  test("No se puede iniciar el juego una vez iniciado"){
    control.startGame
    val startError = Assert.assertThrows(classOf[Exception], () => control.startGame)
    assertEquals("Start can not transition to Turn", startError.getMessage)
  }

  test("Un jugador al jugar una carta, vuelve a tener otro turno"){
    control.startGame
    assert(control.getState == "Turn of: player1")
    control.playCard(player)
    assert(control.getState == "Turn of: player1")
  }

  test("Turn transiciona a ComputerTurn"){
    control.startGame
    assert(control.getState == "Turn of: player1")
    control.passTurn
    assert(control.getState == "Turn of: Computadora")
  }

  test("Despues de pasar turno 2 veces el estado será RoundState") {
    control.startGame
    control.passTurn
    control.passTurn
    assert(control.getState == "Round")
  }

  test("Round puede transicionar a End"){
    control.startGame // -> Pasa al turno 1
    control.passTurn // -> Pasa al turno 2
    control.passTurn // -> Pasa a ronda
    control.endGame // -> pasa a end
    assert(control.getState == "End")
  }

  test("Round puede transicionar a Turn") {
    control.startGame
    control.passTurn
    control.passTurn // -> Pasa a ronda
    control.update() // -> Realiza las acciones de ronda, como ningun debe haber perdido aún debe transicionar a turno
    assert(control.getState == "Turn of: player1")
  }

  test("Llamar metodos en el estado incorrecto provoca error"){
    val playCardError = Assert.assertThrows(classOf[Exception], () => control.playCard(player))
    assertEquals("Wrong State", playCardError.getMessage)

    val passError = Assert.assertThrows(classOf[Exception], () => control.passTurn)
    assertEquals("Wrong State", passError.getMessage)
  }
}
