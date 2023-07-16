package cl.uchile.dcc
package gwent

import cl.uchile.dcc.gwent.Game.GameController

object Main {
  def main(args: Array[String]): Unit = {
    val controller: GameController = new GameController()
    controller.startGame
    while(controller.getState != "End"){
      controller.update()
    }
  }
}
