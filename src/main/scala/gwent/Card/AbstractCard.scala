package cl.uchile.dcc
package gwent.Card

import gwent.ICard

/** An abstract class representing a card
 *
 * @param name Name of the card
 * @param classification Classification of the card
 * @param force Force of the card
 * @param effect Effect of the card
 *
 * @see ICard
 * @author Israel Rodriguez
 * @since 1.1
 * @version 1.0
 */
abstract class AbstractCard(private val name: String,
                            private val classification: String,
                            private var force: Int,
                            private val effect: String) extends ICard{
  

  /* Returns the name of the card */
  def get_Name(): String = name

  /* Returns the force of the card */
  def get_Force(): Int = force

  /** Set the value force to a new one
   *
   * @param newForce the new value of force
   */
  def set_Force(newForce: Int): Unit = {force = newForce}

  /* Return the effect of the card */
  def get_Effect(): String = effect

  /* Return the classification of the card*/
  def get_Classification(): String = classification

}
