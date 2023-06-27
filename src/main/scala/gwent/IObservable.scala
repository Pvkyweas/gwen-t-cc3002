package cl.uchile.dcc
package gwent

import gwent.Card.ICard

trait IObservable {
  def registerObserver(observer: IObserver): Unit
  def notifyCardAdded(card: ICard): Unit
}
