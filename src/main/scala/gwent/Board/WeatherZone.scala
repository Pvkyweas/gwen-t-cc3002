package cl.uchile.dcc
package gwent.Board

import gwent.Card.ICard

import cl.uchile.dcc.gwent.Card.Weather.{AbstractCardWeather, ClearWeatherCard}

/** A class that represent a Zone for weather cards, by default the card in this zone
 * is a ClearWeather
 *
 *
 * @see IZone, AbstractCardWeather
 * @author Israel Rodriguez
 * @since 1.2.3
 * @version 1.0
 */
class WeatherZone extends IZone[AbstractCardWeather]{
  /* Variable that store the active weather card */
  private var cardSlot: AbstractCardWeather = new ClearWeatherCard("Soleado")

  /** This method add a card to the slot
   *
   * @param Card card to add
   */
  def add_Card(Card: AbstractCardWeather): Unit = cardSlot = Card

  /* This method returns the card in cardSlot */
  def getCard: AbstractCardWeather = cardSlot
}
