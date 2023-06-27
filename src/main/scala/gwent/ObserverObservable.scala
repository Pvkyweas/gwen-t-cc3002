package cl.uchile.dcc
package gwent

import gwent.Card.ICard

/**
 * A class that represent the behaviour of a Observer and Observable object
 */
class ObserverObservable extends Observable with IObserver{

  /** Method to receive a notify by its observable
   * notify to its observers the notification
   *
   * @param whoNotify from who is received the notification
   * @param content   the content of the notification
   */
  def getNotification(whoNotify: IObservable, content: ICard): Unit = {
    notifyCardAdded(content)
  }
}
