package cl.uchile.dcc
package gwent

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

trait IDeck {
  def addCard(card: ICard): Unit
  def draw_Card(pos: Int = 1): Option[ICard]
  def isEmpty: Boolean
  def get_Size(): Int
  def shuffle(): Unit
}

