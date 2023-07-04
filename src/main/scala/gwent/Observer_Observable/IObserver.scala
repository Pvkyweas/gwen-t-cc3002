package cl.uchile.dcc
package gwent.Observer_Observable

import gwent.Card.ICard
import Notifications.INotification

trait IObserver {
  def getNotification(content: INotification): Unit
}
