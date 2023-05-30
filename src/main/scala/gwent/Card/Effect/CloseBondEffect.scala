package cl.uchile.dcc
package gwent
package Card
package Effect

import Card.ICard
import cl.uchile.dcc.gwent.Card.Unity.ICardUnity

/** A class that represent a Close Bond effect, its used in unity cards
 *
 * @see IEffect
 * @author Israel Rodriguez
 * @since 1.2.3
 * @version 1.0
 */
class CloseBondEffect extends IEffect{
  /* Returns a string that represent the effect */
  def get_effect(): String = "Vinculo Estrecho"

  override def equals(obj: Any): Boolean = {
    if (this.getClass.getName == obj.getClass.getName) {
      val oEffect = obj.asInstanceOf[IEffect]
      this.get_effect() == oEffect.get_effect()
    } else {
      false
    }
  }
}
