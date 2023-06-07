package cl.uchile.dcc
package gwent.Game

import gwent.Deck.Deck

class DeckFactory {

  /** Method to crear a Deck with the amount of cards desired
   * the sums of cards cannot exceed 25
   *
   * @param qMelee quantity of melee cards
   * @param qRange quantity of range cards
   * @param qSiege quantity of siege cards
   * @param qWeather quantity of weather cards
   *
   * @return A deck with that quantity of cards
   */
  def createDeck(qMelee: Int, qRange: Int, qSiege: Int, qWeather: Int): Deck = ???

}
