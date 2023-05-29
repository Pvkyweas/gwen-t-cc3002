package cl.uchile.dcc
package gwent.Card.Unity

import cl.uchile.dcc.gwent.Card.ICard
import gwent.Board.ISection

trait ICardUnity extends ICard {
  def get_Force(): Int
  private[Card] def set_Force(newForce: Int): Unit
  def playOnSection(Section: ISection): Unit
}
