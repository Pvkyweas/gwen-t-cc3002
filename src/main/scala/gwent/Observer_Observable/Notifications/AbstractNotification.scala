package cl.uchile.dcc
package gwent.Observer_Observable.Notifications

import gwent.Board.AbstractUnityZone
import gwent.Card.Unity.ICardUnity

import cl.uchile.dcc.gwent.Game.GameController
import cl.uchile.dcc.gwent.IPlayer

/**
 * Abstract class that represent the general behaviour of all notification
 */
abstract class AbstractNotification extends INotification {
  def readAboutCard[C <: ICardUnity](zone: AbstractUnityZone[C]): Unit = {}

  def readToController(game_controller: GameController): Unit = {}
}
