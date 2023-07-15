package cl.uchile.dcc
package gwent.Board

import gwent.IPlayer

import cl.uchile.dcc.gwent.Card.Unity.{ICardUnity, MeleeCard, RangeCard, SiegeCard}
import cl.uchile.dcc.gwent.Card.ICard
import cl.uchile.dcc.gwent.Card.Weather.ICardWeather
import cl.uchile.dcc.gwent.Observer_Observable.{IObservable, IObserver, ObserverObservable}

import scala.collection.mutable.ListBuffer

/** A Class that represent a Board from a card game, its contain two sections, one for each player, and a common
 * section which represent the weather zone that will contain a weather card.
 *
 *
 * @see ISection, WeatherZone
 * @author Israel Rodriguez
 * @since 1.2.3
 * @version 1.1
 */
class Board(){

  /* Section for one of the two players, will be use for the player 1*/
  private val Section1: Section = new Section()
  /* Section for one of the two players, will be use for the player 2*/
  private val Section2: Section = new Section()
  /* Zone for weather cards, common to both player*/
  private val wZone: WeatherZone = new WeatherZone()

  /* The weather zone is set in both section, both has the same*/
  Section1.set_wZone(wZone)
  Section2.set_wZone(wZone)

  /* Variables that show if a section is taken or not
  * true if is taken
  * false if isn't*/
  private var isSection1Taken: Boolean = false
  private var isSection2Taken: Boolean = false

  // Set the side in each section
  Section1.set_side("Sección superior")
  Section2.set_side("Sección inferior")
  // Tells the zone to add it
  wZone.registerObserver(Section1)
  wZone.registerObserver(Section2)

  /** Method to add to a player a not taken section to play
   *
   * @param player who going to add the section
   */
  def addPlayer(player: IPlayer):Unit = {
    if (!isSection1Taken){
      player.set_Section(Section1)
      isSection1Taken = true
    } else if (!isSection2Taken) {
      player.set_Section(Section2)
      isSection2Taken = true
    } else {
      throw new AssertionError("The board already has 2 players")
    }

  }

  /** Method to add a weather card to weather zone
   *
   * @param c weather card to add
   */
  def addOnW(c: ICardWeather): Unit = {
    wZone.add_Card(c)
  }

  /* Returns the card in weather zone */
  def get_wCard(): ICardWeather = {
    wZone.getCard
  }

  /** Method to obtain the cards in melee zone in a specific section
   * 
   * @param section Section to get the zone's cards
   * @return ListBuffer[ICard] with cards of the zone
   */
  def get_Melee(section: Boolean): ListBuffer[MeleeCard] = {
    if (section) {
      Section1.getMeleeCard
    }
    else{
      Section2.getMeleeCard
    }
  }

  /** Method to obtain the cards in melee zone in a specific section
   *
   * @param section Section to get the zone's cards
   * @return ListBuffer[ICard] with cards of the zone
   */
  def get_Range(section: Boolean): ListBuffer[RangeCard] = {
    if (section) {
      Section1.getRangeCard
    }
    else {
      Section2.getRangeCard
    }
  }

  /** Method to obtain the cards in melee zone in a specific section
   *
   * @param section Section to get the zone's cards
   * @return ListBuffer[ICard] with cards of the zone
   */
  def get_Siege(section: Boolean): ListBuffer[SiegeCard] = {
    if (section) {
      Section1.getSiegeCard
    }
    else {
      Section2.getSiegeCard
    }
  }

  /**
   * To know which section has less force
   * @return List with the side (or both sides) with less force
   */
  def getSectionWithLessForce(): List[String] = {
    val s1force = Section1.totalForce()
    val s2force =Section2.totalForce()
    if (s1force > s2force) {List(Section2.get_side())}
    else if (s1force < s2force) {List(Section1.get_side())}
    else  {List(Section1.get_side(), Section2.get_side())}
  }
}
