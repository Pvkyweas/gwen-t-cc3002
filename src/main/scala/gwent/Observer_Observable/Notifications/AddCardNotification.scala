package cl.uchile.dcc
package gwent.Observer_Observable.Notifications

import gwent.Card.Unity.ICardUnity
import gwent.Card.ICard

import cl.uchile.dcc.gwent.Board.{AbstractUnityZone, IZone}
import cl.uchile.dcc.gwent.Observer_Observable.IObserver

/** Class to represent a notification, used to notify that a card was added
 *
 * @param content The card that was added and want to notify
 */
class AddCardNotification(content: ICard) extends INotification {

  /** Used to apply the effect of the content to a zone that received this notification
   * This Method calls the method applyYourEffect of the content
   *
   * @param zone Zone to apply the effect and who receive the notification
   * @tparam C Type of the Zone
   */
  def readAboutCard[C <: ICardUnity](zone: AbstractUnityZone[C]): Unit = {content.applyYourEffect(zone)}
}
