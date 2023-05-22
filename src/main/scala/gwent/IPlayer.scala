package cl.uchile.dcc
package gwent

import cl.uchile.dcc.gwent.Card.ICard
import gwent.Board.Board

trait IPlayer {
  def playCard(pos_card: Int, b: Board): Unit
  def drawCard(num_cards: Int): Unit
  def lostRound(): Unit
  def shuffle():Unit
  def numCards_hand(): Int
  def numCards_deck(): Int
  def get_gemCounter(): Int
  def get_Name(): String
  def get_Section(): String
  def get_HandCard(): Option[ICard]
}
