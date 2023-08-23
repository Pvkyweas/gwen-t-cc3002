package cl.uchile.dcc
package gwent.Card

import cl.uchile.dcc.gwent.Board.{AbstractUnityZone, ISection}
import cl.uchile.dcc.gwent.Card.Unity.{ICardUnity, MeleeCard, RangeCard, SiegeCard}
import cl.uchile.dcc.gwent.Card.Visitor.ICardVisitor

import scala.collection.mutable.ListBuffer
trait ICard {
  def applyYourEffect[C<:ICardUnity](where: AbstractUnityZone[C]): Unit
  def get_Name(): String
  def get_Effect(): String
  def playYourSelf(s: ISection): Unit
  def accept(visitor: ICardVisitor): Unit
  def get_Force(showBruteForce: Boolean = false): Int
}
