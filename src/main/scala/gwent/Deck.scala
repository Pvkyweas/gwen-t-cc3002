package cl.uchile.dcc
package gwent

import scala.None

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
 * @see IDeck
 *
 * @author Israel Rodriguez
 * @since 1.0
 * @version 1.1
 */
class Deck extends IDeck{
  private var cards: List[ICard] = List.empty[ICard]

  /** Create a new Deck whose contain a list of cards
   *
   * @param List_cards The list of cards
   */
  def this(List_cards: List[ICard]) = {
    this()
    for(c <- List_cards) {addCard(c)}
  }

  /** Add the card to the list in the first position
   *
   * @param card The card that going to be added
   * @return Nothing
   *
   */
  def addCard(card: ICard): Unit = {
    cards = card :: cards
  }

  /** Draw a card from the list of cards
   *
   * If the list is not empty, return an Option whose contain a ICard object,
   * if is empty then return an Option whose contain None
   *
   * @return Option whose contain an ICard object or None
   *
   */
  def draw_Card(): Option[ICard] = {
    if (cards.isEmpty) {
      None
    }
    else {
      val firstCard: ICard = cards.head
      val rest: List[ICard] = cards.tail
      cards = rest
      Some(firstCard)
    }
  }

  /** Draw a card from a the list of cards by its position
   *
   * @param pos The position of the desired card
   * @return An Option of an ICard object
   */
  def draw_Card(pos: Int): Option[ICard] = {
    if (pos < 0 || pos > cards.size || cards.isEmpty){
      None
    }
    else{
      val newCard: ICard = cards(pos)
      val rest: List[ICard] = cards.patch(pos, Nil, 1)
      cards = rest
      Some(newCard)
    }
  }

  /** Shuffle the list of cards changing its positions
   *
   *  Randomly change the order of the cards
   *
   * @return None
   *
   */
  def shuffle(): Unit = {
    
  }

  /** Return if the list of cards is empty or not
   *
   * @return The state of the list
   */
  def isEmpty: Boolean = cards.isEmpty

  /** Return the size of the list
   *
   * @return The number of cards the list has
   */
  def get_Size(): Int = {
    cards.size
  }
}
