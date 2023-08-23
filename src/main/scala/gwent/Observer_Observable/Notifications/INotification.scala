package cl.uchile.dcc
package gwent.Observer_Observable.Notifications

import gwent.Observer_Observable.IObserver

import cl.uchile.dcc.gwent.Board.AbstractUnityZone
import cl.uchile.dcc.gwent.Card.Unity.ICardUnity
import cl.uchile.dcc.gwent.Game.GameController
import cl.uchile.dcc.gwent.IPlayer

trait INotification {
  def readAboutCard[C <: ICardUnity](zone: AbstractUnityZone[C]): Unit
  def readToController(game_controller: GameController): Unit
}
