package cl.uchile.dcc
package gwent.Observer_Observable.Notifications
import gwent.Board.AbstractUnityZone
import gwent.Card.Unity.ICardUnity

import cl.uchile.dcc.gwent.Game.GameController
import cl.uchile.dcc.gwent.IPlayer

/** A class that represent a notification, used to notify that a player has no more gems
 *
 * @param content player who has no gems
 */
class NoGemsNotification(content: IPlayer) extends AbstractNotification {

  /** Method to tell to the game controller that a player has no gems
   * this method call the method playerLoss of game controller
   *
   * @param game_controller who has to be notified
   */
  override def readAboutWhoLoss(game_controller: GameController): Unit = {game_controller.playerLoss(content)}
}
