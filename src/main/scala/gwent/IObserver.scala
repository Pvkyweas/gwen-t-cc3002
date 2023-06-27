package cl.uchile.dcc
package gwent

import gwent.Card.ICard
import gwent.IObservable

trait IObserver {
  def getNotification(whoNotify: IObservable, content: ICard): Unit
}
