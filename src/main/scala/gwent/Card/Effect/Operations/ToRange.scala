package cl.uchile.dcc
package gwent.Card.Effect.Operations
import gwent.Card.ICard
import gwent.Card.Unity.{ICardUnity, MeleeCard, RangeCard, SiegeCard}

import scala.collection.mutable.ListBuffer

/** A class that represent a To Range operation, this has a visitor behaviour
 *
 * @param OpRange Operation to execute in case the target is a range list
 * @param OpNoRange Operation to execute in opposite case
 */
class ToRange(OpRange: IOperation = new NoOperation, OpNoRange: IOperation = new NoOperation) extends AbstractCardVisitor with IOperation {

  /** This method visit each card and add each one to a specific list, then to range list execute OpRange and
   * to others lists execute OpNoRange, then clear each list
   *
   * @param source Card who wants to apply the operation
   * @param target ListBuffer of card that will be affected
   * @tparam C Generic to specify that the list has to contain subtype card of ICardUnity
   */
  def execute[C <: ICardUnity](source: ICard, target: ListBuffer[C]): Unit = {
    target.foreach(c => c.accept(this))
    OpNoRange.execute(source, melees)
    OpRange.execute(source, ranges)
    OpNoRange.execute(source, sieges)

    // All list are cleared
    melees.clear()
    ranges.clear()
    sieges.clear()
  }
}
