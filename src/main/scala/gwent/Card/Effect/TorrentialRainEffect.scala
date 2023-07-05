package cl.uchile.dcc
package gwent.Card.Effect

import gwent.Card.Unity.{MeleeCard, RangeCard, SiegeCard}

import cl.uchile.dcc.gwent.Card.Effect.Operations.{ResetForce, SetForce, ToSiege}
import cl.uchile.dcc.gwent.Card.Weather.ICardWeather

import scala.collection.mutable.ListBuffer

/**  A class that represent a Weather effect: Torrential Rain, its used in weather cards
 *
 * @see IEffect, TorrentialRainCard
 * @author Israel Rodriguez
 * @since 1.2.3
 * @version 1.1
 */
class TorrentialRainEffect extends Effects with IEffect {
  
  Effect = new ToSiege(new SetForce(1), new ResetForce())

  /* Returns a string that represent the effect */
  override def get_effect(): String = "Lluvia torrencial"

  override def equals(obj: Any): Boolean = {
    if (this.getClass.getName == obj.getClass.getName) {
      super.equals(obj)
    } else {
      false
    }
  }
}
