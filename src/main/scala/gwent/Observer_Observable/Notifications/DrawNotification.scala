package cl.uchile.dcc
package gwent.Observer_Observable.Notifications

import gwent.Game.GameController
import gwent.IPlayer

/**
 * A class that represent a notification of someone that draw cards
 * @param content Who draw cards
 * @param amount Amount of cards drawn
 */
class DrawNotification(content: IPlayer, amount: Int) extends AbstractNotification{

  /** Method to tell to the game controller that a player has drawn a card and how many it draw
   *
   * @param game_controller who will be notified that someone draw cards
   */
  override def readToController(game_controller: GameController): Unit = {game_controller.someDraw(content, amount)}
}
