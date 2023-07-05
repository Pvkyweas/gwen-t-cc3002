package cl.uchile.dcc
package gwent.Card

import cl.uchile.dcc.gwent.Board.{AbstractUnityZone, ISection}
import cl.uchile.dcc.gwent.Card.Unity.{ICardUnity, MeleeCard, RangeCard, SiegeCard}

import scala.collection.mutable.ListBuffer
trait ICard {
  def applyYourEffect[C<:ICardUnity](where: AbstractUnityZone[C]): Unit
  def get_Name(): String
  def get_Effect(): String
  def playYourSelf(s: ISection): Unit
}
