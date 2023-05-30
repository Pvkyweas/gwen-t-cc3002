package cl.uchile.dcc
package gwent
package Card
package Effect

import gwent.Card.ICard
import gwent.Card.Unity.ICardUnity

trait IEffect {
def get_effect(): String
}
