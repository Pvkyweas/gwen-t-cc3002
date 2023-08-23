package cl.uchile.dcc
package gwent
package Card
package Effect

import Card.ICard
import cl.uchile.dcc.gwent.Card.Effect.Operations.{IOperation, MultiplyForce, SameLine, SameName}
import cl.uchile.dcc.gwent.Card.Unity.ICardUnity

import scala.collection.mutable.ListBuffer

/** A class that represent a Close Bond effect, its used in unity cards
 *
 * @see IEffect
 * @author Israel Rodriguez
 * @since 1.2.3
 * @version 1.1
 */
class CloseBondEffect extends Effects with IEffect {
  
  Effect = new SameLine(new SameName(new MultiplyForce(2)))

  /* Returns a string that represent the effect */
  override def get_effect(): String = "Vinculo Estrecho"

  override def equals(obj: Any): Boolean = {
    if (this.getClass.getName == obj.getClass.getName) {
      super.equals(obj)
    } else {
      false
    }
  }
}
