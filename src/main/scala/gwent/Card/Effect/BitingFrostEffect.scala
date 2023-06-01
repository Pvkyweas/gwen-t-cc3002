package cl.uchile.dcc
package gwent.Card.Effect

/**  A class that represent a Weather effect: Biting Frost, its used in weather cards
 *
 * @see IEffect, BitingFrostCard
 * @author Israel Rodriguez
 * @since 1.2.3
 * @version 1.0
 */
class BitingFrostEffect extends EqualsForEffects with IEffect {
  /* Returns a string that represent the effect */
  def get_effect(): String = "Escarcha Mordiente"

  override def equals(obj: Any): Boolean = {
    if (this.getClass.getName == obj.getClass.getName) {
      super.equals(obj)
    } else {
      false
    }
  }
}
