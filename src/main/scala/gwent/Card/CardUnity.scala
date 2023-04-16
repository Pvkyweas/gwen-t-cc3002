package cl.uchile.dcc
package gwent.Card

import gwent.ICard

/** A class representing a unity card
 *
 * @param name Name of the card
 * @param classification Classification of the card, a for meele, d for distance, a for siege
 * @param force Force of the card
 * @param effect Effect of the card, by default is "No tiene"
 * @constructor Create a new unity card with the specified name, classification, force and effect, by default
 *              effect is "No tiene"
 * @example
 * {{{
 *   val unityCard = new CardUnity("example", "exampleType", 2, "exampleEffect")
 *   val nameC = unityCard.get_Name()
 *   println(s" The name of the card is $nameC")
 * }}}
 * @see ICard, AbstracCard
 * @author Israel Rodriguez
 * @since 1.0
 * @version 1.2
 */
class CardUnity(private val name: String,
                private val classification: String,
                private var force: Int,
                private val effect: String = "No tiene") extends AbstractCard(name, classification, force, effect) {

  /**
   *  The force before an effect is applied
   */
  private var prev_force: Int = force

  /** Return the effect code which provide the information about how to apply its effect
   *
   * The structure of the code if "requirement type-requirement to apply-how modify-how much modify"
   *
   *  Return "cla-classification-addf-1" if the effect is "Refuerzo moral"
   *  or "nam-name-mult-2" if the effect is "Vinculo estrecho", if
   *  the card have no effect then return "No tiene"
   *
   * @return the requirement which is classification, name or "No tiene"
   */
  override def get_effectCode(): String = {
   if (effect == "Refuerzo moral"){
     s"cla-${classification}-addf-1"
   } else if (effect == "Vinculo estrecho"){
     s"nam-${name}-mult-2"
    } else {
     "No tiene"
   }
  }

  /** Apply an effect, climatic effect or unity effect
   *
   * Check if the card has an effect, if it has one, check is it's an unity effect or
   * climatic effect and use the appropriate method
   *
   * @param oCard card with the effect to apply
   *
   */
  override def effectApply(oCard: ICard): Unit = {
    if (oCard.get_Effect() != "No tiene") {
      if (oCard.get_Classification() == "Clima"){
        effectWeather(oCard)
      }
      else {
        effectUnity(oCard)
      }
    }

  }

  /** Apply an unity effect if the requirement is satisfied
   *
   * @param oCard Card with a effect to apply
   */
  def effectUnity(oCard: ICard): Unit = {

  }

  /** Apply an weather effect if the requirement is satisfied
   *
   * @param oCard Card with a effect to apply
   */
  def effectWeather(oCard: ICard): Unit = {

  }
}