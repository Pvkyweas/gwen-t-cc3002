package cl.uchile.dcc
package gwent.Board
import gwent.Card.Unity.{ICardUnity, MeleeCard, RangeCard, SiegeCard}

import scala.collection.mutable.ListBuffer
import cl.uchile.dcc.gwent.Card.ICard
import cl.uchile.dcc.gwent.Observer_Observable.Notifications.AddCardNotification
import cl.uchile.dcc.gwent.Observer_Observable.{IObservable, IObserver, Observable, ObserverObservable}

/** A class that represent a Section in which 3 different zones can be found
 *
 * @param mZone Zone for melee cards, it has to be a MeleeZone
 * @param rZone Zone for range cards, it has to be a RangeZone
 * @param sZone Zone for siege cards, it has to be a SiegeZone
 * @see ISection, WeatherZone
 * @author Israel Rodriguez
 * @since 1.2.3
 * @version 1.1
 */
class Section(private val mZone: MeleeZone,
              private val rZone: RangeZone,
              private val sZone: SiegeZone) extends ObserverObservable with ISection{

  /* variable to say which side of the board it is on */
  private var whichSide: String = "no definido"
  
  // Register all zones to the observer's list
  registerObserver(mZone)
  registerObserver(rZone)
  registerObserver(sZone)
  // Tells to all zones to register this section to its observer
  mZone.registerObserver(this)
  rZone.registerObserver(this)
  sZone.registerObserver(this)

  /** Method to specify the side of the section
   *
   * @param s new side
   */
  def set_side(s: String): Unit = whichSide = s

  /* Method to return the side of the section */
  def get_side(): String = whichSide

  /** Add a unity card on Melee zone
   * 
   * @param c Card to add
   */
  def addOnMelee(c: MeleeCard): Unit = {
    mZone.add_Card(c)
    notify(new AddCardNotification(c))
  }

  /** Add a unity card on Range zone
   *
   * @param c Card to add
   */
  def addOnRange(c: RangeCard): Unit = {
    rZone.add_Card(c)
    notify(new AddCardNotification(c))
  }

  /** Add a unity card on Siege zone
   *
   * @param c Card to add
   */
  def addOnSiege(c: SiegeCard): Unit = {
    sZone.add_Card(c)
    notify(new AddCardNotification(c))
  }
  
  /* Returns a listbuffer with melee cards */
  def getMeleeCard: ListBuffer[MeleeCard] = {mZone.get_Card}

  /* Returns a listbuffer with range cards */
  def getRangeCard: ListBuffer[RangeCard] = {rZone.get_Card}

  /* Returns a listbuffer with siege cards */
  def getSiegeCard: ListBuffer[SiegeCard] = {sZone.get_Card}

}
