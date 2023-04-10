package cl.uchile.dcc
package gwent

trait IDeck {
  def addCard(card: ICard): Unit
  def draw_Card(): Option[ICard]
  def draw_Card(pos: Int): Option[ICard]
  def isEmpty: Boolean
  def get_Size(): Int
  def shuffle(): Unit
}
