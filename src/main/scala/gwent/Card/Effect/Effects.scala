package cl.uchile.dcc
package gwent.Card.Effect
import gwent.Card.ICard

import cl.uchile.dcc.gwent.Board.AbstractUnityZone
import cl.uchile.dcc.gwent.Card.Effect.Operations.{IOperation, NoOperation}
import cl.uchile.dcc.gwent.Card.Unity.{ICardUnity, MeleeCard, RangeCard, SiegeCard}
import cl.uchile.dcc.gwent.Card.Weather.ICardWeather
import cl.uchile.dcc.gwent.Exceptions.CardHasNotThisEffectException

import scala.collection.mutable.ListBuffer

/**
 *  An abstract class that represent the behaviour of an effect
 */
abstract class Effects extends IEffect {

  /* Operation that represent the effect */
  protected var Effect: IOperation = new NoOperation

  /**
   * Method that returns the error when a cards try to apply an effect that it doesn't have
   */
  protected def error(): Unit = {throw new CardHasNotThisEffectException("The Card doesn't have the effect that you want to apply")}

  /** Method to check if the cards has the effect that wants to apply
   *
   * @param c Card who wants to apply the effect
   */
  protected def hasEffect(c: ICard): Unit = {
    if (c.get_Effect() != this.get_effect()) error()
  }

  /** Method to apply the effect
   * First check if the card who wants to apply the effect has it
   * and then execute the Operations that represent the effect
   *
   * @param fC Card which is the source of the effect
   * @param zone Target to apply the effect, is a ListBuffer of cards
   * @tparam C Generic for zone parameter, must be subtype of ICardUnity
   */
  override def apply[C <: ICardUnity](fC: ICard, zone: ListBuffer[C]): Unit = {
    hasEffect(fC)
    Effect.execute(fC, zone)
  }

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
