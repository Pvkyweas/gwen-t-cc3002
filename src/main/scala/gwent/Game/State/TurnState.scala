package cl.uchile.dcc
package gwent.Game.State

import gwent.Game.GameController

import cl.uchile.dcc.gwent.IPlayer

class TurnState(controller: GameController, private val player: IPlayer) extends GameState(controller) {

  override def getState: String = {"Turn of:"}

  override def playCard: Unit = {new TurnState(controller, player)}

  override def passTurn: Unit = {new TurnState(controller, player)}

}
