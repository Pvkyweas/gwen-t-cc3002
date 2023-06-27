package cl.uchile.dcc
package gwent.Board

import cl.uchile.dcc.gwent.Card.ICard
import cl.uchile.dcc.gwent.Card.Unity.{ICardUnity, RangeCard}
import cl.uchile.dcc.gwent.{IObservable, IObserver, Observable, ObserverObservable}

import scala.collection.mutable.ListBuffer

/** A Class that represent abstract zone for unity cards
 *
 * @param cardsOnZone ListBuffer of Unity Cards, will contain only melee, range or siege cards
 * @tparam C Type of the Unity Cards that will be in the zone, it can be MeleeCard, RangeCard or SiegeCard
 *
 * @see ICardUnity, IZone
 * @author Israel Rodriguez
 * @since 1.2.3
 * @version 1.1
 */
abstract class AbstractUnityZone[C<:ICardUnity](private val cardsOnZone:ListBuffer[C] = new ListBuffer[C]()) extends ObserverObservable with IZone[C]{

  /** Method to handle the received notification
   *  When a card is added in the board, this notification is received
   *  so the zone tells to the new card to apply its effect in the zone
   *
   * @param whoNotify from who is the notification
   * @param content the content of the notification
   */
  override def getNotification(whoNotify: IObservable, content: ICard): Unit = {content.applyYourEffect(this)}

  /** Add a card to the Zone and notify to its observer
   *
   * @param Card Card to add
   */
  def add_Card(Card: C): Unit = {
    cardsOnZone.addOne(Card)
    notifyCardAdded(Card)
  }

  /** Method to obtain the cards on Zone
   *
   * @return ListBuffer[ICard] with the cards
   */
  def get_Card: ListBuffer[C] = cardsOnZone

}
