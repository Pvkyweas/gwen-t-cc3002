package cl.uchile.dcc
package gwent

import gwent.Deck.{Deck, HandDeck}

import cl.uchile.dcc.gwent.Card.ICard
import gwent.Board.{Board, ISection}

import cl.uchile.dcc.gwent.Card.Unity.ICardUnity
import cl.uchile.dcc.gwent.Card.Weather.AbstractCardWeather
import cl.uchile.dcc.gwent.Exceptions.BoardNotFoundException
import cl.uchile.dcc.gwent.Observer_Observable.Notifications.NoGemsNotification
import cl.uchile.dcc.gwent.Observer_Observable.Observable

import scala.collection.generic.IsSeq

/** A class representing a player
 *
 * @param name Name of the player
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
             private var gem_counter: Int = 2,
             private val deck_cards: Deck,
             private val hand_cards: HandDeck) extends Observable with IPlayer {

  /* Section in which the player will play their cards*/
  private var section_board: Option[ISection] = None
  private var board: Option[Board] = None

  private def boardNotFoundError(msj: String): Unit = {throw new BoardNotFoundException(msj)}

  /** Draw a card from the hand by its position and play it in the board
   *  if the board is not defined, throw an assertionError with this message:
   *  "The player doesn't have a Board, add the player to a Board"
   * 
   * @param pos_card position of the desired card
   */
  def playCard(pos_card: Int): Unit = {
    if (board.isDefined) {
      hand_cards.draw_Card(pos_card).get.playYourSelf(this)
    } else {
      boardNotFoundError("The player doesn't have a Board, add the player to a Board")
    }
  }

  /** Method to add a card, if the card is a weather card then is added to a weather zone, in other hand, if is a
   * unity card then is added to a section
   *
   * @param c Card to add
   * @tparam C Type of the card, it has to be subtype of ICardUnity
   *
   * @see ICardUnity
   */
  def playMe[C<:ICardUnity](c: C): Unit = {
    section_board.foreach((s: ISection) => c.playOnSection(s))
  }

  /** Method to add a card, if the card is a weather card then is added to a weather zone, in other hand, if is a
   * unity card then is added to a section
   *
   * @param c Card to add
   * @tparam C Type of the card, it has to be subtype of AbstractCardWeather
   * @see AbstractCardWeather
   */
  def playMe[C<: AbstractCardWeather](c: C): Unit = {
    board.foreach((b: Board) => c.playOnBoard(b))
  }

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
  def drawCard(num_cards: Int): Unit = {
      hand_cards.add_multipleCard(deck_cards.draw_multipleCard(num_cards))
  }

  /** subtract 1 from the gem counter, if the gem_counter is 0 then notify to the game controller */
  def lostRound(): Unit = {
    if (gem_counter > 0 ) {
      gem_counter = gem_counter - 1
      if (gem_counter == 0) {
        notify(new NoGemsNotification(this))
      }
    }
  }

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

  /** Return the section board of the player, if the section is not defined
   * throw an assertionError with this message:
   * "The player doesn't have a Section, add the player to a Board"
   * */
  def get_Section(): String = {
    if (section_board.isDefined){
      section_board.get.get_side()
    } else {
      boardNotFoundError("The player doesn't have a Section, add the player to a Board")
      "No deberia llegar hasta aqui"
    }
  }

  /** Set the section of the board to the player
   *
   * @param newSection the new section of the player
   */
  def set_Section(newSection: ISection): Unit = {
    section_board = Some(newSection)
  }

  /** Set the board in which the player will play
   *
   * @param newBoard Board in which the player will play
   */
  def set_Board(newBoard: Board): Unit = {
    board = Some(newBoard)
  }

  override def equals(obj: Any): Boolean = {
    if (this.getClass.getName == obj.getClass.getName) {
      val oPlayer = obj.asInstanceOf[Player]
      val equals_params: Boolean = oPlayer.get_Name() == this.get_Name() && this.get_gemCounter() == oPlayer.get_gemCounter()
      val equals_decks: Boolean = this.deck_cards.equals(oPlayer.deck_cards) && this.hand_cards.equals(oPlayer.hand_cards)
      equals_params && equals_decks
    }
    else {
      false
    }
  }
}
