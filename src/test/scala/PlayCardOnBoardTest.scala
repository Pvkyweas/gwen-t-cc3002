package cl.uchile.dcc

import gwent.{IPlayer, Player}
import gwent.Board.{Board, ISection, MeleeZone, RangeZone, Section, SiegeZone, WeatherZone}

import scala.collection.mutable.ListBuffer
import cl.uchile.dcc.gwent.Card.Unity.{ICardUnity, MeleeCard, RangeCard, SiegeCard}
import cl.uchile.dcc.gwent.Card.ICard
import cl.uchile.dcc.gwent.Card.Effect.NoneEffect
import cl.uchile.dcc.gwent.Card.Weather.{ICardWeather, BitingFrostWeatherCard, ClearWeatherCard, TorrentialRainWeatherCard}
import cl.uchile.dcc.gwent.Deck.{Deck, HandDeck}
import munit.FunSuite
import org.junit.Assert

class PlayCardOnBoardTest extends FunSuite {

  var Player1: IPlayer = _
  var Player2: IPlayer = _
  var Section1: Section = _
  var Section2: Section = _
  var Board: Board = _
  var wCard: ICardWeather = _
  var wCard2: ICardWeather = _
  var mCard: MeleeCard = _
  var rCard: RangeCard = _
  var sCard: SiegeCard = _

  override def beforeEach(context: BeforeEach): Unit = {
    wCard = new BitingFrostWeatherCard("BitingFrost")
    wCard2 = new TorrentialRainWeatherCard("TorrentialRain")
    mCard = new MeleeCard("m", new NoneEffect(), 3)
    rCard = new RangeCard("r", new NoneEffect(), 3)
    sCard = new SiegeCard("s", new NoneEffect(), 3)
    Player1 = new Player(name = "jugador1", deck_cards = new Deck(ListBuffer[ICard](mCard, sCard, wCard)), hand_cards = new HandDeck())
    Player2 = new Player(name = "jugador2", deck_cards = new Deck(ListBuffer[ICard](rCard, wCard2)), hand_cards = new HandDeck())
    Section1 = new Section(new MeleeZone(), new RangeZone(), new SiegeZone())
    Section2 = new Section(new MeleeZone(), new RangeZone(), new SiegeZone())
    Board = new Board(Section1= Section1, Section2 = Section2, wZone = new WeatherZone)

    // Se añaden los jugadores al tablero
    Board.addPlayer(Player1)
    Board.addPlayer(Player2)
  }

  test("Si el jugador fue añadido a un tablero, se le puede obtener su seccion"){
    assertEquals(Player1.get_Section(),"Sección superior")
    assertEquals(Player2.get_Section(),"Sección inferior")
  }

  test("No se puede añadir un tercer jugador a un tablero"){
    val p3: IPlayer = new Player("jugador3", deck_cards = new Deck(ListBuffer[ICard](rCard, wCard2)), hand_cards = new HandDeck())
    val e = Assert.assertThrows(classOf[AssertionError], () => Board.addPlayer(p3))
    assertEquals("The board already has 2 players", e.getMessage)
  }

  test("Un jugador puede cambiar la carta en la zona de clima"){
    Player1.drawCard(1)
    Player2.drawCard(1)
    
    // Por defecto debe tener una carta de clima despejado con nombre Soleado
    assertEquals(Board.get_wCard(), new ClearWeatherCard("Soleado"))

    Player1.playCard(0)
    
    assertEquals(Board.get_wCard(), new BitingFrostWeatherCard("BitingFrost"))
    
    Player2.playCard(0)

    assertEquals(Board.get_wCard(), new TorrentialRainWeatherCard("TorrentialRain"))
  }

  test("Los jugadores pueden jugar cartas en las zonas melee, range y siege"){
    Player1.drawCard(3)
    Player2.drawCard(2)

    assertEquals(Board.get_Melee(section = true), ListBuffer[MeleeCard]()) // No hay cartas en la sección 1 zona melee
    assertEquals(Board.get_Range(section = false), ListBuffer[RangeCard]()) // No hay cartas en la sección 2 zona range
    assertEquals(Board.get_Siege(section = true), ListBuffer[SiegeCard]()) // No hay cartas en la sección 1 zona siege

    // Se juegan las cartas de unidad de los jugadores
    Player1.playCard(2)
    Player1.playCard(1)

    Player2.playCard(1)

    // Se comprueba que se hayan agregado
    assertEquals(Board.get_Melee(section = true), ListBuffer[MeleeCard](mCard))
    assertEquals(Board.get_Range(section = false), ListBuffer[RangeCard](rCard))
    assertEquals(Board.get_Siege(section = true), ListBuffer[SiegeCard](sCard))

    // Se comprueba que solo se agregaron en la sección correcta
    assertEquals(Board.get_Melee(section = false), ListBuffer[MeleeCard]())
    assertEquals(Board.get_Range(section = true), ListBuffer[RangeCard]())
    assertEquals(Board.get_Siege(section = false), ListBuffer[SiegeCard]())
  }

}
