package cl.uchile.dcc
package CardTests

import cl.uchile.dcc.gwent.Card.Effect.{BitingFrostEffect, ClearWeatherEffect, CloseBondEffect, IEffect, ImpenetrableFogEffect, MoralBoosterEffect, NoneEffect, TorrentialRainEffect}
import cl.uchile.dcc.gwent.Card.ICard
import cl.uchile.dcc.gwent.Card.Unity.{ICardUnity, MeleeCard, RangeCard, SiegeCard}
import cl.uchile.dcc.gwent.Card.Weather.{BitingFrostWeatherCard, ClearWeatherCard, ImpenetrableFogWeatherCard, TorrentialRainWeatherCard}
import munit.FunSuite

class CardApplyEffectTest extends FunSuite{
  // Unity Card
  var Cprueba_melee: ICard = _
  var Cprueba_distancia: ICard = _
  var Cprueba_asedio: ICard = _

  // Weather Card
  var Cprueba_clima1: ICard = _
  var Cprueba_clima2: ICard = _
  var Cprueba_clima3: ICard = _
  var Cprueba_clima4: ICard = _

  // Efectos Unidad
  var Eunity_moralBooster: IEffect = _
  var Eunity_closeBond: IEffect = _

  override def beforeEach(context: BeforeEach): Unit = {
    // Unidad
    Cprueba_melee = new MeleeCard("m1", new NoneEffect(), 1)
    Cprueba_distancia = new RangeCard("d1", new NoneEffect(), 2)
    Cprueba_asedio = new SiegeCard("a1", new NoneEffect(), 3)

    // Clima
    Cprueba_clima1 = new ClearWeatherCard("Soleado")
    Cprueba_clima2 = new TorrentialRainWeatherCard("Lluvia Torrencial")
    Cprueba_clima3 = new BitingFrostWeatherCard("Escarcha Mordiente")
    Cprueba_clima4 = new ImpenetrableFogWeatherCard("Niebla Impenetrable")

    // Efectos
    Eunity_closeBond = new CloseBondEffect()
    Eunity_moralBooster = new MoralBoosterEffect()

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

  test("equals"){
    assert(!Eunity_closeBond.equals(Eunity_moralBooster))
    assert(Eunity_closeBond.equals(new CloseBondEffect()))
    assert(!Eunity_moralBooster.equals(Eunity_closeBond))
    assert(Eunity_moralBooster.equals(new MoralBoosterEffect()))

    assert((new ImpenetrableFogEffect).equals(new ImpenetrableFogEffect()))
    assert(!(new ImpenetrableFogEffect).equals(Eunity_closeBond))
    assert(!(new TorrentialRainEffect).equals(Eunity_closeBond))
    assert(!(new ClearWeatherEffect).equals(Eunity_closeBond))
    assert(!(new BitingFrostEffect).equals(Eunity_closeBond))
    assert(!(new NoneEffect).equals(Eunity_closeBond))

    assert(!Cprueba_clima2.equals(Eunity_closeBond))
    assert(Cprueba_clima4.equals(new ImpenetrableFogWeatherCard("Niebla Impenetrable")))

    assert(Cprueba_asedio.equals(Eunity_closeBond))
  }
}
