package cl.uchile.dcc

import cl.uchile.dcc.gwent.Card.Effect.NoneEffect
import cl.uchile.dcc.gwent.Card.ICard
import cl.uchile.dcc.gwent.Card.Unity.{MeleeCard, RangeCard, SiegeCard}
import cl.uchile.dcc.gwent.Card.Visitor.{ICardVisitor, KnowTypeVisitor}
import cl.uchile.dcc.gwent.Card.Weather.ClearWeatherCard
import munit.FunSuite

class KnowTypeVisitorTest extends FunSuite{
  var melee: ICard = _
  var range: ICard = _
  var siege: ICard = _
  var weather: ICard = _
  var visitor: KnowTypeVisitor = _
  override def beforeEach(context: BeforeEach): Unit = {
    melee = new MeleeCard("a", new NoneEffect, 1)
    range = new RangeCard("a", new NoneEffect, 1)
    siege = new SiegeCard("a", new NoneEffect, 1)
    weather = new ClearWeatherCard("a")

    visitor = new KnowTypeVisitor()
  }

  test("El resultado de que KnowTypeVisitor visite una carta debe ser su tipo"){
    melee.accept(visitor)
    assert(visitor.getResult() == "melee")

    range.accept(visitor)
    assert(visitor.getResult() == "range")

    siege.accept(visitor)
    assert(visitor.getResult() == "siege")

    weather.accept(visitor)
    assert(visitor.getResult() == "weather")
  }
}
