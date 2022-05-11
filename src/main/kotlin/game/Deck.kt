package game

import game.CardType.*

class Deck {
    init {
        getDeck()
    }

    fun getDeck() = arrayListOf(
        Card(1, CLOVER),
        Card(2, CLOVER),
        Card(3, CLOVER),
        Card(4, CLOVER),
        Card(5, CLOVER),
        Card(6, CLOVER),
        Card(7, CLOVER),
        Card(8, CLOVER),
        Card(9, CLOVER),
        Card(10, CLOVER),
        Card(10, CLOVER, FaceCard.JACK),
        Card(10, CLOVER, FaceCard.QUEEN),
        Card(10, CLOVER, FaceCard.KING),

        Card(1, ACE),
        Card(2, ACE),
        Card(3, ACE),
        Card(4, ACE),
        Card(5, ACE),
        Card(6, ACE),
        Card(7, ACE),
        Card(8, ACE),
        Card(9, ACE),
        Card(10, ACE),
        Card(10, ACE, FaceCard.JACK),
        Card(10, ACE, FaceCard.QUEEN),
        Card(10, ACE, FaceCard.KING),

        Card(1, DIAMOND),
        Card(2, DIAMOND),
        Card(3, DIAMOND),
        Card(4, DIAMOND),
        Card(5, DIAMOND),
        Card(6, DIAMOND),
        Card(7, DIAMOND),
        Card(8, DIAMOND),
        Card(9, DIAMOND),
        Card(10, DIAMOND),
        Card(10, DIAMOND, FaceCard.JACK),
        Card(10, DIAMOND, FaceCard.QUEEN),
        Card(10, DIAMOND, FaceCard.KING),

        Card(1, HEART),
        Card(2, HEART),
        Card(3, HEART),
        Card(4, HEART),
        Card(5, HEART),
        Card(6, HEART),
        Card(7, HEART),
        Card(8, HEART),
        Card(9, HEART),
        Card(10, HEART),
        Card(10, HEART, FaceCard.JACK),
        Card(10, HEART, FaceCard.QUEEN),
        Card(10, HEART, FaceCard.KING),
    )
}
