package cl.uchile.dcc
package gwent.Game.State

import gwent.Game.{GameController, InvalidTransitionException, WrongStateException}

class GameState(protected val controller: GameController) {
  /* Set the state of the controller to this state*/
  controller.state = this

  def getState: String = {"asd"}

  def end: Unit = { transitionError("EndState") }

  def start: Unit = {transitionError("RoundState")}

  def playCard: Unit = {stateError()}

  def passTurn: Unit = {transitionError("TurnState or RoundState")}

  def startTurns: Unit = {transitionError("TurnState")}

  private def transitionError(target: String): Unit = {new InvalidTransitionException(target)}

  private def stateError(): Unit = {new WrongStateException("Wrong State")}

}
