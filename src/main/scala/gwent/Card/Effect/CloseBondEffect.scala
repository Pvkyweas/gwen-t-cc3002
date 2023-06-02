package cl.uchile.dcc
package gwent
package Card
package Effect

import Card.ICard
import cl.uchile.dcc.gwent.Card.Unity.ICardUnity

import scala.collection.mutable.ListBuffer

/** A class that represent a Close Bond effect, its used in unity cards
 *
 * @see IEffect
 * @author Israel Rodriguez
 * @since 1.2.3
 * @version 1.0
 */
class CloseBondEffect extends Effects with IEffect {

  /** Method to apply CloseBond effect, doubles the force of the cards with the same name
   * in the same line, this effect not exclude the card that apply the effect
   *
   * @param fC Card with the effect
   * @param tC Cards to apply the effect
   * @tparam C Type of the cards, its the same for fC and tC, has to be subtype of ICardUnity
   */
  override def applySameLine[C <: ICardUnity](fC: C, tC: ListBuffer[C]): Unit = {
    hasEffect(fC)
    tC.foreach((c: C) => if(c.get_Name() == fC.get_Name()) {
      c.set_Force(c.get_Force(showBruteForce = true)*2)
    })
  }


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
