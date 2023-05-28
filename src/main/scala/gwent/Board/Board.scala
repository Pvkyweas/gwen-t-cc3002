package cl.uchile.dcc
package gwent.Board

import gwent.IPlayer

import cl.uchile.dcc.gwent.Card.Unity.{ICardUnity, MeleeCard, RangeCard, SiegeCard}
import cl.uchile.dcc.gwent.Card.ICard
import cl.uchile.dcc.gwent.Card.Weather.AbstractCardWeather

import scala.collection.mutable.ListBuffer

/**
 *
 * @param Section1
 * @param Section2
 * @param wZone
 */
class Board(private val Section1: ISection,
            private val Section2: ISection,
            private val wZone: WeatherZone) {

  /** Method to add an unity card to section's player
   *
   * @param c Unity Card to add
   * @param s Section of the player
   */
  def addCard(c: ICardUnity, s: Boolean): Unit = {
    if (s){
      Section1.add_Card(c)
    }
    else{
      Section2.add_Card(c)
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
