package cl.uchile.dcc
package gwent.Game.State

import gwent.Game.GameController

import cl.uchile.dcc.gwent.IPlayer

import scala.collection.mutable.ListBuffer

class RoundState(controller: GameController) extends GameState(controller){

  override def getState: String = {"Round"}

  /**
   * Method for the states that are reached several times,
   * it serves so that they do the actions that correspond to them
   *
   * RoundState: -First tells to the player with less force to lose a gem (both in case of tie)
   *             -Check if someone has 0 gems
   *             -if someone has 0 gems -> endgame
   *             -if nobody has 0 gems -> next turn (player1's turn)
   */
  override def update(): Unit = {

    controller.discountGems()

    controller.aPlayerLost()

    controller.clearBoard()

    if (!controller.aPlayerLost()) {controller.startTurns}
    else {controller.endGame}
  }

  /**
   * Method to transition to TurnState
   *
   * @param p The player whose turn it is
   */
  override def startTurns(p: IPlayer): Unit = {new TurnState(controller, p)}

  /**
   * Method to transition to end game
   */
  override def end(listWithLossers: ListBuffer[IPlayer]): Unit = {new EndState(controller, listWithLossers)}

}
