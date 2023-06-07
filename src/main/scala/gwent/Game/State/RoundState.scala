package cl.uchile.dcc
package gwent.Game.State

import gwent.Game.GameController

class RoundState(controller: GameController) extends GameState(controller){

  override def startTurns: Unit = {controller.state = new TurnState(controller)}

  override def end: Unit = {controller.state = new EndState(controller)}

}
