package cl.uchile.dcc
package gwent.Card.Effect.Operations

import gwent.Card.Unity.{MeleeCard, RangeCard, SiegeCard}

import scala.collection.mutable.ListBuffer

/** An abstract class that represent the behaviour of a visitor class
 * specifically a card visitor
 *
 */
abstract class AbstractCardVisitor extends ICardVisitor {

  /* ListBuffer that will contain melee card*/
  protected val melees: ListBuffer[MeleeCard] = ListBuffer()
  /* ListBuffer that will contain range card*/
  protected val ranges: ListBuffer[RangeCard] = ListBuffer()
  /* ListBuffer that will contain siege card*/
  protected val sieges: ListBuffer[SiegeCard] = ListBuffer()

  /** Method to visit melee card, this add the card to the melee's list
   *
   * @param card the visited melee card
   */
  def visitMelee(card: MeleeCard): Unit = {
    melees.addOne(card)
  }

  /** Method to visit range card, this add the card to the range's list
   *
   * @param card the visited range card
   */
  def visitRange(card: RangeCard): Unit = {
    ranges.addOne(card)
  }

  /** Method to visit siege card, this add the card to the siege's list
   *
   * @param card the visited siege card
   */
  def visitSiege(card: SiegeCard): Unit = {
    sieges.addOne(card)
  }
}
