package cl.uchile.dcc
package gwent.Game.State

import gwent.Game.GameController

import cl.uchile.dcc.gwent.IPlayer

class RoundState(controller: GameController) extends GameState(controller){

  override def getState: String = {"Round"}

  override def startTurns(p: IPlayer): Unit = {new TurnState(controller, p)}

  override def end: Unit = {new EndState(controller)}

}
