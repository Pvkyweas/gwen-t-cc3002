package cl.uchile.dcc
package gwent.Board

import gwent.Card.Unity.ICardUnity
import cl.uchile.dcc.gwent.Card.ICard
import scala.collection.mutable.ListBuffer

trait ISection {
def add_Card(c: ICardUnity): Unit
def addOnMelee(c: ICardUnity): Unit
def addOnRange(c: ICardUnity): Unit
def addOnSiege(c: ICardUnity): Unit
def getRangeCard: ListBuffer[ICard]
def getMeleeCard: ListBuffer[ICard]
def getSiegeCard: ListBuffer[ICard]
}
