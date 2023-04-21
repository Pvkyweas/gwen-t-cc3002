package cl.uchile.dcc
package gwent.Deck

import gwent.ICard

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

  override def equals(obj: Any): Boolean = {
    if (this.getClass.getName == obj.getClass.getName) {
      val oDeck = obj.asInstanceOf[AbstractDeck]
      if (this.get_Size() == oDeck.get_Size()) {
        var isEqual: Boolean = true
        val this_cardList: ListBuffer[Option[ICard]] = ListBuffer()
        val other_cardList: ListBuffer[Option[ICard]] = ListBuffer()
        val stop: Int = this.get_Size()
        for (c <- 1 to stop) {
          var this_card: ICard = this.draw_Card().get
          var other_card: ICard = oDeck.draw_Card().get
          isEqual = isEqual && this_card.equals(other_card)

          this_cardList.prepend(Some(this_card))
          other_cardList.prepend(Some(other_card))
        }
        this.add_multipleCard(this_cardList)
        oDeck.add_multipleCard(other_cardList)
        isEqual
      } else {
        false
      }
    }
    else {
      false
    }
  }
}
