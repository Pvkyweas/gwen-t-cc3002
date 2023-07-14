package cl.uchile.dcc
package gwent.Game

import gwent.Game.State.{GameState, StartState}

import cl.uchile.dcc.gwent.Board.Board
import cl.uchile.dcc.gwent.Deck.Deck
import cl.uchile.dcc.gwent.{IPlayer, Player}
import cl.uchile.dcc.gwent.Observer_Observable.IObserver
import cl.uchile.dcc.gwent.Observer_Observable.Notifications.INotification

import scala.collection.mutable.ListBuffer

/**
 * A class that represent the Controller of the game, this class has the behaviour of the game
 */
class GameController() extends IObserver{
  /* Variable that represent the state of the game, starts being a StartState*/
  private[Game] var state: GameState = new StartState(this)

  /* The factory that produces decks*/
  private val deckFactory: DeckFactory = new DeckFactory()

  /* The board in which the game happens*/
  private val board: Board = new Board()

  /* List with the player who reach 0 gems*/
  private val noGemsList: ListBuffer[IPlayer] = ListBuffer()

  /* Player 1*/
  private var player1: Option[IPlayer] = None
  /* Computer*/
  private var computer: Option[IPlayer] = None

  /**
   *  Constructor to when the game controller is initialize with a player 1
   * @param player The player 1
   */
  def this(player: IPlayer) = {
    this()
    // Register the player to the board and player register this game controller as observer
    addPlayer(player)
  }

  /**
   *  To know if the player 1 is defined
   * @return Boolean, True if the player 1 is defined and False otherwise
   */
  private[Game] def isPlayer1(): Boolean = {player1.isDefined}

  /**
   *  Method to add the player, tell to the player to add this
   *  game controller as observer and add the player to the board
   *
   *  this method is also used in the constructor that has a player as parameter
   *
   * @param nP The player to be added
   */
  def addPlayer(nP: IPlayer): Unit = {
    player1 = Some(nP)
    nP.registerObserver(this)
    board.addPlayer(nP)
  }

  /**
   *  Private method to add a computer player, does the same as addPlayer but with a computer player
   * @param nP the computer player to be added
   */
  private[Game] def addIA(nP: IPlayer): Unit = {
    computer = Some(nP)
    nP.registerObserver(this)
    board.addPlayer(nP)
  }

  /**
   * Private method to tells to both player to draw 10 cards
   */
  private[Game] def initialDraw(): Unit = {
    player1.foreach(p => p.drawCard(10))
    computer.foreach(c => c.drawCard(10))
  }

  /**
   *  Method to get the actual state
   * @return A string with the name of the actual state
   */
  def getState: String = {state.getState}

  /**
   *  Method to change the state
   * @param newState The new state
   */
  def setState(newState: GameState): Unit = state = newState

  /**
   * Method that initialize the game
   */
  def startGame: Unit = {state.start}

  def endGame: Unit = {state.end}

  def playCard: Unit = {state.playCard}

  def passTurn: Unit = {state.passTurn}

  private[Game] def startTurns: Unit = {state.startTurns(player1.get)}

  def playerLoss(whoLoss: IPlayer): Unit = {}

  /**
   * Method to create a standard deck
   * the standard deck contains 25 cards in total, 7 are weather cards
   * @return A standard deck
   */
  def createStandardDeck(): Deck = deckFactory.createDeck(25, 7)

  // Observer's method
  def getNotification(content: INotification): Unit = {content.readToController(this)}
}
