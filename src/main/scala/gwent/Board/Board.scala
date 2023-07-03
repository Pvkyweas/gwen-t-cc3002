package cl.uchile.dcc
package gwent.Board

import gwent.{IObservable, IObserver, IPlayer, ObserverObservable}

import cl.uchile.dcc.gwent.Card.Unity.{ICardUnity, MeleeCard, RangeCard, SiegeCard}
import cl.uchile.dcc.gwent.Card.ICard
import cl.uchile.dcc.gwent.Card.Weather.AbstractCardWeather

import scala.collection.mutable.ListBuffer

/** A Class that represent a Board from a card game, its contain two sections, one for each player, and a common
 * section which represent the weather zone that will contain a weather card.
 *
 * @param Section1 Section for one of the two players
 * @param Section2 Section for one of the two players
 * @param wZone Zone for weather cards, common to both player
 *
 * @see ISection, WeatherZone
 * @author Israel Rodriguez
 * @since 1.2.3
 * @version 1.1
 */
class Board(private val Section1: Section,
            private val Section2: Section,
            private val wZone: WeatherZone){

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
      player.set_Board(this)
      isSection1Taken = true
    } else if (!isSection2Taken) {
      player.set_Section(Section2)
      player.set_Board(this)
      isSection2Taken = true
    } else {
      throw new AssertionError("The board already has 2 players")
    }

  }

  /** Method to add a weather card to weather zone
   *
   * @param c weather card to add
   */
  def addOnW(c: AbstractCardWeather): Unit = {
    wZone.add_Card(c)
  }

  /* Returns the card in weather zone */
  def get_wCard(): AbstractCardWeather = {
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


}
