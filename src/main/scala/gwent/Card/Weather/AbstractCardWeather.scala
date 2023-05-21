package cl.uchile.dcc
package gwent.Card.Weather

import gwent.Card.ICard

/** A class representing an abstract weather card
 *
 * @param name Name of the card
 * @see ICard
 * @author Israel Rodriguez
 * @since 1.2.3
 * @version 1.0
 */
abstract class AbstractCardWeather(private val name: String) extends ICard{

  def get_Name(): String = name

  /* Return the effect of the card */
  def get_Effect(): String = ???

  /** Apply an effect
   */
  def effectApply(oCard: ICard): Unit = ???

  override def equals(obj: Any): Boolean = {
    if (this.getClass.getName == obj.getClass.getName) {
      val oCard = obj.asInstanceOf[AbstractCardWeather]
      this.name == oCard.name
    }
    else {
      false
    }
  }
}
