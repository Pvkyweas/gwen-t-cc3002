package cl.uchile.dcc
package gwent.Card.Effect

import gwent.Card.Unity.{MeleeCard, RangeCard, SiegeCard}

import cl.uchile.dcc.gwent.Card.ICard
import cl.uchile.dcc.gwent.Card.Weather.AbstractCardWeather

import scala.collection.mutable.ListBuffer

/**  A class that represent a Weather effect: Biting Frost, its used in weather cards
 *
 * @see IEffect, BitingFrostCard
 * @author Israel Rodriguez
 * @since 1.2.3
 * @version 1.0
 */
class BitingFrostEffect extends Effects with IEffect {

  /** Set to 1 the value of cards's force in the melee line
   *
   * @param wC Weather card with the effect
   * @param mCards List with the melee cards
   */
  override def applyFromWeatherToMelee(wC: AbstractCardWeather, mCards: ListBuffer[MeleeCard]): Unit = {
    hasEffect(wC)
    mCards.foreach((c: MeleeCard) => c.set_Force(1))
    mCards.foreach((c: MeleeCard) => c.set_affectedByW(true))
  }

  /** Reset the value of cards's force in the range line
   *
   * @param wC     Weather card with the effect
   * @param rCards List with the range cards
   */
  override def applyFromWeatherToRange(wC: AbstractCardWeather, rCards: ListBuffer[RangeCard]): Unit = {
    hasEffect(wC)
    rCards.foreach((c: RangeCard) => c.reset())
  }

  /** Reset the value of cards's force in the siege line
   *
   * @param wC     Weather card with the effect
   * @param sCards List with the siege cards
   */
  override def applyFromWeatherToSiege(wC: AbstractCardWeather, sCards: ListBuffer[SiegeCard]): Unit = {
    hasEffect(wC)
    sCards.foreach((c: SiegeCard) => c.reset())
  }

  /* Returns a string that represent the effect */
  override def get_effect(): String = "Escarcha Mordiente"

  override def equals(obj: Any): Boolean = {
    if (this.getClass.getName == obj.getClass.getName) {
      super.equals(obj)
    } else {
      false
    }
  }
}
