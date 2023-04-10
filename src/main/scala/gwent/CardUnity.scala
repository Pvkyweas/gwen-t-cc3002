package cl.uchile.dcc
package gwent

/** A class representing a unity card
 *
 * @param name Name of the card
 * @param classification Classification of the card
 * @param force Force of the card
 * @param effect Effect of the card, by default is "No tiene"
 *
 * @constructor Create a new unity card with the specified name, classification, force and effect, by default
 *              effect is "No tiene"
 *
 * @example
 * {{{
 *   val unityCard = new CardUnity("example", "exampleType", 2, "exampleEffect")
 *   val nameC = unityCard.get_Name()
 *   println(s" The name of the card is $nameC")
 * }}}
 *
 * @see ICard
 *
 * @author Israel Rodriguez
 * @since 1.0
 * @version 1.0
 */
class CardUnity(private val name: String,
                private val classification: String,
                private var force: Int,
                private val effect: String = "No tiene") extends ICard {

  /**
   *  The force before an effect is applied
   *
   */
  private var prev_force: Int = force

  /** Returns the name of the card
   *
   * @return name of the card
   */
  def get_Name(): String = name

  /** Returns the force of the card
   *
   * @return force of the card
   */
  def get_Force(): Int = force

  /** Return the classification of the card
   *
   * @return classification of the card
   */
  def get_Classification(): String = classification

  /** Return the effect of the card
   *
   * @return effect of the card
   */
  def get_Effect(): String = effect

  /** Return the requirement needed to apply an effect
   *
   *  Return classification of the card if the effect is "Refuerzo moral"
   *  or the name of the card if the effect is "Vinculo estrecho", if
   *  the card have no effect then return "No tiene"
   *
   * @return the requirement which is classification, name or "No tiene"
   */
  def get_Requirement(): String = {
   if (effect == "Refuerzo moral"){
     classification
   } else if (effect == "Vinculo estrecho"){
      name
    } else {
     "No tiene"
   }
  }

  /** Apply an effect, climatic effect or unity effect
   *
   * Depending on the effect, check if it satisfy the requirement then if it is satisfied
   * apply the effect
   *
   * @param effect Effect to apply
   * @param requirement Requirement of the effect
   */
  def effectApply(effect: String, requirement: String): Unit = {

  }

  /** Apply an unity effect if the requirement is satisfied
   *
   * @param effect Effect to apply
   * @param requirement Requirement to satisfy
   */
  def effectUnity(effect: String, requirement: String): Unit = {

  }

  /** Apply an weather effect if the requirement is satisfied
   *
   * @param effect Effect to apply
   * @param requirement Requirement to satisfy
   */
  def effectWeather(effect: String, requirement: String): Unit = {

  }
}