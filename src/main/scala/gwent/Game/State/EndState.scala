package cl.uchile.dcc
package gwent.Game.State

import gwent.Game.GameController

import cl.uchile.dcc.gwent.IPlayer

import scala.collection.mutable.ListBuffer

class EndState(controller: GameController,lossers: ListBuffer[IPlayer]) extends GameState(controller){

  override def getState: String = {"End"}
}
