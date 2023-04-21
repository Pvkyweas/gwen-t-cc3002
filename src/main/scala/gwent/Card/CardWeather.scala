package cl.uchile.dcc
package gwent.Card

import gwent.ICard

/** A class representing a weather card
 *
 * @param name Name of the card
 * @param effect Effect of the card, by default is "No tiene"
 * @constructor Create a new unity card with the specified name, classification, force and effect, by default
 *              effect is "No tiene"
 * @example
 * {{{
 *   val weatherCard = new CardWeather("example", "exampleEffect")
 *   val nameC = weatherCard.get_Name()
 *   println(s" The name of the card is $nameC")
 * }}}
 * @see ICard, AbstractCard
 * @author Israel Rodriguez
 * @since 1.0
 * @version 1.2
 */
class CardWeather(private val name: String,
                  private val effect: String) extends AbstractCard(name, "Clima", 0, effect) {
  
  /** Returns the effect code which provide the information about how to apply its effect
   *
   * The structure of the code if "requirement type-requirement to apply-how modify-how much modify"
   *
   * Return "cla-d-sf-1" if its effect if "Niebla impenetrable", "cla-c-sf-1" if its
   * "Escarcha mordiente", "cla-s-sf-1" if its "Lluvia torrencial" and "all-rf"  if the effect
   * is other like "Clima despejado"
   */
  override def get_effectCode(): String = {
    if (effect == "Niebla impenetrable") {
      "cla-d-sf-1"
    } else if (effect == "Escarcha mordiente") {
      "cla-c-sf-1"
    } else if (effect == "Lluvia torrencial") {
      "cla-a-sf-1"
    } else {
      "all-rf"
    }
  }

  /** Do nothing because can't apply an effect on a weather card */
  override def effectApply(ICard: ICard): Unit = {println("Does nothing")}

  override def equals(obj: Any): Boolean = {
    if (this.getClass.getName == obj.getClass.getName) {
      super.equals(obj)
    }
    else {
      false
    }
  }
}
