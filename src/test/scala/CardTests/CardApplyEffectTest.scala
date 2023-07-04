package cl.uchile.dcc
package CardTests

import cl.uchile.dcc.gwent.Board.ISection
import cl.uchile.dcc.gwent.Card.Effect.{BitingFrostEffect, ClearWeatherEffect, CloseBondEffect, IEffect, ImpenetrableFogEffect, MoralBoosterEffect, NoneEffect, TorrentialRainEffect}
import cl.uchile.dcc.gwent.Card.ICard
import cl.uchile.dcc.gwent.Card.Unity.{ICardUnity, MeleeCard, RangeCard, SiegeCard}
import cl.uchile.dcc.gwent.Card.Weather.{AbstractCardWeather, BitingFrostWeatherCard, ClearWeatherCard, ImpenetrableFogWeatherCard, TorrentialRainWeatherCard}
import munit.FunSuite
import org.junit.Assert
import scala.collection.mutable.ListBuffer

class CardApplyEffectTest extends FunSuite{
  // Unity Card
  var Cprueba_melee: MeleeCard = _
  var Cprueba_melee2: MeleeCard = _
  var Cprueba_melee_CB: MeleeCard = _
  var Cprueba_distancia: RangeCard = _
  var Cprueba_distancia2: RangeCard = _
  var Cprueba_asedio: SiegeCard = _
  var Cprueba_asedio2: SiegeCard = _

  var lista_melee: ListBuffer[MeleeCard] = _
  var lista_distancia: ListBuffer[RangeCard] = _
  var lista_asedio: ListBuffer[SiegeCard] = _

  // Weather Card
  var Cprueba_clima1: AbstractCardWeather = _
  var Cprueba_clima2: AbstractCardWeather = _
  var Cprueba_clima3: AbstractCardWeather = _
  var Cprueba_clima4: AbstractCardWeather = _

  // Efectos Unidad
  var Eunity_MB: IEffect = _
  var Eunity_CB: IEffect = _

  // Efectos Clima
  var Eclima_CW: IEffect = _
  var Eclima_TR: IEffect = _
  var Eclima_BF: IEffect = _
  var Eclima_IF: IEffect = _

  // Efecto none
  var Enone: IEffect = _

  // Section
  var Sprueba: ISection = _

  override def beforeEach(context: BeforeEach): Unit = {
    // Clima
    Cprueba_clima1 = new ClearWeatherCard("Soleado")
    Cprueba_clima2 = new TorrentialRainWeatherCard("Lluvia Torrencial")
    Cprueba_clima3 = new BitingFrostWeatherCard("Escarcha Mordiente")
    Cprueba_clima4 = new ImpenetrableFogWeatherCard("Niebla Impenetrable")

    // Efectos
    Eunity_CB = new CloseBondEffect()
    Eunity_MB = new MoralBoosterEffect()
    Eclima_CW = new ClearWeatherEffect()
    Eclima_TR = new TorrentialRainEffect()
    Eclima_BF = new BitingFrostEffect()
    Eclima_IF = new ImpenetrableFogEffect()
    Enone = new NoneEffect()

    // Unidad
    Cprueba_melee = new MeleeCard("m1", new NoneEffect(), 2)
    Cprueba_distancia = new RangeCard("d1", new NoneEffect(), 3)
    Cprueba_asedio = new SiegeCard("a1", new NoneEffect(), 4)
    Cprueba_melee2 = new MeleeCard("m2", Eunity_MB, 3)
    Cprueba_distancia2 = new RangeCard("d2", Eunity_MB, 4)
    Cprueba_asedio2 = new SiegeCard("a2", Eunity_MB, 5)
    Cprueba_melee_CB = new MeleeCard("m1", Eunity_CB, 3)

    lista_melee = ListBuffer(Cprueba_melee, Cprueba_melee2, Cprueba_melee_CB)
    lista_distancia = ListBuffer(Cprueba_distancia, Cprueba_distancia2)
    lista_asedio = ListBuffer(Cprueba_asedio, Cprueba_asedio2)
  }

  test("NoneEffect hace nada"){
    Enone.apply(Cprueba_melee, lista_melee)
    Enone.apply(Cprueba_distancia, lista_distancia)
    Enone.apply(Cprueba_asedio, lista_asedio)

    assertEquals(Cprueba_melee.get_Force(), 2)
    assertEquals(Cprueba_melee2.get_Force(), 3)
    assertEquals(Cprueba_melee_CB.get_Force(), 3)

    assertEquals(Cprueba_distancia.get_Force(),3)
    assertEquals(Cprueba_distancia2.get_Force(),4)

    assertEquals(Cprueba_asedio.get_Force(), 4)
    assertEquals(Cprueba_asedio2.get_Force(), 5)
  }

  test("MoralBooster suma 1 a la fuerza de las cartas de la misma linea") {
    Eunity_MB.apply(Cprueba_melee2, lista_melee)
    Eunity_MB.apply(Cprueba_distancia2, lista_distancia)
    Eunity_MB.apply(Cprueba_asedio2, lista_asedio)

    assertEquals(Cprueba_melee.get_Force(), 3)
    assertEquals(Cprueba_melee2.get_Force(), 3) // <= Esta carta es la que aplica el efecto
    assertEquals(Cprueba_melee_CB.get_Force(), 4)

    assertEquals(Cprueba_distancia.get_Force(), 4)
    assertEquals(Cprueba_distancia2.get_Force(), 4) // <= Esta carta es la que aplica el efecto

    assertEquals(Cprueba_asedio.get_Force(), 5)
    assertEquals(Cprueba_asedio2.get_Force(), 5) // <= Esta carta es la que aplica el efecto
  }

  test("CloseBond duplica la fuerza de las cartas con mismo nombre en la linea") {
    Eunity_CB.apply(Cprueba_melee_CB, lista_melee)

    assertEquals(Cprueba_melee.get_Force(), 4)
    assertEquals(Cprueba_melee2.get_Force(), 3) // <= Esta carta tiene otro nombre
    assertEquals(Cprueba_melee_CB.get_Force(), 6) // La carta que aplica CB tambien es afectada
  }

  test("TorrentialRain establece a 1 la fuerza de las cartas asedio") {
    Eclima_TR.apply(Cprueba_clima2, lista_melee)
    Eclima_TR.apply(Cprueba_clima2, lista_distancia)
    Eclima_TR.apply(Cprueba_clima2, lista_asedio)

    assertEquals(Cprueba_melee.get_Force(), 2)
    assertEquals(Cprueba_melee2.get_Force(), 3)
    assertEquals(Cprueba_melee_CB.get_Force(), 3)

    assertEquals(Cprueba_distancia.get_Force(), 3)
    assertEquals(Cprueba_distancia2.get_Force(), 4)

    assertEquals(Cprueba_asedio.get_Force(), 1)
    assertEquals(Cprueba_asedio2.get_Force(), 1)
  }

  test("BitingFrost establece a 1 la fuerza de las cartas melee") {
    Eclima_BF.apply(Cprueba_clima3, lista_melee)
    Eclima_BF.apply(Cprueba_clima3, lista_distancia)
    Eclima_BF.apply(Cprueba_clima3, lista_asedio)

    assertEquals(Cprueba_melee.get_Force(), 1)
    assertEquals(Cprueba_melee2.get_Force(), 1)
    assertEquals(Cprueba_melee_CB.get_Force(), 1)

    assertEquals(Cprueba_distancia.get_Force(), 3)
    assertEquals(Cprueba_distancia2.get_Force(), 4)

    assertEquals(Cprueba_asedio.get_Force(), 4)
    assertEquals(Cprueba_asedio2.get_Force(), 5)
  }
  test("ImpenetrableFog establece a 1 la fuerza de las cartas rango") {
    Eclima_IF.apply(Cprueba_clima4, lista_melee)
    Eclima_IF.apply(Cprueba_clima4, lista_distancia)
    Eclima_IF.apply(Cprueba_clima4, lista_asedio)

    assertEquals(Cprueba_melee.get_Force(), 2)
    assertEquals(Cprueba_melee2.get_Force(), 3)
    assertEquals(Cprueba_melee_CB.get_Force(), 3)

    assertEquals(Cprueba_distancia.get_Force(), 1)
    assertEquals(Cprueba_distancia2.get_Force(), 1)

    assertEquals(Cprueba_asedio.get_Force(), 4)
    assertEquals(Cprueba_asedio2.get_Force(), 5)
  }

  test("Si las cartas estan afectadas por un clima, ClearWeather les quita el efecto, si no, hace nada") {
    // Se prueba que no haga nada
    Eclima_CW.apply(Cprueba_clima1, lista_melee)
    Eclima_CW.apply(Cprueba_clima1, lista_distancia)
    Eclima_CW.apply(Cprueba_clima1, lista_asedio)

    assertEquals(Cprueba_melee.get_Force(), 2)
    assertEquals(Cprueba_melee2.get_Force(), 3)
    assertEquals(Cprueba_melee_CB.get_Force(), 3)

    assertEquals(Cprueba_distancia.get_Force(), 3)
    assertEquals(Cprueba_distancia2.get_Force(), 4)

    assertEquals(Cprueba_asedio.get_Force(), 4)
    assertEquals(Cprueba_asedio2.get_Force(), 5)

    // Se prueba colocando un efecto de clima cualquiera
    Eclima_BF.apply(Cprueba_clima3, lista_melee)
    Eclima_BF.apply(Cprueba_clima3, lista_distancia)
    Eclima_BF.apply(Cprueba_clima3, lista_asedio)

    // Se vuelve a colocar Clima despejado
    Eclima_CW.apply(Cprueba_clima1, lista_melee)
    Eclima_CW.apply(Cprueba_clima1, lista_distancia)
    Eclima_CW.apply(Cprueba_clima1, lista_asedio)

    // Se comprueba que los valores sean los originales
    assertEquals(Cprueba_melee.get_Force(), 2)
    assertEquals(Cprueba_melee2.get_Force(), 3)
    assertEquals(Cprueba_melee_CB.get_Force(), 3)

    assertEquals(Cprueba_distancia.get_Force(), 3)
    assertEquals(Cprueba_distancia2.get_Force(), 4)

    assertEquals(Cprueba_asedio.get_Force(), 4)
    assertEquals(Cprueba_asedio2.get_Force(), 5)
  }

  test("Si se aplican los efectos en el siguiente orden: Cualquiera de clima(menos CW), MB o CB, CW el valor de fuerza " +
    "final es como si no se hubiera colocado el primer efecto"){
    Eclima_BF.apply(Cprueba_clima3, lista_melee)
    assertEquals(Cprueba_melee.get_Force(), 1)
    Eunity_MB.apply(Cprueba_melee2, lista_melee)
    assertEquals(Cprueba_melee.get_Force(), 1)
    Eclima_CW.apply(Cprueba_clima1, lista_melee)
    assertEquals(Cprueba_melee.get_Force(), 3)
  }

  test("No se puede aplicar un efecto si la carta que busca aplicar el efecto no lo tiene: NoneEffect"){
    val e = Assert.assertThrows(classOf[Exception], () => Enone.apply(Cprueba_melee2, lista_melee))
    assertEquals("The Card doesn't have the effect that you want to apply", e.getMessage)
  }
  test("No se puede aplicar un efecto si la carta que busca aplicar el efecto no lo tiene: MoralBooster"){
    val e = Assert.assertThrows(classOf[Exception], () => Eunity_MB.apply(Cprueba_melee, lista_melee))
    assertEquals("The Card doesn't have the effect that you want to apply", e.getMessage)

  }
  test("No se puede aplicar un efecto si la carta que busca aplicar el efecto no lo tiene: CloseBond"){
    val e = Assert.assertThrows(classOf[Exception], () => Eunity_CB.apply(Cprueba_melee2, lista_melee))
    assertEquals("The Card doesn't have the effect that you want to apply", e.getMessage)

  }
  test("No se puede aplicar un efecto si la carta que busca aplicar el efecto no lo tiene: ClearWeather"){
    val e = Assert.assertThrows(classOf[Exception], () => Eclima_CW.apply(Cprueba_clima2, lista_melee))
    assertEquals("The Card doesn't have the effect that you want to apply", e.getMessage)

  }
  test("No se puede aplicar un efecto si la carta que busca aplicar el efecto no lo tiene: TorrentialRain"){
    val e = Assert.assertThrows(classOf[Exception], () => Eclima_TR.apply(Cprueba_clima3, lista_melee))
    assertEquals("The Card doesn't have the effect that you want to apply", e.getMessage)

  }
  test("No se puede aplicar un efecto si la carta que busca aplicar el efecto no lo tiene: BitingFrost"){
    val e = Assert.assertThrows(classOf[Exception], () => Eclima_BF.apply(Cprueba_clima4, lista_melee))
    assertEquals("The Card doesn't have the effect that you want to apply", e.getMessage)

  }
  test("No se puede aplicar un efecto si la carta que busca aplicar el efecto no lo tiene: ImpenetrableFog"){
    val e = Assert.assertThrows(classOf[Exception], () => Eclima_IF.apply(Cprueba_clima1, lista_melee))
    assertEquals("The Card doesn't have the effect that you want to apply", e.getMessage)
  }

  test("No se puede aplicar un efecto si la carta que busca aplicar el efecto no lo tiene: NoneEffect - Clima a melee"){
    val e = Assert.assertThrows(classOf[Exception], () => Enone.apply(Cprueba_clima1, lista_melee))
    assertEquals("The Card doesn't have the effect that you want to apply", e.getMessage)
  }

  test("No se puede aplicar un efecto si la carta que busca aplicar el efecto no lo tiene: NoneEffect - Clima a range") {
    val e = Assert.assertThrows(classOf[Exception], () => Enone.apply(Cprueba_clima1, lista_distancia))
    assertEquals("The Card doesn't have the effect that you want to apply", e.getMessage)
  }

  test("No se puede aplicar un efecto si la carta que busca aplicar el efecto no lo tiene: NoneEffect - Clima a asedio") {
    val e = Assert.assertThrows(classOf[Exception], () => Enone.apply(Cprueba_clima1, lista_asedio))
    assertEquals("The Card doesn't have the effect that you want to apply", e.getMessage)
  }

  test("equals"){
    assert(!Eunity_CB.equals(Eunity_MB))
    assert(Eunity_CB.equals(new CloseBondEffect()))
    assert(!Eunity_MB.equals(Eunity_CB))
    assert(Eunity_MB.equals(new MoralBoosterEffect()))

    assert(Eclima_IF.equals(new ImpenetrableFogEffect()))
    assert(!Eclima_IF.equals(Eunity_CB))
    assert(!Eclima_TR.equals(Eunity_CB))
    assert(!Eclima_CW.equals(Eunity_CB))
    assert(!Eclima_BF.equals(Eunity_CB))
    assert(!Enone.equals(Eunity_CB))

    assert(!Cprueba_clima2.equals(Eunity_CB))
    assert(Cprueba_clima4.equals(new ImpenetrableFogWeatherCard("Niebla Impenetrable")))

    assert(!Cprueba_asedio.equals(Eunity_CB))
  }
}
