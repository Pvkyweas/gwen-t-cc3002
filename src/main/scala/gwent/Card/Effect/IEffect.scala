package cl.uchile.dcc
package gwent
package Card
package Effect

import gwent.Card.Unity.{ICardUnity, MeleeCard, RangeCard, SiegeCard}

import cl.uchile.dcc.gwent.Card.Weather.AbstractCardWeather

import scala.collection.mutable.ListBuffer

trait IEffect {
  def get_effect(): String
  protected def applySameLine[C<:ICardUnity](fC: C, tC: ListBuffer[C]): Unit
  protected def error(): Unit
  protected def hasEffect(c: ICard): Unit
  def applyTo(fC: MeleeCard, mCards: ListBuffer[MeleeCard]): Unit
  def applyTo(fC: RangeCard, rCards: ListBuffer[RangeCard]): Unit
  def applyTo(fC: SiegeCard, sCards: ListBuffer[SiegeCard]): Unit
  def applyFromWeatherToMelee(wC: AbstractCardWeather, mCards: ListBuffer[MeleeCard]): Unit
  def applyFromWeatherToRange(wC: AbstractCardWeather, rCards: ListBuffer[RangeCard]): Unit
  def applyFromWeatherToSiege(wC: AbstractCardWeather, sCards: ListBuffer[SiegeCard]): Unit

}
