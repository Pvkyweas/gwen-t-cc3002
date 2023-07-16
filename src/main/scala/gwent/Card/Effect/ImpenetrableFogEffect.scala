package cl.uchile.dcc
package gwent.Card.Effect

import gwent.Card.Unity.{MeleeCard, RangeCard, SiegeCard}

import cl.uchile.dcc.gwent.Card.Effect.Operations.{IOperation, ResetForce, SetForce}
import cl.uchile.dcc.gwent.Card.Visitor.ToRange
import cl.uchile.dcc.gwent.Card.Weather.ICardWeather

import scala.collection.mutable.ListBuffer

/** A class that represent a Weather effect: Impenetrable Fog, its used in weather cards
 *
 * @see IEffect, ImpenetrableFogCard
 * @author Israel Rodriguez
 * @since 1.2.3
 * @version 1.1
 */
class ImpenetrableFogEffect extends Effects with IEffect {

  Effect = new ToRange(new SetForce(1), new ResetForce())

  /* Returns a string that represent the effect */
  override def get_effect(): String = "Niebla impenetrable"

  override def equals(obj: Any): Boolean = {
    if (this.getClass.getName == obj.getClass.getName) {
      super.equals(obj)
    } else {
      false
    }
  }
}
