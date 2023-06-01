package cl.uchile.dcc
package gwent.Card
package Effect

/** A class that represent an effect that does nothing
 *
 * @see IEffect
 * @author Israel Rodriguez
 * @since 1.2.3
 * @version 1.0
 */
class NoneEffect extends EqualsForEffects with IEffect {

  /* Returns a string that represent the effect */
  def get_effect(): String = "No tiene efecto"

  override def equals(obj: Any): Boolean = {
    if (this.getClass.getName == obj.getClass.getName) {
      super.equals(obj)
    } else {
      false
    }
  }

}
