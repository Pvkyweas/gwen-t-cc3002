package cl.uchile.dcc
package gwent.Board

import gwent.Card.Unity.{ICardUnity, MeleeCard, RangeCard, SiegeCard}

import cl.uchile.dcc.gwent.Card.ICard

import scala.collection.mutable.ListBuffer

trait ISection {
def addOnMelee(c: MeleeCard): Unit
def addOnRange(c: RangeCard): Unit
def addOnSiege(c: SiegeCard): Unit
def getRangeCard: ListBuffer[RangeCard]
def getMeleeCard: ListBuffer[MeleeCard]
def getSiegeCard: ListBuffer[SiegeCard]
private[Board] def set_side(s: String): Unit
def get_side(): String
}
