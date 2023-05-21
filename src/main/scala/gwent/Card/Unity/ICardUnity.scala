package cl.uchile.dcc
package gwent.Card.Unity

import cl.uchile.dcc.gwent.Card.ICard

trait ICardUnity extends ICard {
  def get_Force(): Int
  def set_Force(newForce: Int): Unit
}
