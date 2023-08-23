package cl.uchile.dcc
package gwent.Card.Visitor
import gwent.Card.Unity.{MeleeCard, RangeCard, SiegeCard}

import cl.uchile.dcc.gwent.Card.Weather.ICardWeather

/**
 * A class that represent a visitor to know the type of a card
 */
class KnowTypeVisitor extends ICardVisitor{
  /**
   * Result of the visit
   */
  private var result: String = ""

  /**
   * If the card is melee, the result will be melee
   * @param card Visited card
   */
  override def visitMelee(card: MeleeCard): Unit ={result = "melee"}

  /**
   * If the card is melee, the result will be range
   *
   * @param card Visited card
   */
  override def visitRange(card: RangeCard): Unit = {result = "range"}

  /**
   * If the card is melee, the result will be siege
   *
   * @param card Visited card
   */
  override def visitSiege(card: SiegeCard): Unit = {result = "siege"}

  /**
   * If the card is melee, the result will be weather
   *
   * @param card Visited card
   */
  override def visitWeather(card: ICardWeather): Unit = {result = "weather"}

  /**
   * To know the result of the visit
   * @return A string with the result
   */
  def getResult(): String = {
    val copyResult = result
    copyResult
  }
}
