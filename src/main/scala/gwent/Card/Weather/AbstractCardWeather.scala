package cl.uchile.dcc
package gwent.Card.Weather

import gwent.Card.ICard
import gwent.Board.{AbstractUnityZone, Board, ISection, IZone, MeleeZone, RangeZone, SiegeZone}
import gwent.Card.Effect.IEffect
import cl.uchile.dcc.gwent.Card.Unity.{ICardUnity, MeleeCard, RangeCard, SiegeCard}
import cl.uchile.dcc.gwent.Card.Visitor.ICardVisitor
import cl.uchile.dcc.gwent.IPlayer

import scala.collection.mutable.ListBuffer

/** A class representing an abstract weather card
 *
 * @param effect Effect of the card
 * @see ICard
 * @author Israel Rodriguez
 * @since 1.2.3
 * @version 1.1
 */
abstract class AbstractCardWeather(private val name: String, private val effect: IEffect) extends ICardWeather{

  /* Return the name of the card*/
  def get_Name(): String = name

  /* Return the effect of the card */
  def get_Effect(): String = effect.get_effect()

  /** Method to add this unity card to a zone of the Board, the section is who add the card
   *
   * @param s Section in which the card will be added
   */
  def playYourSelf(s: ISection): Unit = {s.addOnWeather(this)}

  /** Method to apply the effect of the card on a specific zone
   *
   * @param where Zone to apply the effect
   * @tparam C Type of the cards on the zone, it can be MeleeCard, RangeCard or SiegeCard
   */
  def applyYourEffect[C<:ICardUnity](where: AbstractUnityZone[C]): Unit = {effect.apply(this, where.get_Card)}

  /** Method to accept a visitor object
   * this be used to know if a card is a weather card
   *
   * @param visitor Visitor object
   */
  def accept(visitor: ICardVisitor): Unit = ???

  /**
   * Return the force of the card, in this case is always 0
   * @param showBruteForce Type of force, unused by weather card
   * @return 0
   */
  def get_Force(showBruteForce: Boolean): Int = 0

  override def equals(obj: Any): Boolean = {
    if (this.getClass.getName == obj.getClass.getName) {
      val oCard = obj.asInstanceOf[AbstractCardWeather]
      this.effect == oCard.effect
    }
    else {
      false
    }
  }
}
