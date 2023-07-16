package cl.uchile.dcc
package gwent

import gwent.Deck.Deck

import scala.util.Random
import cl.uchile.dcc.gwent.Card.ICard
import cl.uchile.dcc.gwent.Card.Visitor.{ICardVisitor, KnowTypeVisitor}

import scala.collection.mutable.ListBuffer

class Computer(private val name: String,
               private var gem_counter: Int = 2,
               private val deck_cards: Deck) extends Player(name, gem_counter, deck_cards){

  /**
   * Method to obtain the position of a random weather card in hand
   * @return Pos if there is a weather card, -1 if not
   */
  private def posWeatherCard(): Int = {
    // Obtain the list of cards
    val cardlist: List[ICard] = hand_cards.getList()
    // The visitor to know what card is a weather card
    val visitor: KnowTypeVisitor = new KnowTypeVisitor()
    // Filter
    val weatherCards = cardlist.filter((c: ICard) => {
      c.accept(visitor)
      visitor.getResult() == "weather"
    })
    // Obtain a random weather card
    if (weatherCards.nonEmpty) {
      val RandomCard = Random.shuffle(weatherCards).head
      cardlist.indexOf(RandomCard)
    }
    else -1
  }

  /**
   * To know the force of the cards in hand
   * @return value of force of cards in hand
   */
  def getForceHand(): Int = {
    val cardlist: List[ICard]  = hand_cards.getList()
    var totalF = 0
    cardlist.foreach((c: ICard) => totalF += c.get_Force())
    totalF
  }

  /**
   * Method to play a random card in hand
   */
  def playRandomCard(): Unit = {
    val numCards: Int = numCards_hand()
    val randomPos: Int = Random.nextInt(numCards)
    playCard(randomPos)
  }

  /**
   * If can, play a random weather card, if can not then passTurn
   */
  def playRandomWeather(): Unit = {
    val randomPos: Int = posWeatherCard()
    if (randomPos != -1) playCard(randomPos)
    else passTurn()
  }
}
