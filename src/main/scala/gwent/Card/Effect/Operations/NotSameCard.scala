package cl.uchile.dcc
package gwent.Card.Effect.Operations

import gwent.Board.AbstractUnityZone
import gwent.Card.ICard
import gwent.Card.Unity.ICardUnity

import scala.collection.mutable.ListBuffer

/** A class that represent the Not Same Card operation
 *
 * @param nextOp the next operation that will be executed, by default is a NoOperation object
 */
class NotSameCard(nextOp: IOperation = new NoOperation) extends IOperation {

  /** This method tell the next operation to execute with a filtered target
   * the filtered target not contain the source card
   *
   * @param source Card who wants to apply the operation
   * @param target ListBuffer of card that will be affected
   * @tparam C Generic to specify that the list has to contain subtype card of ICardUnity
   */
  def execute[C <: ICardUnity](source: ICard, target: ListBuffer[C]): Unit = {
    val notSource: ListBuffer[C] = target.filter(c => !source.equals(c))
    nextOp.execute(source, notSource)
  }

}
