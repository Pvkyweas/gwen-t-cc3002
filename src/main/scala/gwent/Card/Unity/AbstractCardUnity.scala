package cl.uchile.dcc
package gwent.Card.Unity

import gwent.Card.Unity.ICardUnity
import gwent.Board.{Board, ISection}
import gwent.Card.Effect.IEffect
import gwent.Card.ICard

/** A class representing an abstract unity card
 *
 * @param name Name of the card
 * @param force Force of the card
 * @param effect Effect of the card, it is an IEffect object
 * @see ICard, ICardUnity
 * @author Israel Rodriguez
 * @since 1.2.3
 * @version 1.0
 */
abstract class AbstractCardUnity(private val name: String,
                                 private val effect: IEffect,
                                 private var force: Int) extends ICardUnity {

  /** The force before an effect is applied */
  private var prev_force: Int = force

  /* Returns the name of the card */
  def get_Name(): String = name

  /* Return the effect of the card */
  def get_Effect(): String = effect.get_effect()

  /* Returns the force of the card */
  def get_Force(): Int = force

  /** Set the value force to a new one */
  def set_Force(newForce: Int): Unit = {
    this.prev_force = this.force
    this.force = newForce
  }

  /** Method to add this unity card to a zone of the Board
   *
   * @param b Board to add the unity card
   * @param s Section of the player
   */
  def playOnBoard(b: Board, s: Boolean): Unit = {b.addCard(this, s)}
  
  def playOnSection(Section: ISection): Unit = ???

  /** Apply an effect
   */
  def effectApply(oCard: ICard): Unit = {}


  override def equals(obj: Any): Boolean = {
    if (this.getClass.getName == obj.getClass.getName) {
      val oCard = obj.asInstanceOf[AbstractCardUnity]
      this.name == oCard.name && this.force == oCard.force && this.effect.equals(oCard.effect)
    }
    else {
      false
    }
  }
}
