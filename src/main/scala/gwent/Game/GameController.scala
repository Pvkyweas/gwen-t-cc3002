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

  /**
   * Method to end the game, transitions to EndState
   */
  def endGame: Unit = {state.end(noGemsList)}

  /**
   * Method to tell to its state that a player has played a card
   * @param p Player who played a card
   */
  def playCard(p: IPlayer): Unit = {state.playCard(p)}

  /**
   * Method to tell to its state that a player has passed its turn
   */
  def passTurn: Unit = {state.passTurn(computer)}

  /**
   *  Method to start the turn of the player 1
   */
  private[Game] def startTurns: Unit = {state.startTurns(player1.get)}

  /**
   * Method for states to carry out the actions that correspond to them
   * Calls the state's update method
   */
  def update(): Unit = {state.update()}

  /**
   * Method to indicate to the players that a section lost
   */
  private[Game] def discountGems(): Unit = {
    for (section <- board.getSectionWithLessForce()) {
      player1.foreach(p => if(p.get_Section() == section) p.lostRound())
      computer.foreach(c => if(c.get_Section() == section) c.lostRound())
    }
  }

  /**
   * To know if a player reached 0 gems
   * @return True if the noGemsList has a player
   */
  private[Game] def aPlayerLost(): Boolean = noGemsList.nonEmpty

  /**
   * Method to reset the board, delete all cards in it
   */
  private[Game] def clearBoard(): Unit = board.clear()

  /**
   * Add a player with 0 gems to noGemsList
   * @param whoLoss The player who loss all of its gems
   */
  def playerLoss(whoLoss: IPlayer): Unit = {noGemsList.addOne(whoLoss)}

  /**
   * Method to create a standard deck
   * the standard deck contains 25 cards in total, 7 are weather cards
   * @return A standard deck
   */
  def createStandardDeck(): Deck = deckFactory.createDeck(25, 7)

  // Observer's method
  def getNotification(content: INotification): Unit = {content.readToController(this)}
}
