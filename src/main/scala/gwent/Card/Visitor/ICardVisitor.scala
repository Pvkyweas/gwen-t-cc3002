package cl.uchile.dcc
package gwent.Card.Visitor

import gwent.Card.Unity.{MeleeCard, RangeCard, SiegeCard}

import cl.uchile.dcc.gwent.Card.Weather.ICardWeather

trait ICardVisitor {
  def visitMelee(card: MeleeCard): Unit
  def visitRange(card: RangeCard): Unit
  def visitSiege(card: SiegeCard): Unit
  def visitWeather(card: ICardWeather): Unit
}
