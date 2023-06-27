package cl.uchile.dcc
package gwent
package Card
package Effect

import gwent.Card.Unity.{ICardUnity, MeleeCard, RangeCard, SiegeCard}

import cl.uchile.dcc.gwent.Board.AbstractUnityZone
import cl.uchile.dcc.gwent.Card.Weather.AbstractCardWeather

import scala.collection.mutable.ListBuffer

trait IEffect {
  def get_effect(): String
  protected def error(): Unit
  protected def hasEffect(c: ICard): Unit
  def apply[C<:ICardUnity](fC: ICard, zone: ListBuffer[C]): Unit
}
