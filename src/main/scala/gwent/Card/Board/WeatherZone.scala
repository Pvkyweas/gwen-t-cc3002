package cl.uchile.dcc
package gwent.Card.Board

import gwent.Card.ICard

import cl.uchile.dcc.gwent.Card.Weather.{AbstractCardWeather, ClearWeatherCard}

class WeatherZone extends IZone{
  private var cardSlot: AbstractCardWeather = new ClearWeatherCard("Soleado")

  def add_Card(Card: ICard): Unit = ???
}
