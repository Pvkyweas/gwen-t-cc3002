package cl.uchile.dcc
package gwent.Card.Visitor

import gwent.Card.Effect.Operations.{IOperation, NoOperation}
import gwent.Card.ICard
import gwent.Card.Unity.{ICardUnity, MeleeCard, RangeCard, SiegeCard}

import scala.collection.mutable.ListBuffer

/** A class that represent a To Siege operation, this has a visitor behaviour
 *
 * @param OpSiege Operation to execute in case the target is a siege list
 * @param OpNoSiege Operation to execute in opposite case
 */
class ToSiege(OpSiege: IOperation = new NoOperation, OpNoSiege: IOperation = new NoOperation) extends AbstractCardVisitor with IOperation {

  /** This method visit each card and add each one to a specific list, then to siege list execute OpSiege and
   * to others lists execute OpNoSiege, then clear each list
   *
   * @param source Card who wants to apply the operation
   * @param target ListBuffer of card that will be affected
   * @tparam C Generic to specify that the list has to contain subtype card of ICardUnity
   */
    def execute[C <: ICardUnity](source: ICard, target: ListBuffer[C]): Unit = {
      target.foreach(c => c.accept(this))
      OpNoSiege.execute(source, melees)
      OpNoSiege.execute(source, ranges)
      OpSiege.execute(source, sieges)

      // All list are cleared
      melees.clear()
      ranges.clear()
      sieges.clear()
    }
  }
