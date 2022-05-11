package game

class Card(val value: Int, val cardType: CardType, val faceCard: FaceCard = FaceCard.NUMBER)

enum class CardType {
    CLOVER,
    ACE,
    HEART,
    DIAMOND
}

enum class FaceCard {
    NUMBER,
    JACK,
    QUEEN,
    KING
}