package cl.uchile.dcc
package gwent.Game.State

import gwent.Game.GameController

import cl.uchile.dcc.gwent.Exceptions.{InvalidTransitionException, WrongStateException}
import cl.uchile.dcc.gwent.IPlayer

import scala.collection.mutable.ListBuffer

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

  def end(listWithLossers: ListBuffer[IPlayer]): Unit = { transitionError("End") }

  def start: Unit = {transitionError("Turn")}

  def playCard(p:IPlayer): Unit = {stateError()}

  def passTurn(pNextTurn: Option[IPlayer]): Unit = {stateError()}

  def startTurns(p: IPlayer): Unit = {transitionError("Turn")}

  /**
   * Method for the states that are reached several times,
   * it serves so that they do the actions that correspond to them
   */
  def update(): Unit = {}

  private def transitionError(target: String): Unit = {throw new InvalidTransitionException(target)}

  private def stateError(): Unit = {throw new WrongStateException("Wrong State")}

}
