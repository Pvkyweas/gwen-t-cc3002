package cl.uchile.dcc
package gwent.Observer_Observable.Notifications

import gwent.IPlayer

import cl.uchile.dcc.gwent.Game.GameController

/** A class that represent a notification, used to notify that a player has passed its turn
 *
 * @param content player who pass its turn
 */
class PassTurnNotification (content: IPlayer) extends AbstractNotification {

  /** Method to tell to the game controller that a player has passed its turn
   * this method call the method passTurn of game controller
   *
   * @param game_controller who has to be notified
   */
  override def readToController(game_controller: GameController): Unit = {
    game_controller.passTurn
  }
}
