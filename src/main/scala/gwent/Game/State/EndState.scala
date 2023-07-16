package cl.uchile.dcc
package gwent.Game.State

import gwent.Game.GameController

import cl.uchile.dcc.gwent.IPlayer

import scala.collection.mutable.ListBuffer

/**
 * A class that represent the final state, the end game state
 * @param controller The controller that has this state
 * @param losers List with players that reach 0 gems
 */
class EndState(controller: GameController, losers: ListBuffer[IPlayer]) extends GameState(controller){

  override def getState: String = {"End"}
}
