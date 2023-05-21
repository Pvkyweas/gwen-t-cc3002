package cl.uchile.dcc
package gwent.Card.Weather

/** A class representing a weather card with Clear weather
 *
 * @param name Name of the card
 * @constructor Create a new weather card with the specified name
 * @example
 * {{{
 *   val weatherCard = new ClearWeatherCard("example")
 *   val nameC = weatherCard.get_Name()
 *   println(s" The name of the card is $nameC")
 * }}}
 * @see ICard
 * @author Israel Rodriguez
 * @since 1.2.3
 * @version 1.0
 */
class ClearWeatherCard(private val name: String) extends AbstractCardWeather(name){

  override def get_Effect(): String = "Clima despejado"
  
  override def equals(obj: Any): Boolean = {
    if (this.getClass.getName == obj.getClass.getName) {
      super.equals(obj)
    }
    else {
      false
    }
  }
}
