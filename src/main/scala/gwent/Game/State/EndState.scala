package cl.uchile.dcc
package gwent.Game.State

import gwent.Game.GameController

class EndState(controller: GameController) extends GameState(controller){

  override def getState: String = {"End"}
}
