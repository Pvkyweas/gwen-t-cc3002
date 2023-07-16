package cl.uchile.dcc
package gwent.Board

import cl.uchile.dcc.gwent.Card.ICard
import cl.uchile.dcc.gwent.IPrintable

import scala.collection.mutable.ListBuffer

trait IZone[C<:ICard] extends IPrintable{
  def add_Card(Card:C): Unit
  def totalForce(): Int
  def clear(): Unit
}
