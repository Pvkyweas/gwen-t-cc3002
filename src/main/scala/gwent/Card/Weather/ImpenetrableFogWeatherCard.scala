package cl.uchile.dcc
package gwent.Card.Weather

import gwent.Card.Effect.ImpenetrableFogEffect

/** A class representing a weather card with impenetrable Fog weather
 *
 * @param name Name of the card
 * @constructor Create a new unity card with the specified name
 * @example
 * {{{
 *   val weatherCard = new ImpenetrableFogWeatherCard("example")
 *   val nameC = weatherCard.get_Name()
 *   println(s" The name of the card is $nameC")
 * }}}
 * @see ICard
 * @author Israel Rodriguez
 * @since 1.2.3
 * @version 1.0
 */
class ImpenetrableFogWeatherCard(private val name: String) extends AbstractCardWeather(name, new ImpenetrableFogEffect()){
  
  override def equals(obj: Any): Boolean = {
    if (this.getClass.getName == obj.getClass.getName) {
      super.equals(obj)
    }
    else {
      false
    }
  }
}
