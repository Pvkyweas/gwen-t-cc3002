package cl.uchile.dcc
package gwent.Card.Board

import cl.uchile.dcc.gwent.Card.ICard

trait IZone {
def add_Card(Card:ICard): Unit
}
