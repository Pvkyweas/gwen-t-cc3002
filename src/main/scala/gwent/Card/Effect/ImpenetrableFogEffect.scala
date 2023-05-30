package cl.uchile.dcc
package gwent.Card.Effect

/** A class that represent a Weather effect: Impenetrable Fog, its used in weather cards
 *
 * @see IEffect, ImpenetrableFogCard
 * @author Israel Rodriguez
 * @since 1.2.3
 * @version 1.0
 */
class ImpenetrableFogEffect extends IEffect{
  /* Returns a string that represent the effect */
  def get_effect(): String = "Niebla impenetrable"

  override def equals(obj: Any): Boolean = {
    if (this.getClass.getName == obj.getClass.getName) {
      val oEffect = obj.asInstanceOf[IEffect]
      this.get_effect() == oEffect.get_effect()
    } else {
      false
    }
  }
}
