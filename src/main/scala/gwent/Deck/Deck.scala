package cl.uchile.dcc
package gwent.Deck

import gwent.ICard

import scala.None
import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.util.Random

/** A class representing a deck.
 *
 * @constructor Create an empty deck
 *
 * @example
 * {{{
 *   val deck = new Deck()
 *   deck.addCard(new CardClimatic("Example", "None"))
 *   val numCards = deck.get_Size()
 *   println(s"deck has $numCards")
 * }}}
 *
 * @see AbstractDeck
 *
 * @author Israel Rodriguez
 * @since 1.0
 * @version 1.1
 */
class Deck extends AbstractDeck{
  /* The stack in which the cards will be stored*/
  private val cards: mutable.Stack[ICard] = mutable.Stack()

  /** Create a new Deck whose contain a stack of cards
   *
   * @param List_cards The list of cards
   */
  def this(List_cards: ListBuffer[ICard]) = {
    this()
    for(c <- List_cards) {addCard(c)}
  }

  /** Add the card to the stack in the first position
   *
   * @param card The card that going to be added
   * @return Nothing
   *
   */
  override def addCard(card: ICard): Unit = {
    cards.push(card)
  }

  /** Draw a card from the stack of cards
   *
   * If the stack is not empty, return an Option whose contain a ICard object,
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
      Some(cards.pop())
    }
  }

  /** Shuffle the stack of cards changing its positions */
  def shuffle(): Unit = {
    Random.shuffle(cards)
  }

  /** Return if the stack of cards is empty or not
   *
   * @return The state of the list
   */
  override def isEmpty: Boolean = cards.isEmpty

  /** Return the size of the stack
   *
   * @return The number of cards the list has
   */
  override def get_Size(): Int = {
    cards.size
  }

  /** Draw as many card as numC is
   *
   * @param numC number of card to draw
   * @return a List which contains a List with Option[ICard]
   */
  def draw_multipleCard(numC: Int): ListBuffer[Option[ICard]] = {
    val resultList: ListBuffer[Option[ICard]] = ListBuffer()
    if (numC <= this.get_Size() && numC>0) {
      for (i <- 1 to numC) {
        val newCard: Option[ICard] = this.draw_Card()
        resultList += newCard
      }
      resultList
    }
    else if(!this.isEmpty && numC>0) {
      draw_multipleCard(this.get_Size())
    }
    else {
      val newCard: Option[ICard] = None
      resultList += newCard
      resultList
    }
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
