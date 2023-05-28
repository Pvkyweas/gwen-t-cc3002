package cl.uchile.dcc
package gwent.Board

import cl.uchile.dcc.gwent.Card.ICard
import cl.uchile.dcc.gwent.Card.Unity.{ICardUnity, RangeCard}

import scala.collection.mutable.ListBuffer
abstract class AbstractUnityZone[C<:ICardUnity](protected val cardsOnZone:ListBuffer[C] = new ListBuffer[C]()) extends IZone[C] {

  /** Add a card to the Zone
   *
   * @param Card Card to add
   */
  def add_Card(Card: C): Unit = cardsOnZone.addOne(Card)

  /** Method to obtain the cards on Zone
   *
   * @return ListBuffer[ICard] with the cards
   */
  def get_Card: ListBuffer[C] = cardsOnZone

}
