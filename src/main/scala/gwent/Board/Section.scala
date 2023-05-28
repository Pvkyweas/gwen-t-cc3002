package cl.uchile.dcc
package gwent.Board
import gwent.Card.Unity.{ICardUnity, MeleeCard, RangeCard, SiegeCard}

import scala.collection.mutable.ListBuffer
import cl.uchile.dcc.gwent.Card.ICard

class Section(private val mZone: MeleeZone,
              private val rZone: RangeZone,
              private val sZone: SiegeZone) extends ISection{

  /** this method says to card to add itself
   * 
   * @param c card to add
   */
  def add_Card(c: ICardUnity): Unit = c.playOnSection(this)

  /** Add a unity card on Melee zone
   * 
   * @param c Card to add
   */
  def addOnMelee(c: MeleeCard): Unit = mZone.add_Card(c)

  /** Add a unity card on Range zone
   *
   * @param c Card to add
   */
  def addOnRange(c: RangeCard): Unit = rZone.add_Card(c)

  /** Add a unity card on Siege zone
   *
   * @param c Card to add
   */
  def addOnSiege(c: SiegeCard): Unit = sZone.add_Card(c)
  
  /* Returns a listbuffer with melee cards */
  def getMeleeCard: ListBuffer[MeleeCard] = {mZone.get_Card}

  /* Returns a listbuffer with range cards */
  def getRangeCard: ListBuffer[RangeCard] = {rZone.get_Card}

  /* Returns a listbuffer with siege cards */
  def getSiegeCard: ListBuffer[SiegeCard] = {sZone.get_Card}

}
