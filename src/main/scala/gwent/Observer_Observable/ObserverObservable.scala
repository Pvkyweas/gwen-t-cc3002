package cl.uchile.dcc
package gwent.Observer_Observable

import gwent.Card.ICard

import cl.uchile.dcc.gwent.Observer_Observable.Notifications.INotification

/**
 * A class that represent the behaviour of a Observer and Observable object
 */
class ObserverObservable extends Observable with IObserver{

  /** Method to receive a notify by its observable
   * notify to its observers the notification
   *
   * @param content   the content of the notification
   */
  def getNotification(content: INotification): Unit = {
    notify(content)
  }
}
