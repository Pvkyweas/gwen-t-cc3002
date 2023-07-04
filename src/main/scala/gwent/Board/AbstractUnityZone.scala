package cl.uchile.dcc
package gwent.Board

import cl.uchile.dcc.gwent.Card.ICard
import cl.uchile.dcc.gwent.Card.Unity.{ICardUnity, RangeCard}
import cl.uchile.dcc.gwent.Observer_Observable.Notifications.{AddCardNotification, INotification}
import cl.uchile.dcc.gwent.Observer_Observable.{IObservable, IObserver, Observable, ObserverObservable}

import scala.collection.mutable.ListBuffer

/** A Class that represent abstract zone for unity cards
 *
 * @param cardsOnZone ListBuffer of Unity Cards, will contain only melee, range or siege cards
 * @tparam C Type of the Unity Cards that will be in the zone, it can be MeleeCard, RangeCard or SiegeCard
 *
 * @see ICardUnity, IZone
 * @author Israel Rodriguez
 * @since 1.2.3
 * @version 1.2
 */
abstract class AbstractUnityZone[C<:ICardUnity](private val cardsOnZone:ListBuffer[C] = new ListBuffer[C]()) extends ObserverObservable with IZone[C]{

  /** Method to handle the received notification
   *  When a card is added in the board, this notification is received
   *  and the zone says to the notification to read to it
   *
   * @param content the notification
   */
  override def getNotification(content: INotification): Unit = {content.readAboutCard(this)}

  /** Add a card to the Zone and notify to its observer
   *
   * @param Card Card to add
   */
  def add_Card(Card: C): Unit = {
    cardsOnZone.addOne(Card)
    notify(new AddCardNotification(Card))
  }

  /** Method to obtain the cards on Zone
   *
   * @return ListBuffer[ICard] with the cards
   */
  def get_Card: ListBuffer[C] = cardsOnZone

}
