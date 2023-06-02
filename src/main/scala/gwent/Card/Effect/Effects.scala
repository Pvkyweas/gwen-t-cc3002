package cl.uchile.dcc
package gwent.Card.Effect
import gwent.Card.ICard

import cl.uchile.dcc.gwent.Card.Unity.{ICardUnity, MeleeCard, RangeCard, SiegeCard}
import cl.uchile.dcc.gwent.Card.Weather.AbstractCardWeather

import scala.collection.mutable.ListBuffer

/**
 *  An abstract class that represent the behaviour of an effect
 */
abstract class Effects extends IEffect {
  /**
   * Method that returns the error when a cards try to apply an effect that it doesn't have
   */
  def error(): Unit = {throw new AssertionError("The Card doesn't have the effect that you want to apply")}

  /** Method to check if the cards has the effect that wants to apply
   *
   * @param c Card who wants to apply the effect
   */
  def hasEffect(c: ICard): Unit = {if (c.get_Effect() != this.get_effect()) error()}

  /** Method to apply an effect when the cards that wants to apply the effect is in the same lane as the objective line
   *
   * @param fC Card with the effect
   * @param tC Cards to apply the effect
   * @tparam C Type of the cards, its the same for fC and tC, has to be subtype of ICardUnity
   */
  def applySameLine[C <: ICardUnity](fC: C, tC: ListBuffer[C]): Unit = {hasEffect(fC)}

  /** Method to apply an effect from a melee card to a list of melee cards
   *
   * @param fC Melee card with the effect
   * @param mCards List with the melee cards
   */
  def applyTo(fC: MeleeCard, mCards: ListBuffer[MeleeCard]): Unit = {applySameLine(fC, mCards)}

  /** Method to apply an effect from a range card to a list of range cards
   *
   * @param fC     Range card with the effect
   * @param rCards List with the range cards
   */
  def applyTo(fC: RangeCard, rCards: ListBuffer[RangeCard]): Unit = {applySameLine(fC, rCards)}

  /** Method to apply an effect from a siege card to a list of siege cards
   *
   * @param fC     Siege card with the effect
   * @param sCards List with the siege cards
   */
  def applyTo(fC: SiegeCard, sCards: ListBuffer[SiegeCard]): Unit = {applySameLine(fC, sCards)}

  /** Method to apply an effect from a weather card to a list of melee cards
   *
   * @param wC Weather card with the effect
   * @param mCards List with the melee cards
   */
  def applyFromWeatherToMelee(wC: AbstractCardWeather, mCards: ListBuffer[MeleeCard]): Unit = {hasEffect(wC)}

  /** Method to apply an effect from a weather card to a list of range cards
   *
   * @param wC     Weather card with the effect
   * @param rCards List with the range cards
   */
  def applyFromWeatherToRange(wC: AbstractCardWeather, rCards: ListBuffer[RangeCard]): Unit = {hasEffect(wC)}

  /** Method to apply an effect from a weather card to a list of siege cards
   *
   * @param wC     Weather card with the effect
   * @param sCards List with the siege cards
   */
  def applyFromWeatherToSiege(wC: AbstractCardWeather, sCards: ListBuffer[SiegeCard]): Unit = {hasEffect(wC)}

  /** Method to get the effect of the card
   *
   * @return a String with the name of the effect
   */
  def get_effect(): String = "No tiene efecto"

  override def equals(obj: Any): Boolean = {
    if (this.getClass.getName == obj.getClass.getName) {
      val oEffect = obj.asInstanceOf[IEffect]
      this.get_effect() == oEffect.get_effect()
    } else {
      false
    }
  }
}
