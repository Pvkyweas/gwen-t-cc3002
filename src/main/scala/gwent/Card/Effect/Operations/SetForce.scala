package cl.uchile.dcc
package gwent.Card.Effect.Operations
import gwent.Card.ICard
import gwent.Card.Unity.ICardUnity

import scala.collection.mutable.ListBuffer

/** A class that represent an set force operation
 *
 * @param quantity this indicate to what value the force will be set
 * @param nextOp the next operation that will be executed, by default is a NoOperation object
 */
class SetForce(quantity: Int,nextOp: IOperation = new NoOperation) extends IOperation {

  /** This method set the force to quantity of each card of the target and
   * change the affectedByW variable to true
   *
   * @param source Card who wants to apply the operation
   * @param target ListBuffer of card that will be affected
   * @tparam C Generic to specify that the list has to contain subtype card of ICardUnity
   */
  def execute[C <: ICardUnity](source: ICard, target: ListBuffer[C]): Unit = {
    target.foreach(c => c.set_Force(quantity))
    target.foreach(c => c.set_affectedByW(true))
    nextOp.execute(source, target)
  }
}
