package cl.uchile.dcc
package gwent.Card.Unity

import cl.uchile.dcc.gwent.Card.ICard
import gwent.Board.ISection

import cl.uchile.dcc.gwent.Card.Visitor.ICardVisitor

trait ICardUnity extends ICard {
  private[Card] def set_Force(newForce: Int): Unit
  private[Card] def set_affectedByW(isAffectedNow: Boolean): Unit
  private[Card] def reset():Unit
}
