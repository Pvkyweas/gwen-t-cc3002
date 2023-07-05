package cl.uchile.dcc
package gwent.Card.Weather

import gwent.Card.ICard
import gwent.Board.{AbstractUnityZone, Board, ISection, IZone, MeleeZone, RangeZone, SiegeZone}
import gwent.Card.Effect.IEffect

import cl.uchile.dcc.gwent.Card.Unity.{ICardUnity, MeleeCard, RangeCard, SiegeCard}
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

  /** Method to add this unity card to a zone of the Board, the player is who add the card
   *
   * @param p Player who add the card to board
   */
  def playYourSelf(p: IPlayer): Unit = {p.playMe(this)}

  /** Method to add this weather card to a weather zone of the Board
   * 
   * @param b Board to add the weather card
   */
  def playOnBoard(b: Board): Unit = {
    b.addOnW(this)
  }

  def applyYourEffect[C<:ICardUnity](where: AbstractUnityZone[C]): Unit = {effect.apply(this, where.get_Card)}

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
