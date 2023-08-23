package cl.uchile.dcc
package gwent.Board

import gwent.Card.Unity.{ICardUnity, MeleeCard, RangeCard, SiegeCard}

import cl.uchile.dcc.gwent.Card.ICard
import cl.uchile.dcc.gwent.Card.Weather.ICardWeather
import cl.uchile.dcc.gwent.IPrintable

import scala.collection.mutable.ListBuffer

trait ISection extends IPrintable{
def addOnMelee(c: MeleeCard): Unit
def addOnRange(c: RangeCard): Unit
def addOnSiege(c: SiegeCard): Unit
def addOnWeather(c: ICardWeather): Unit
def getRangeCard: ListBuffer[RangeCard]
def getMeleeCard: ListBuffer[MeleeCard]
def getSiegeCard: ListBuffer[SiegeCard]
def totalForce(): Int
private[Board] def set_side(s: String): Unit
private[Board] def set_wZone(z: WeatherZone): Unit
def get_side(): String
def clear(): Unit
}
