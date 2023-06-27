package cl.uchile.dcc
package gwent.Card.Unity

import gwent.Card.Effect.IEffect

import cl.uchile.dcc.gwent.Board.ISection
import cl.uchile.dcc.gwent.Card.Effect.Operations.ICardVisitor

import scala.collection.mutable.ListBuffer

/** A class representing a melee unity card
 *
 * @param name Name of the card
 * @param force Force of the card
 * @param effect Effect of the card, it is an IEffect object
 * @constructor Create a new unity card with the specified name, force and effect, by default
 *              effect is a NoneEffect
 * @example
 * {{{
 *   val unityCard = new MeleeCard("example", 2, new noneEffect())
 *   val nameC = unityCard.get_Name()
 *   println(s" The name of the card is $nameC")
 * }}}
 * @see ICard, ICardUnity, AbstractUnityCard
 * @author Israel Rodriguez
 * @since 1.2.3
 * @version 1.1
 */
class MeleeCard(private val name: String, private val effect: IEffect,
                private var force: Int) extends AbstractCardUnity(name, effect, force){

  /** Add this card on melee zone of the section
   * 
   * @param Section Section that own the melee zone
   */
  override def playOnSection(Section: ISection): Unit = {
    Section.addOnMelee(this)
  }

  /** Method to accept a visitor object
   * this be used to apply specific effects to melee cards
   *
   * @param visitor Visitor object
   */
  override def accept(visitor: ICardVisitor): Unit = visitor.visitMelee(this)
  
  override def equals(obj: Any): Boolean = {
    if (this.getClass.getName == obj.getClass.getName) {
      super.equals(obj)
    }
    else {
      false
    }
  }
}
