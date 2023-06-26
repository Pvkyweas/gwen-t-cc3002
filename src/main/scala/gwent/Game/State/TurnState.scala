package cl.uchile.dcc
package gwent.Game.State

import gwent.Game.GameController

class TurnState(controller: GameController) extends GameState(controller) {

  override def getState: String = {"Turn of:"}

  override def playCard: Unit = {controller.state = new TurnState(controller)}

  override def passTurn: Unit = {controller.state = new TurnState(controller)}

}
