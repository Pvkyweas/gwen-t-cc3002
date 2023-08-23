package cl.uchile.dcc
package gwent.Card.Effect.Operations
import gwent.Card.ICard
import gwent.Card.Unity.ICardUnity

import scala.collection.mutable.ListBuffer

/** A class that represent the reset force operation
 *
 * @param nextOp the next operation that will be executed, by default is a NoOperation object
 */
class ResetForce(nextOp: IOperation = new NoOperation) extends IOperation {

  /** This method tell to each card of the target that has to reset its force
   *
   * @param source Card who wants to apply the operation
   * @param target ListBuffer of card that will be affected
   * @tparam C Generic to specify that the list has to contain subtype card of ICardUnity
   */
  def execute[C <: ICardUnity](source: ICard, target: ListBuffer[C]): Unit = {
    target.foreach(c => c.reset())
    nextOp.execute(source, target)
  }
}
