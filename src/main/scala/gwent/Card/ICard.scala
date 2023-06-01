package cl.uchile.dcc
package gwent.Card
import gwent.Board.Board

import cl.uchile.dcc.gwent.IPlayer
trait ICard {
  def get_Name(): String
  def effectApply(oCard: ICard): Unit
  def get_Effect(): String
  def playYourSelf(p: IPlayer): Unit
}
