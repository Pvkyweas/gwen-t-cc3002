package cl.uchile.dcc
package gwent.Card
package Effect

import gwent.Card.ICard

import cl.uchile.dcc.gwent.Card.Unity.ICardUnity

import scala.collection.mutable.ListBuffer

/** A class that represent a Moral Boost effect, its used in unity cards
 *
 * @see IEffect
 * @author Israel Rodriguez
 * @since 1.2.3
 * @version 1.0
 */
class MoralBoosterEffect extends Effects with IEffect {

  /** Method to apply MoralBooster effect, its add 1 to the cards 
   * in the same line excluding the card that apply the effect
   * 
   * @param fC Card with the effect
   * @param tC Cards to apply the effect
   * @tparam C Type of the cards, its the same for fC and tC, has to be subtype of ICardUnity
   */
  override def applySameLine[C <: ICardUnity](fC: C, tC: ListBuffer[C]): Unit = {
    hasEffect(fC)
    tC.foreach((c: C) => if (!c.equals(fC)) {
      c.set_Force(c.get_Force(showBruteForce = true) + 1)
    })
  }


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
