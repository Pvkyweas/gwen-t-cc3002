package cl.uchile.dcc
package gwent.Game.State

import gwent.Game.GameController

import cl.uchile.dcc.gwent.Exceptions.{InvalidTransitionException, WrongStateException}
import cl.uchile.dcc.gwent.IPlayer

/**
 *  A class that represent all states of a game controller
 * @param controller The controller that has this state
 */
class GameState(protected val controller: GameController) {
  /* Set the state of the controller to this state*/
  controller.setState(this)

  /** To know which is the current state
   *
   * @return A String with the name of the current state
   */
  def getState: String = {""}

  def end: Unit = { transitionError("End") }

  def start: Unit = {transitionError("Round")}

  def playCard: Unit = {stateError()}

  def passTurn: Unit = {transitionError("Turn or Round")}

  def startTurns(p: IPlayer): Unit = {transitionError("Turn")}

  private def transitionError(target: String): Unit = {throw new InvalidTransitionException(target)}

  private def stateError(): Unit = {throw new WrongStateException("Wrong State")}

}
