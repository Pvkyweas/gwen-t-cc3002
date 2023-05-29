package cl.uchile.dcc
package gwent

import gwent.Deck.{Deck, HandDeck}

import cl.uchile.dcc.gwent.Card.ICard
import gwent.Board.Board

/** A class representing a player
 *
 * @param name Name of the player
 * @param section_board Section of the player on the board, True for upper section and False for lower
 * @param gem_counter Number of gems owned by the player
 * @param deck_cards Deck of cards
 * @param hand_cards Cards in hand
 * @constructor Creates a new player with the specified parameters
 * @example
 * {{{
 *   val player = new Player("example", "exampleSection", 2, Deck(), Deck())
 *   val player_name = player.get_Name()
 *   println(s"the name of the player is $player_name")
 * }}}
 * @see IPlayer
 * @author Israel Rodriguez
 * @since 1.0
 * @version 1.2
 */
class Player(private val name: String,
             private val section_board: Boolean,
             private var gem_counter: Int = 2,
             private val deck_cards: Deck,
             private val hand_cards: HandDeck) extends IPlayer {

  /** Draw a card from the hand by its position and play it in the board
   * 
   * @param pos_card position of the desired card
   * @param b Board in which the card will be played
   */
  def playCard(pos_card: Int, b: Board): Unit = {hand_cards.draw_Card(pos_card).get.playOnBoard(b, section_board)}

  /** Method to obtaining the card in the position 0 in hand deck
   *
   * This method is used to test shuffle method
   *
   * @return an Option of ICard
   */
  def get_HandCard(): Option[ICard] = {
    hand_cards.draw_Card()
  }

  /** Draw a number of cards from the deck
   *
   *  Draws cards from the deck and places them in the hand
   * @param num_cards number of cards to draw
   */
  def drawCard(num_cards: Int): Unit = {hand_cards.add_multipleCard(deck_cards.draw_multipleCard(num_cards))}

  /** subtract 1 from the gem counter */
  def lostRound(): Unit = {if (gem_counter > 0 ) gem_counter = gem_counter - 1}

  /** Randomly change the order of the cards in deck */
  def shuffle(): Unit = deck_cards.shuffle()

  /** Return the number of cards in hand */
  def numCards_hand(): Int = hand_cards.get_Size()

  /** Return the number of cards in deck */
  def numCards_deck(): Int = deck_cards.get_Size()

  /** Return how many gem the player has */
  def get_gemCounter(): Int = gem_counter

  /** Return the name of the player */
  def get_Name(): String = name

  /** Return the section board of the player */
  def get_Section(): String = {if (section_board) "Sección superior" else "Sección Inferior"}

  override def equals(obj: Any): Boolean = {
    if (this.getClass.getName == obj.getClass.getName) {
      val oPlayer = obj.asInstanceOf[Player]
      val equals_params: Boolean = oPlayer.get_Name() == this.get_Name() && oPlayer.get_Section() == this.get_Section() && this.get_gemCounter() == oPlayer.get_gemCounter()
      val equals_decks: Boolean = this.deck_cards.equals(oPlayer.deck_cards) && this.hand_cards.equals(oPlayer.hand_cards)
      equals_params && equals_decks
    }
    else {
      false
    }
  }
}
