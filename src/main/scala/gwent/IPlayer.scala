package cl.uchile.dcc
package gwent

import cl.uchile.dcc.gwent.Card.ICard
import gwent.Board.{Board, ISection}

import cl.uchile.dcc.gwent.Card.Unity.ICardUnity
import cl.uchile.dcc.gwent.Card.Weather.ICardWeather

trait IPlayer {
  def playCard(pos_card: Int): Unit
  def playMe[C<:ICardUnity](c: C): Unit
  def playMe[C<: ICardWeather](c: C): Unit
  def drawCard(num_cards: Int): Unit
  def lostRound(): Unit
  def shuffle():Unit
  def numCards_hand(): Int
  def numCards_deck(): Int
  def get_gemCounter(): Int
  def get_Name(): String
  def get_Section(): String
  def get_HandCard(): Option[ICard]
  private[gwent] def set_Section(newSection: ISection): Unit
  private[gwent] def set_Board(newBoard: Board): Unit
}
