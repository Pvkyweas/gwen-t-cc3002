package cl.uchile.dcc
package gwent.Observer_Observable.Notifications

import gwent.Observer_Observable.IObserver

import cl.uchile.dcc.gwent.Board.AbstractUnityZone
import cl.uchile.dcc.gwent.Card.Unity.ICardUnity

trait INotification {
  def readAboutCard[C <: ICardUnity](zone: AbstractUnityZone[C]): Unit
}
