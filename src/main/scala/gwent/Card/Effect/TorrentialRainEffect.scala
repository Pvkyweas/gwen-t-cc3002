package cl.uchile.dcc
package gwent.Card.Effect

/**  A class that represent a Weather effect: Torrential Rain, its used in weather cards
 *
 * @see IEffect, TorrentialRainCard
 * @author Israel Rodriguez
 * @since 1.2.3
 * @version 1.0
 */
class TorrentialRainEffect extends EqualsForEffects with IEffect {
  /* Returns a string that represent the effect */
  def get_effect(): String = "Lluvia torrencial"

  override def equals(obj: Any): Boolean = {
    if (this.getClass.getName == obj.getClass.getName) {
      super.equals(obj)
    } else {
      false
    }
  }
}
