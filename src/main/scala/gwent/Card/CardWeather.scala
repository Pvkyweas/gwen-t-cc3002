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
 * @version 1.1
 */
class CardWeather(private val name: String,
                  private val effect: String) extends AbstractCard(name, "Clima", 0, effect) {


  /** Returns the requirement needed to apply its effect
   *
   * Return "distancia" if its effect if "Niebla impenetrable", "cuerpo a cuerpo" if its
   * "Escarcha mordiente", "asedio" if its "Lluvia torrencial" and "No tiene" if the effect
   * is other like "Clima despejado"
   */
  override def get_Requirement(): String = {
    if (effect == "Niebla impenetrable") {
      "distancia"
    } else if (effect == "Escarcha mordiente") {
      "cuerpo a cuerpo"
    } else if (effect == "Lluvia torrencial") {
      "asedio"
    } else {
      "No tiene"
    }
  }

  /** Do nothing because can't apply an effect on a weather card */
  override def effectApply(ICard: ICard): Unit = {}
}
