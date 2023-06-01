package cl.uchile.dcc
package gwent.Card.Effect

/**  A class that represent a Weather effect: Clear Weather, its used in weather cards
 *
 * @see IEffect, ClearWeatherCard
 * @author Israel Rodriguez
 * @since 1.2.3
 * @version 1.0
 */
class ClearWeatherEffect extends EqualsForEffects with IEffect {
  /* Returns a string that represent the effect */
  def get_effect(): String = "Clima despejado"

  override def equals(obj: Any): Boolean = {
    if (this.getClass.getName == obj.getClass.getName) {
      super.equals(obj)
    } else {
      false
    }
  }
}
