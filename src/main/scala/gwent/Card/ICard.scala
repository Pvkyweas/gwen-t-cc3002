package cl.uchile.dcc
package gwent.Card
import gwent.Board.Board
trait ICard {
  def get_Name(): String
  def effectApply(oCard: ICard): Unit
  def get_Effect(): String
  def playOnBoard(b: Board, s: Boolean): Unit
}
