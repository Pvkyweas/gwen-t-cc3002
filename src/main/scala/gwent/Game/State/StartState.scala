package cl.uchile.dcc
package gwent.Game.State

import gwent.Game.GameController

class StartState(controller: GameController) extends GameState(controller){

  override def getState: String = {"Start"}
  
  override def start: Unit = {controller.state = new RoundState(controller)}
}
