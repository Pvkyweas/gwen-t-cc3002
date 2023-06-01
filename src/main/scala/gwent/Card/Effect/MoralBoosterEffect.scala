package cl.uchile.dcc
package gwent.Card
package Effect

import gwent.Card.ICard

/** A class that represent a Moral Boost effect, its used in unity cards
 *
 * @see IEffect
 * @author Israel Rodriguez
 * @since 1.2.3
 * @version 1.0
 */
class MoralBoosterEffect extends EqualsForEffects with IEffect {

  /* Returns a string that represent the effect */
  def get_effect(): String = "Refuerzo Moral"

  override def equals(obj: Any): Boolean = {
    if (this.getClass.getName == obj.getClass.getName) {
      super.equals(obj)
    } else {
      false
    }
  }
}
