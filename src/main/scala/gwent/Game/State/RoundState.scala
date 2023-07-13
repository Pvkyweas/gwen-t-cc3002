package cl.uchile.dcc
package gwent.Game.State

import gwent.Game.GameController

class RoundState(controller: GameController) extends GameState(controller){

  override def getState: String = {"Round"}

  override def startTurns: Unit = {new TurnState(controller)}

  override def end: Unit = {new EndState(controller)}

}
