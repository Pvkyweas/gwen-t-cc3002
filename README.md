# Gwen't

![http://creativecommons.org/licenses/by/4.0/](https://i.creativecommons.org/l/by/4.0/88x31.png)

This work is licensed under a
[Creative Commons Attribution 4.0 International License](http://creativecommons.org/licenses/by/4.0/)

Context
-------

This project's goal is to create a (simplified) clone of the
[_Gwent_](https://www.playgwent.com/en)card game developed by [_CD PROJEKT RED_](https://cdprojektred.com/en/)

---

## Explicación Tarea 2 entrega final

### Jugador

El jugador tiene un nombre, un contador de gemas (que no puede bajar de 0), un mazo de cartas de tipo Deck, una mano de cartas de tipo HandDeck y una sección en el tablero, decidi que fuera un booleano ya que solo hay dos jugadores por partida. No hice que cada jugador tuviera su sección de cartas dentro de su variable section. 


### Mazos

Decidi hacer clases distintas para representar mazos que contendran las cartas de las manos y las del mazo, esto debido a que presentan las siguientes diferencias:

* En un mazo si importa el orden
* En un mazo la carta que se saca es la primera a diferencia de la mano, en donde se puede sacar de cualquier posición
* Tiene sentido barajar un mazo, pero no una mano (dado el tipo de juego)

Para los mazos normales utilice un Stack y para la mano un ListBuffer ya que cumplen con el requerimiento del orden. Decidi no implementar una interface por que una clase abstracta se ajusta mejor a la situación ambas clases no tendrian exactamente los mismos metodos por lo que, al crear un objeto de tipo mazo, si los metodos que quiera utilizar no estan en la interface, no podria llamarlos (o almenos eso me pasaba) entonces, como de todas formas no iba a crear los objetos como un tipo "el nombre de la interface", la clase abstracta termina funcionando mejor.

### Cartas

Siguiendo el feedback de la tarea 1, cambie como estan implementadas las cartas, ahora estas se separan en cartas de unidad y cartas de clima que no heredan de una misma clase abstracta, si implementan la interfaz ICard.

* Unidad: Estas implementan la interfaz ICardUnity que extiende de ICard e implementa los metodos en cuestión, se divide en 3 tipos de cartas MeleeCard, RangeCard y SiegeCard.

* Clima: Esta hereda de la clase abstracta AbstractCardWeather, no se divide en distintos tipos de cartas de clima como las de unidad, por que este tipo de cartas solo puede ir en una zona y los efectos decidi implementarlos de forma que sean objetos asi que no me hace sentido tener una clase para cada tipo de clima.

### Tablero

Para la implementación del tablero, considere distintas opciones pero decidi que el tablero solo contendria una zona para las cartas de clima y Secciones para cada jugador y la interacción entre estos y el tablero al agregar las cartas seria que el metodo del jugador que hace la acción de jugar una carta deberia de recibir el tablero en el que se debera jugar y la posición de la carta que se quiera jugar.

* Zonas
   Se pensaron 4 zonas, la de clima y las que contienen cartas de unidad. Para la de clima, como solo puede haber una carta se creo una variable que sea de tipo AbstractWeatherCard, en cuanto a las zonas de unidad (Melee, Range y Siege) estas tendrian un ListBuffer que contenga las cartas. Para las zonas de unidad se creo una clase abstracta AbstracUnityZone que contenga la implementación de los metodos, de esta clase solo heredan MeleeZone, RangeZone y SiegeZone.

* Secciones
   Para la implementación de las secciones, estas tendrian 3 zonas, una para las cartas cuerpo a cuerpo (melee), una para las rango y otra para las de asedio, tiene metodos especificos para agregar cartas a cada zona, esto se hizo por medio de double dispatch.

### Efectos

Decidi cambiar la implementación de los efectos de las cartas tratando de seguir algún diseño de los mostrados en clases, pero aún no estoy seguro de como hacer la implementación. Lo que si he decidido (al menos por ahora) es que los efectos sean un objeto de tipo IEffect y no un String o la implementación que habia pensado antes.