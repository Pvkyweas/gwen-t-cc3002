package cl.uchile.dcc
package gwent.Card

trait ICard {
  def get_Name(): String
  def effectApply(oCard: ICard): Unit
  def get_Effect(): String
}
