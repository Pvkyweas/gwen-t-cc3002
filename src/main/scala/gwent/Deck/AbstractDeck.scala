package cl.uchile.dcc
package gwent.Deck

import cl.uchile.dcc.gwent.Card.ICard
import scala.collection.mutable.ListBuffer

/** An abstract class representing a deck and a hand deck.
 *
 * @author Israel Rodriguez
 * @since 1.1
 * @version 1.0
 */
abstract class AbstractDeck{

  /** From a list of card, add each card to the deck
   *
   * @param List_cards List with the card to add
   */
  def add_multipleCard(List_cards: ListBuffer[Option[ICard]]): Unit = {
    for (c <- List_cards) {
      if (c.isDefined) {
        addCard(c.get)
      }
    }
  }
  
  def addCard(card: ICard): Unit = ???
  def get_Size(): Int = ???
  def draw_Card(pos: Int=0): Option[ICard] = ???
  def isEmpty: Boolean = ???
  protected def getList(): List[ICard] = ???

  override def equals(obj: Any): Boolean = {
    if (this.getClass.getName == obj.getClass.getName) {
      val oDeck = obj.asInstanceOf[AbstractDeck]
      if (this.get_Size() == oDeck.get_Size()) {
        this.getList() == oDeck.getList()
      } else{
        false
      }
    } else {
      false
    }
  }

}
