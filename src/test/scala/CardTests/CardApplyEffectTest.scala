package cl.uchile.dcc
package CardTests

import cl.uchile.dcc.gwent.Card.Effect.NoneEffect
import cl.uchile.dcc.gwent.Card.ICard
import cl.uchile.dcc.gwent.Card.Unity.{ICardUnity, MeleeCard, RangeCard, SiegeCard}
import cl.uchile.dcc.gwent.Card.Weather.{BitingFrostWeatherCard, ClearWeatherCard, ImpenetrableFogWeatherCard, TorrentialRainWeatherCard}
import munit.FunSuite

class CardApplyEffectTest extends FunSuite{
  // Unity Card
  var Cprueba_mele: ICard = _
  var Cprueba_distancia: ICard = _
  var Cprueba_asedio: ICard = _

  // Weather Card
  var Cprueba_clima1: ICard = _
  var Cprueba_clima2: ICard = _
  var Cprueba_clima3: ICard = _
  var Cprueba_clima4: ICard = _

  override def beforeEach(context: BeforeEach): Unit = {

  }
  test("Si se le aplica un efecto a una carta de clima ocurre nada"){

  }

  test("Si se trata de aplicar el efecto de una carta que no tiene, ocurre nada"){

  }

  test("Se les puede aplicar el efecto: Refuerzo moral si son de la misma fila") {

  }

  test("Se les puede aplicar el efecto: Vinculo estrecho si tienen el mismo nombre") {

  }

  test("Se les puede aplicar los efectos de las cartas de clima, si se cambia el clima se recupera el valor anterior de fuerza") {

  }
}
