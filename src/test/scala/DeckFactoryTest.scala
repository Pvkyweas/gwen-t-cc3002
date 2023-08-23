package cl.uchile.dcc

import cl.uchile.dcc.gwent.Game.DeckFactory
import gwent.Deck.Deck

import cl.uchile.dcc.gwent.Card.ICard
import munit.FunSuite
import org.junit.Assert

import scala.collection.mutable.ListBuffer

class DeckFactoryTest extends FunSuite {
  var factory: DeckFactory = _

  override def beforeEach(context: BeforeEach): Unit = {
    factory = new DeckFactory()
  }

  test("Tratar de crear un mazo con más cartas de clima que cantidad total de cartas lanza una exepción"){
    val e = Assert.assertThrows(classOf[Exception], () => factory.createDeck(2,3))
    assertEquals("You can't create more weather cards than the total amount", e.getMessage)
  }

  test("No se pueden crear mazos con cantidades negativas"){
    val e = Assert.assertThrows(classOf[Exception], () => factory.createDeck(3, -3))
    assertEquals("You can't create a deck with negative values", e.getMessage)
  }

  test("Se puede crear un mazo estandar"){
    // Posibles nombres
    val SiegeNames: List[String] = List(
      "Elefante con ballesta",
      "Escorpion",
      "Cañon de salvas",
      "Onagro",
      "Obús"
    )

    val MeleeNames: List[String] = List(
      "Lancero",
      "Alabardero",
      "Milicia",
      "Samurai",
      "Berserker"
    )

    val RangeNames: List[String] = List(
      "Arquero de tiro largo",
      "Conquistador",
      "Ballestero",
      "Carreta de guerra",
      "Arambai"
    )

    // Se crea el mazo
    val deck: Deck = factory.createDeck(25,7)

    //Se comprueba que hayan 25 cartas
    assertEquals(deck.get_Size(), 25)

    //Se obtienen todas las cartas en una lista
    val list: ListBuffer[Option[ICard]] = deck.draw_multipleCard(25)

    var CountM = 0
    var CountR = 0
    var CountS = 0
    var CountW = 0

    for (c <- list) {
      if (MeleeNames.contains(c.get.get_Name())) {CountM += 1}
      else if (RangeNames.contains(c.get.get_Name())) {CountR += 1}
      else if (SiegeNames.contains(c.get.get_Name())) {CountS += 1}
      else {CountW += 1}
    }

    // Se comprueba que haya la cantidad correcta de cada tipo
    assertEquals(CountM,6)
    assertEquals(CountR,6)
    assertEquals(CountS,6)
    assertEquals(CountW,7)

  }
}
