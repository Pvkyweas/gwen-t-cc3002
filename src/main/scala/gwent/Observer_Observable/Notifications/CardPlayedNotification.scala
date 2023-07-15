package cl.uchile.dcc
package gwent.Observer_Observable.Notifications

import gwent.Game.GameController
import gwent.IPlayer

/**
 * A class that represent a notification, used to notify that a player has played a card
 * @param content player who played a card
 */
class CardPlayedNotification(content: IPlayer) extends AbstractNotification {

  /** Method to tell to the game controller that a player has played a card
   * this method call the method playCard of game controller
   *
   * @param game_controller who has played a card
   */
  override def readToController(game_controller: GameController): Unit = {game_controller.playCard(content)}
}
