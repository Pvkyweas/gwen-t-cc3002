package cl.uchile.dcc
package gwent

/** A class representing a player
 *
 * @param name Name of the player
 * @param section_board Section of the player on the board
 * @param gem_counter Amount of gems the player has, by default is 2
 * @param deck_cards Deck of cards
 * @param hand_cards Cards in hand
 *
 * @constructor Creates a new player with the specified parameters
 *
 * @example
 * {{{
 *   val player = new Player("example", "exampleSection", 2, Deck(), Deck())
 *   val player_name = player.get_Name()
 *   println(s"the name of the player is $player_name")
 * }}}
 *
 * @see IPlayer
 *
 * @author Israel Rodriguez
 * @since 1.0
 * @version 1.0
 */
class Player(private val name: String,
             private val section_board: String,
             private var gem_counter: Int = 2,
             private val deck_cards: IDeck,
             private val hand_cards: IDeck) extends IPlayer {

  /** Play a card from the hand by its position
   *
   * @param pos_card position of the desired card
   * @return an ICard object
   */
  def playCard(pos_card: Int): ICard = {hand_cards.draw_Card().get}

  /** Draw a number of cards from the deck
   *
   *  Draws cards from the deck and places them in the hand
   * @param num_cards number of cards to draw
   */
  def drawCard(num_cards: Int): Unit = {}

  /** subtract 1 from the gem counter */
  def lostRound(): Unit = {gem_counter = gem_counter - 1}

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
  def get_Section(): String = section_board
}
