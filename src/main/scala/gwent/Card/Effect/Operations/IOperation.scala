package cl.uchile.dcc
package gwent.Card.Effect.Operations

import gwent.Card.ICard
import gwent.Card.Unity.ICardUnity

import scala.collection.mutable.ListBuffer

trait IOperation {
  def execute[C <: ICardUnity](source: ICard, target: ListBuffer[C]): Unit
}
