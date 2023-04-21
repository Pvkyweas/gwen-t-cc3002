package cl.uchile.dcc
package gwent

trait IPlayer {
  def playCard(pos_card: Int): Option[ICard]
  def drawCard(num_cards: Int): Unit
  def lostRound(): Unit
  def shuffle():Unit
  def numCards_hand(): Int
  def numCards_deck(): Int
  def get_gemCounter(): Int
  def get_Name(): String
  def get_Section(): String
}
