package cl.uchile.dcc
package gwent.Deck

import cl.uchile.dcc.gwent.Card.ICard
import scala.collection.mutable.ListBuffer
import scala.util.Random

/** A class representing a hand deck.
 *
 * @constructor Create an empty hand deck
 *
 * @see AbstractDeck
 *
 * @author Israel Rodriguez
 * @since 1.1
 * @version 1.0
 */
class HandDeck extends AbstractDeck {
  /* The list in which the cards will be stored*/
  private val cards: ListBuffer[ICard] = ListBuffer()

  /** Add the card to the list
   *
   * @param card The card that going to be added
   */
  override def addCard(card: ICard): Unit = {
    cards += card
  }

  /** Draw a card from the list of cards
   *
   * If the list is not empty, return an Option whose contain a ICard object,
   * if is empty then return an Option whose contain None
   *
   * @return Option whose contain an ICard object or None
   *
   */
  override def draw_Card(pos: Int = 0): Option[ICard] = {
    if (this.isEmpty) {
      None
    }
    else {
      val cardDrawed:Option[ICard] = Some(cards(pos))
      cards.remove(pos)
      cardDrawed
    }
  }

  /** Return if the list of cards is empty or not
   * @return The state of the list
   */
  override def isEmpty: Boolean = cards.isEmpty

  /** Return the size of the list
   * @return The number of cards the list has
   */
  override def get_Size(): Int = {
    cards.size
  }

  /**
   * Method to obtain a list with all the cards without modifying the original ListBuffer
   *
   * @return List of cards
   */
  override protected def getList(): List[ICard] = {
    val copyList = cards.clone()
    copyList.toList
  }

  override def equals(obj: Any): Boolean = {
    if (this.getClass.getName == obj.getClass.getName) {
      super.equals(obj)
    }
    else {
      false
    }
  }
}
