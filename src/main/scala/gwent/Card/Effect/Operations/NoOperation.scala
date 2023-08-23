package cl.uchile.dcc
package gwent.Card.Effect.Operations

import gwent.Board.AbstractUnityZone
import gwent.Card.ICard
import gwent.Card.Unity.ICardUnity

import scala.collection.mutable.ListBuffer

/**
 *  A class that represent a No Operation object
 */
class NoOperation extends IOperation {

  /** Do nothing
   *
   * @param source Card who wants to apply the operation
   * @param target ListBuffer of card that will be affected
   * @tparam C Generic to specify that the list has to contain subtype card of ICardUnity
   */
  def execute[C <: ICardUnity](source: ICard, target: ListBuffer[C]): Unit = {}
}
