package cl.uchile.dcc
package gwent.Observer_Observable

import gwent.Card.ICard
import Notifications.INotification

trait IObservable {
  def registerObserver(observer: IObserver): Unit
  def notify(content: INotification): Unit
}
