package cl.uchile.dcc
package gwent.Card.Effect.Operations

import gwent.Card.Unity.{MeleeCard, RangeCard, SiegeCard}

trait ICardVisitor {
  def visitMelee(card: MeleeCard): Unit
  def visitRange(card: RangeCard): Unit
  def visitSiege(card: SiegeCard): Unit
}
