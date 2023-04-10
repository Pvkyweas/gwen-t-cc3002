package cl.uchile.dcc
package gwent

/** A class representing a weather card
 *
 * @param name Name of the card
 * @param effect Effect of the card, by default is "No tiene"
 *
 * @constructor Create a new unity card with the specified name, classification, force and effect, by default
 *              effect is "No tiene"
 *
 * @example
 * {{{
 *   val weatherCard = new CardWeather("example", "exampleEffect")
 *   val nameC = weatherCard.get_Name()
 *   println(s" The name of the card is $nameC")
 * }}}
 *
 * @see ICard
 *
 * @author Israel Rodriguez
 * @since 1.0
 * @version 1.0
 */
class CardWeather(private val name: String, private val effect: String) extends ICard {

  /** Returns the name of the card
   *
   * @return name of the card
   */
  def get_Name(): String = name

  /** Returns 0 because a weather card don't have force */
  def get_Force(): Int = 0

  /** Return the effect of the card
   *
   * @return effect of the card
   */
  def get_Effect(): String = effect

  /** Returns "Clima" because the weather card's type is "Clima" */
  def get_Classification(): String = "Clima"

  /** Returns "No tiene" because weather cards don't have requirement to apply an effect */
  def get_Requirement(): String = "No tiene"

  /** Do nothing because can't apply an effect on a weather card */
  def effectApply(effect: String, requirement: String): Unit = {}
}
