package cl.uchile.dcc
package gwent.Card.Effect.Operations

import gwent.Board.AbstractUnityZone

import cl.uchile.dcc.gwent.Card.ICard
import cl.uchile.dcc.gwent.Card.Unity.ICardUnity

import scala.collection.mutable.ListBuffer

/** A class that represent a same line operation
 *
 * @param nextOp the next operation that will be executed, by default is a NoOperation object
 */
class SameLine( nextOp: IOperation = new NoOperation) extends IOperation {

  /** This method only call the next operation if the target contain the source
   * because, if the target is in the same line that source then it will contain source
   *
   * @param source Card who wants to apply the operation
   * @param target ListBuffer of card that will be affected
   * @tparam C Generic to specify that the list has to contain subtype card of ICardUnity
   */
  def execute[C <: ICardUnity](source: ICard, target: ListBuffer[C]): Unit = {
    if (target.contains(source)) nextOp.execute(source, target)
  }
}
