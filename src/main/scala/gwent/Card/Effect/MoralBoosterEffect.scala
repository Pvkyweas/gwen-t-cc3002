package cl.uchile.dcc
package gwent.Card
package Effect

import gwent.Card.ICard

import cl.uchile.dcc.gwent.Board.AbstractUnityZone
import cl.uchile.dcc.gwent.Card.Effect.Operations.{AddForce, IOperation, NotSameCard, SameLine}
import cl.uchile.dcc.gwent.Card.Unity.ICardUnity

import scala.collection.mutable.ListBuffer

/** A class that represent a Moral Boost effect, its used in unity cards
 *
 * @see IEffect
 * @author Israel Rodriguez
 * @since 1.2.3
 * @version 1.1
 */
class MoralBoosterEffect extends Effects with IEffect {

  Effect = new SameLine(new NotSameCard(new AddForce(1)))

  /* Returns a string that represent the effect */
  override def get_effect(): String = "Refuerzo Moral"

  override def equals(obj: Any): Boolean = {
    if (this.getClass.getName == obj.getClass.getName) {
      super.equals(obj)
    } else {
      false
    }
  }
}
