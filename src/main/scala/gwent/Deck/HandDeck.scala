package cl.uchile.dcc
package gwent.Deck

import gwent.{ICard, IDeck}

import scala.collection.mutable.ListBuffer
import scala.util.Random

/** A class representing a hand deck.
 *
 * @constructor Create an empty hand deck
 *
 * @see IDeck, Deck
 *
 * @author Israel Rodriguez
 * @since 1.1
 * @version 1.0
 */
class HandDeck extends IDeck {
  /* The list in which the cards will be stored*/
  private val cards: ListBuffer[ICard] = ListBuffer()

  /** Create a new Deck whose contain a list of cards
   *
   * @param List_cards The list of cards
   */
  def this(List_cards: List[ICard]) = {
    this()
    for (c <- List_cards) {
      addCard(c)
    }
  }

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
  override def draw_Card(pos: Int = 1): Option[ICard] = {
    if (this.isEmpty) {
      None
    }
    else {
      val cardDrawed:Option[ICard] = Some(cards(pos))
      cards.remove(pos)
      cardDrawed
    }
  }

  /** Shuffle the list of cards changing its positions */
  override def shuffle(): Unit = {
    Random.shuffle(cards)
  }

  /** Return if the list of cards is empty or not
   *
   * @return The state of the list
   */
  override def isEmpty: Boolean = cards.isEmpty

  /** Return the size of the list
   *
   * @return The number of cards the list has
   */
  override def get_Size(): Int = {
    cards.size
  }

  /** From a list of card, add each card to the deck
   *
   * @param List_cards List with the card to add
   */
  def add_multipleCard(List_cards: ListBuffer[Option[ICard]]): Unit = {
    for (c <- List_cards) {
      if (c.isDefined){
      addCard(c.get)
      }
    }
  }
}
