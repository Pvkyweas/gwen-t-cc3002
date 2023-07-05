package cl.uchile.dcc
package gwent.Board

import gwent.Card.ICard

import cl.uchile.dcc.gwent.Card.Weather.{ICardWeather, ClearWeatherCard}
import cl.uchile.dcc.gwent.Observer_Observable.Notifications.AddCardNotification
import cl.uchile.dcc.gwent.Observer_Observable.{IObservable, IObserver, Observable}

/** A class that represent a Zone for weather cards, by default the card in this zone
 * is a ClearWeather
 *
 * @see IZone, AbstractCardWeather
 * @author Israel Rodriguez
 * @since 1.2.3
 * @version 1.1
 */
class WeatherZone extends Observable with IZone[ICardWeather]{
  /* Variable that store the active weather card */
  private var cardSlot: ICardWeather = new ClearWeatherCard("Soleado")

  /** This method add a card to the slot
   * and notify to its observer that the card was added
   *
   * @param Card card to add
   */
  def add_Card(Card: ICardWeather): Unit = {
    cardSlot = Card
    notify(new AddCardNotification(Card))
  }

  /* This method returns the card in cardSlot */
  def getCard: ICardWeather = cardSlot
}
