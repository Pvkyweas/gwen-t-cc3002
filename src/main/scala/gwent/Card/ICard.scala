package cl.uchile.dcc
package gwent.Card

import cl.uchile.dcc.gwent.Card.Unity.{ICardUnity, MeleeCard, RangeCard, SiegeCard}
import cl.uchile.dcc.gwent.IPlayer

import scala.collection.mutable.ListBuffer
trait ICard {
  def get_Name(): String
  def get_Effect(): String
  def playYourSelf(p: IPlayer): Unit
}
