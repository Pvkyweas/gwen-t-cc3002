package cl.uchile.dcc
package gwent.Card.Visitor

import gwent.Card.Effect.Operations.{IOperation, NoOperation}
import gwent.Card.ICard
import gwent.Card.Unity.{ICardUnity, MeleeCard, RangeCard, SiegeCard}

import scala.collection.mutable.ListBuffer

/** A class that represent a To Melee operation, this has a visitor behaviour
 *
 * @param OpMelee Operation to execute in case the target is a melee list
 * @param OpNoMelee Operation to execute in opposite case
 */
class ToMelee(OpMelee: IOperation = new NoOperation, OpNoMelee: IOperation = new NoOperation) extends AbstractCardVisitor with IOperation {

  /** This method visit each card and add each one to a specific list, then to melee list execute OpMelee and
   * to others lists execute OpNoMelee, then clear each list
   *
   * @param source Card who wants to apply the operation
   * @param target ListBuffer of card that will be affected
   * @tparam C Generic to specify that the list has to contain subtype card of ICardUnity
   */
  def execute[C <: ICardUnity](source: ICard, target: ListBuffer[C]): Unit = {
    target.foreach(c => c.accept(this))
    OpMelee.execute(source, melees)
    OpNoMelee.execute(source, ranges)
    OpNoMelee.execute(source, sieges)

    // All list are cleared
    melees.clear()
    ranges.clear()
    sieges.clear()
  }
}
