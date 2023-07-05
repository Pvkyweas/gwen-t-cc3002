package cl.uchile.dcc
package gwent.Card.Effect

import gwent.Card.Unity.{MeleeCard, RangeCard, SiegeCard}

import cl.uchile.dcc.gwent.Card.Effect.Operations.ResetForce
import cl.uchile.dcc.gwent.Card.Weather.ICardWeather

import scala.collection.mutable.ListBuffer

/**  A class that represent a Weather effect: Clear Weather, its used in weather cards
 *
 * @see IEffect, ClearWeatherCard
 * @author Israel Rodriguez
 * @since 1.2.3
 * @version 1.1
 */
class ClearWeatherEffect extends Effects with IEffect {

  Effect = new ResetForce()
  
  /* Returns a string that represent the effect */
  override def get_effect(): String = "Clima despejado"

  override def equals(obj: Any): Boolean = {
    if (this.getClass.getName == obj.getClass.getName) {
      super.equals(obj)
    } else {
      false
    }
  }
}
