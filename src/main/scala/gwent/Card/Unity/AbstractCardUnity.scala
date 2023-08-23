package cl.uchile.dcc
package gwent.Card.Unity

import gwent.Card.Unity.ICardUnity
import gwent.Board.{AbstractUnityZone, Board, ISection, IZone}
import gwent.Card.Effect.IEffect
import gwent.Card.ICard
import cl.uchile.dcc.gwent.Card.Visitor.ICardVisitor
import cl.uchile.dcc.gwent.IPlayer

import scala.collection.mutable.ListBuffer

/** A class representing an abstract unity card
 *
 * @param name Name of the card
 * @param force Force of the card
 * @param effect Effect of the card, it is an IEffect object
 * @see ICard, ICardUnity
 * @author Israel Rodriguez
 * @since 1.2.3
 * @version 1.1
 */
abstract class AbstractCardUnity(private val name: String,
                                 private val effect: IEffect,
                                 private var force: Int) extends ICardUnity {

  /** Variable to save the force's value when the card is affected by a weather effect,
   * if the card is affected by a weather effect, then MR and CB affect this variable*/
  private var brute_force: Int = force

  /** Variable to know if the card is affected by an Weather effect*/
  private var affectedByW: Boolean = false

  /* Returns the name of the card */
  def get_Name(): String = name

  /* Return the effect of the card */
  def get_Effect(): String = {
    effect.get_effect()
  }

  /** Method to obtain the force value or brute_force value
   *
   * @param showBruteForce Boolean, true to show brute_force and false to show force, by default is false
   * @return An int that is brute_force or force
   */
  def get_Force(showBruteForce: Boolean = false): Int = {if (showBruteForce) this.brute_force else this.force}

  /** Method to change the value of the force
   *
   * If the card is not affected by a weather effect, then force is set to newForce
   * and brute_force saves the previous value of force. In other hand, if the card is
   * affected by a weather effect then brute_force is set to newForce
   *
   * @param newForce New value to Force
   */
  def set_Force(newForce: Int): Unit = {
    if (!affectedByW){
      this.brute_force = this.force
      this.force = newForce
    } else {
      this.brute_force = newForce
    }
  }

  /** Change the value of the variable affectedByW for isAffectedNow
   * 
   * @param isAffectedNow new value of affectedByW
   */
  def set_affectedByW(isAffectedNow: Boolean): Unit = {
    affectedByW = isAffectedNow
  }

  /** Method to reset the value of force to brute_force and set affectedByW to false
   */
  def reset(): Unit = {
    this.force = this.brute_force
    affectedByW = false
  }

  /** Method to apply the effect of the card on a specific zone
   *
   * @param where Zone to apply the effect
   * @tparam C Type of the cards on the zone, it can be MeleeCard, RangeCard or SiegeCard
   */
  def applyYourEffect[C<:ICardUnity](where: AbstractUnityZone[C]): Unit = {effect.apply(this, where.get_Card)}
  
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
