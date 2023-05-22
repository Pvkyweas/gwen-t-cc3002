package cl.uchile.dcc
package gwent.Board

import cl.uchile.dcc.gwent.Card.ICard

import scala.collection.mutable.ListBuffer
abstract class AbstractUnityZone(private val cardsOnZone:ListBuffer[ICard] = new ListBuffer()) extends IZone{

  /** Add a card to the Zone
   *
   * @param Card Card to add
   */
  def add_Card(Card: ICard): Unit = cardsOnZone.addOne(Card)

  /** Method to obtain the cards on Zone
   *
   * @return ListBuffer[ICard] with the cards
   */
  def get_Card: ListBuffer[ICard] = cardsOnZone

}
