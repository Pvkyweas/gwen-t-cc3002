package cl.uchile.dcc
package gwent.Card.Effect

import gwent.Card.Unity.{MeleeCard, RangeCard, SiegeCard}

import cl.uchile.dcc.gwent.Card.Effect.Operations.{ResetForce, SetForce, ToMelee}
import cl.uchile.dcc.gwent.Card.ICard
import cl.uchile.dcc.gwent.Card.Weather.ICardWeather

import scala.collection.mutable.ListBuffer

/**  A class that represent a Weather effect: Biting Frost, its used in weather cards
 *
 * @see IEffect, BitingFrostCard
 * @author Israel Rodriguez
 * @since 1.2.3
 * @version 1.1
 */
class BitingFrostEffect extends Effects with IEffect {

  Effect = new ToMelee(new SetForce(1), new ResetForce())

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
