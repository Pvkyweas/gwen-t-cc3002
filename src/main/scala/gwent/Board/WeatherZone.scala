package cl.uchile.dcc
package gwent.Board

import gwent.Card.ICard

import cl.uchile.dcc.gwent.Card.Weather.{AbstractCardWeather, ClearWeatherCard}

class WeatherZone extends IZone{
  /* Variable that store the active weather card */
  private var cardSlot: ICard = new ClearWeatherCard("Soleado")

  /** This method add a card to the slot
   *
   * @param Card card to add
   */
  def add_Card(Card: ICard): Unit = cardSlot = Card

  /* This method returns the card in cardSlot */
  def getCard: ICard = cardSlot
}
