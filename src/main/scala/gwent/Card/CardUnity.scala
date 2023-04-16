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
   *  The force before a weather effect is applied
   */
  private var prev_force: Int = force

  /* Returns the force of the card */
  override def get_Force(): Int = force

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

  /** Apply an effect according to its effect code if the requirement is satisfied
   *
   * First, check the requirement type and if is satisfied, if it does, check
   * how it will be modified and apply the modification.
   *
   * If the modification isn't a weather effect and affect force, save
   * the actual value of force in prev_force. And, if the weather card is exchanged for another,
   * then change force's value to the previous one.
   *
   * @param oCard Card with a effect to apply
   */
  override def effectApply(oCard: ICard): Unit = {
    val effect_code = oCard.get_effectCode().split("-")
    //requirement type-requirement to apply-how modify-how much modify

    // classification requirement
    if (effect_code(0) == "cla" && effect_code(1).contains(classification)){
      // addition effect
      if (effect_code(2) == "addf"){
        force = force + effect_code(3).toInt
      }
      // set effect
      else if (effect_code(2) == "sf") {
        force = effect_code(3).toInt
      }
    }
    // name requirement
    else if (effect_code(0) == "nam" && effect_code(1) == name){
      // multiplication effect
      if (effect_code(2) == "mult"){
        force = force * effect_code(3).toInt
      }
    }
    // affect all
    else if (effect_code(0) == "all") {
      // reset effect
      if (effect_code(1) == "rf") {
        force = prev_force
      }
    }

    // when the effect applied is not by a weather card, save the new value of force in prev_force
    if (oCard.get_Classification() != "Clima") {
      prev_force = force
    }
    // when the weather class is changed the following happens
    else if (oCard.get_Classification() == "Clima" && !effect_code(1).contains(classification)){
      force = prev_force
    }

  }
}