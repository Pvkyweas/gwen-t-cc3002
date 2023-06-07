package cl.uchile.dcc

import cl.uchile.dcc.gwent.Game.GameController
import cl.uchile.dcc.gwent.Game.State.{EndState, GameState, RoundState, StartState, TurnState}
import munit.FunSuite
import org.junit.Assert

class GameStateTest extends FunSuite {
  // Controler
  var control: GameController = _

  override def beforeEach(context: BeforeEach): Unit ={
    control = new GameController()
  }

  test("Estado base es StartState"){
    assert(control.getState == "Start")
  }

  test("Start solo transiciona a Round"){
    val endError = Assert.assertThrows(classOf[Exception], () => control.state.end)
    assertEquals("Start can not transition to End", endError.getMessage)

    val turnError = Assert.assertThrows(classOf[Exception], () => control.state.startTurns)
    assertEquals("Start can not transition to Turn", turnError.getMessage)

    control.startGame
    assert(control.getState == "Round")
  }

  test("Round puede transicionar a End"){
    control.startGame
    control.state.end
    assert(control.getState == "End")
  }

  test("Round puede transicionar a Turn") {
    control.startGame
    control.startTuns
    assert(control.getState == "Turn of:") // Supongo que deberia decir de quien es el turno
  }

  test("Turn puede transicionar a Turn"){
    control.startGame
    control.startTuns
    assert(control.getState == "Turn of:")
    control.passTurn
    assert(control.getState == "Turn of:")
  }

  test("Despues de pasar turno 2 veces se vuelve a round"){
    control.startGame
    control.startTuns
    control.passTurn
    control.passTurn
    assert(control.getState == "Round")
  }

  test("Llamar metodos en el estado incorrecto provoca error"){
    val playCardError = Assert.assertThrows(classOf[Exception], () => control.playCard)
    assertEquals("Wrong State", playCardError.getMessage)

  }
}
