package cl.uchile.dcc
package gwent

import gwent.Deck.{Deck, HandDeck}

import cl.uchile.dcc.gwent.Card.ICard
import gwent.Board.{Board, ISection}

import cl.uchile.dcc.gwent.Card.Unity.ICardUnity
import cl.uchile.dcc.gwent.Card.Weather.ICardWeather
import cl.uchile.dcc.gwent.Exceptions.BoardNotFoundException
import cl.uchile.dcc.gwent.Observer_Observable.Notifications.{CardPlayedNotification, DrawNotification, NoGemsNotification, PassTurnNotification}
import cl.uchile.dcc.gwent.Observer_Observable.Observable

import scala.collection.generic.IsSeq

/** A class representing a player
 *
 * @param name Name of the player
 * @param gem_counter Number of gems owned by the player
 * @param deck_cards Deck of cards
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
             private val deck_cards: Deck) extends Observable with IPlayer{

  /* Cards in hand*/
  protected val hand_cards: HandDeck = new HandDeck()

  /* Section in which the player will play their cards*/
  private var section_board: Option[ISection] = None

  private def boardNotFoundError(msj: String): Unit = {throw new BoardNotFoundException(msj)}

  /** Draw a card from the hand by its position and play it in the board
   *  if the board is not defined, throw an assertionError with this message:
   *  "The player doesn't have a Board, add the player to a Board"
   * 
   * @param pos_card position of the desired card
   */
  def playCard(pos_card: Int): Unit = {
    if (section_board.isDefined) {
      hand_cards.draw_Card(pos_card).get.playYourSelf(section_board.get)
      notify(new CardPlayedNotification(this))
    } else {
      boardNotFoundError("The player doesn't have a Board, add the player to a Board")
    }
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
    if (deck_cards.get_Size() != 0) {
      hand_cards.add_multipleCard(deck_cards.draw_multipleCard(num_cards))
      notify(new DrawNotification(this, num_cards))
    }
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

  /**
   * Method to notify to the game controller that this player has passed turn
   */
  def passTurn(): Unit = {
    notify(new PassTurnNotification(this))
  }

  override def Print(): Unit = {
    println(s"Cartas en mazo: ${numCards_deck()}, gemas: $gem_counter, sección: ${get_Section()}")
    hand_cards.Print()
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
