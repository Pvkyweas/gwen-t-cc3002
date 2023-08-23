package cl.uchile.dcc
package gwent.Card.Effect.Operations
import gwent.Card.ICard
import gwent.Card.Unity.ICardUnity

import scala.collection.mutable.ListBuffer

/** A class that represent an Add Force operation
 *
 * @param quantity this indicate how much the force going to be added
 * @param nextOp the next operation that will be executed, by default is a NoOperation object
 */
class AddForce(quantity: Int, nextOp: IOperation = new NoOperation) extends IOperation {

  /** Method to add force of each card of the target
   *
   * @param source Card who wants to apply the operation
   * @param target ListBuffer of card that will be affected
   * @tparam C Generic to specify that the list has to contain subtype card of ICardUnity
   */
  def execute[C <: ICardUnity](source: ICard, target: ListBuffer[C]): Unit = {
    target.foreach((c: C) => c.set_Force(c.get_Force(showBruteForce = true) + quantity))
    nextOp.execute(source, target)
  }
}
