package cl.uchile.dcc
package gwent.Card.Unity

import cl.uchile.dcc.gwent.Card.ICard
import gwent.Board.ISection

trait ICardUnity extends ICard {
  def get_Force(showBruteForce: Boolean = false): Int
  private[Card] def set_Force(newForce: Int): Unit
  private[Card] def set_affectedByW(isAffectedNow: Boolean): Unit
  private[Card] def reset():Unit
  def playOnSection(Section: ISection): Unit
}
