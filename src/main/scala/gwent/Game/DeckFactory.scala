package cl.uchile.dcc
package gwent.Game

import gwent.Deck.Deck

import scala.util.Random
import cl.uchile.dcc.gwent.Card.Effect.{CloseBondEffect, IEffect, MoralBoosterEffect, NoneEffect}
import cl.uchile.dcc.gwent.Card.ICard
import cl.uchile.dcc.gwent.Card.Unity.{ICardUnity, MeleeCard, RangeCard, SiegeCard}
import cl.uchile.dcc.gwent.Card.Weather.{BitingFrostWeatherCard, ClearWeatherCard, ICardWeather, ImpenetrableFogWeatherCard, TorrentialRainWeatherCard}
import cl.uchile.dcc.gwent.Exceptions.InvalidAmountCardDeckException

import scala.collection.mutable.ListBuffer

/**
 * A class that represent a Factory of deck, with this class you can create decks with random cards
 */
class DeckFactory {

  /* List that contains the possibles weather Cards*/
  private val listWeatherCard: List[ICardWeather] = List(
    new BitingFrostWeatherCard("Escarcha mordiente"),
    new ClearWeatherCard("Despejado"),
    new ImpenetrableFogWeatherCard("Niebla impenetrable"),
    new TorrentialRainWeatherCard("Lluvia torrencial")
  )

  /* List that contains the possibles effects*/
  private val listEffect: List[IEffect] = List(
    new MoralBoosterEffect,
    new CloseBondEffect
  )

  /* Map with the possibles names and forces for cards*/
  private val cardsCombination: Map[String, (String, Int)] = Map(
    "m1" -> ("Lancero", 3),
    "m2" -> ("Alabardero", 6),
    "m3" -> ("Milicia", 4),
    "m4" -> ("Samurai", 8),
    "m5" -> ("Berserker", 9),
    "r1" -> ("Arquero de tiro largo", 6),
    "r2" -> ("Conquistador", 16),
    "r3" -> ("Ballestero", 5),
    "r4" -> ("Carreta de guerra", 9),
    "r5" -> ("Arambai", 12),
    "s1"-> ("Elefante con ballesta", 8),
    "s2"-> ("Escorpion", 12),
    "s3"-> ("Cañon de salvas", 16),
    "s4"-> ("Onagro", 18),
    "s5"-> ("Obús", 18)
  )

  /** Method to create a Deck with the amount of cards desired
   *
   * @param qTotal quantity of cards in the whole deck
   * @param qWeather quantity of weather cards
   *
   * @return A deck with that quantity of cards
   */
  def createDeck(qTotal: Int, qWeather: Int): Deck = {
    if (qWeather < 0 || qTotal < 0) throw new InvalidAmountCardDeckException("You can't create a deck with negative values")
    if (qWeather>qTotal) throw new InvalidAmountCardDeckException("You can't create more weather cards than the total amount")
    val result: Deck = new Deck()

    /* Quantity of cards of each type*/
    val qEachType: Int = (qTotal-qWeather)/3

    // Se agregan las cartas de clima
    for (i <- 1 to qWeather) {result.addCard(Random.shuffle(listWeatherCard).head)}

    // Se determina la cantidad de cartas con efecto
    val effectQuantity: Int = Random.between(6,11)
    val (qM, qR, qS) = obtainQuantity(effectQuantity, qEachType)

    // Se agregan las cartas
    for (c <- createCards(qEachType, qM, qR, qS)) {result.addCard(c)}

    result.shuffle()
    result
  }

  /** Method to create a ListBuffer with unity cards, each card is randomly generated, the values of name and force
   *  of each card is obtained from a map that contains the combination of name and force.
   *
   * @param q Amount of cards for each type
   * @param qEffectM Amount to know how many melee cards has an effect
   * @param qEffectR Amount to know how many range cards has an effect
   * @param qEffectS Amount to know how many siege cards has an effect
   * @return A ListBuffer with all cards
   */
  private def createCards(q: Int, qEffectM: Int, qEffectR: Int, qEffectS: Int): ListBuffer[ICardUnity] = {
    val list_result: ListBuffer[ICardUnity] = ListBuffer()
    for (i <- 1 to q) {
        var combination: Option[(String, Int)] = cardsCombination.get("m" + (Random.nextInt(5)+1).toString)
        var values: (String, Int) = combination.get
        if (i < qEffectM) list_result.addOne(new MeleeCard(values._1, Random.shuffle(listEffect).head, values._2))
        else list_result.addOne(new MeleeCard(values._1, new NoneEffect, values._2))
    }
    for (i <- 1 to q) {
      var combination: Option[(String, Int)] = cardsCombination.get("r" + (Random.nextInt(5)+1).toString)
      var values: (String, Int) = combination.get
      if (i < qEffectM) list_result.addOne(new RangeCard(values._1, Random.shuffle(listEffect).head, values._2))
      else list_result.addOne(new RangeCard(values._1, new NoneEffect, values._2))
    }
    for (i <- 1 to q) {
      var combination: Option[(String, Int)] = cardsCombination.get("s" + (Random.nextInt(5)+1).toString)
      var values: (String, Int) = combination.get
      if (i < qEffectM) list_result.addOne(new SiegeCard(values._1, Random.shuffle(listEffect).head, values._2))
      else list_result.addOne(new SiegeCard(values._1, new NoneEffect, values._2))
    }
    list_result
  }

  /** method to decompose an integer value into 3 integer values that add up to the original value
   *
   * @param value Value to decompose
   * @param maxValue indicates the maximum value of each return value
   * @return 3 int values that add up value
   */
  private def obtainQuantity(value: Int, maxValue: Int): (Int, Int, Int) = {
    val rand = new Random()
    var a = 0
    var b = 0
    var c = 0

    while (a + b + c != value || a == 0 || b == 0 || c == 0) {
      a = rand.nextInt(value)
      b = rand.nextInt(value)
      if (a<maxValue && b<maxValue) c = value - a - b
      else c = 0
    }

    (a, b, c)
  }
}
