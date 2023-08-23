package cl.uchile.dcc
package gwent.Observer_Observable

import gwent.Card.ICard

import cl.uchile.dcc.gwent.Observer_Observable.Notifications.INotification

import scala.collection.mutable.ListBuffer

/**
 * A class that represent the behaviour of an observable object
 */
class Observable extends IObservable {

  /* ListBuffer that contains the objects that observe this object */
  protected val observers: ListBuffer[IObserver] = ListBuffer()

  /** Method to register a new observer to the list
   *
   * @param observer the new observer
   */
  def registerObserver(observer: IObserver): Unit = {
    observers.addOne(observer)
  }

  /** Method to notify to all observers that a card was added
   *
   * @param content the added card
   */
  def notify(content: INotification): Unit = {
    observers.foreach((s: IObserver) => s.getNotification(content))
  }

}
