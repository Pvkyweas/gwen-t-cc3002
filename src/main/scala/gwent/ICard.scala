package cl.uchile.dcc
package gwent

trait ICard {
  def get_Name(): String
  def get_Force(): Int
  def get_Effect(): String
  def get_Classification(): String
  def get_effectCode(): String
  def set_Force(newForce: Int): Unit
  def effectApply(oCard: ICard): Unit
}
