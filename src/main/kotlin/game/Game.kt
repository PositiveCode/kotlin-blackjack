package game

import kotlin.random.Random
import kotlin.random.nextInt

class Game {
    var playerPoints = 0

    /**
     * This contains the full deck game deck
     * */
    private var gameDeck = arrayListOf<Card>()

    /**
     * add all player cards into this arraylist
     * */
    var playerCards = arrayListOf<Card>()

    /**
     * add all dealer cards into this arraylist
     * */
    var dealerCards = arrayListOf<Card>()

    fun start() {
        resetDeck()
        resetDrawnCards()
        dealerDraw()
        playerDraw()
        dealerDraw()
        playerDraw()
    }

    fun initialResults(playerCards: ArrayList<Card>, dealerCards: ArrayList<Card>): GameResult {
        /**
         * initial results checks:
         * - blackjacks wins
         * - blackjacks draws
         * */

        return when {
            isBlackjack(playerCards) && isBlackjack(dealerCards) -> {
                GameResult.DRAW
            }

            isBlackjack(playerCards) -> {
                playerPoints += 15
                GameResult.PLAYER_BLACKJACK
            }

            isBlackjack(dealerCards) -> {
                playerPoints -= 10
                GameResult.DEALER_BLACKJACK
            }
            else -> GameResult.GAME_CONTINUE
        }
    }

    fun finalResults(playerCardValue: Int, dealerCardValue: Int): GameResult {
        /**
         * final results checks
         * - check dealer bust
         * - if player > dealer = PLAYER_WON
         * - if dealer > player = PLAYER_LOSE
         * */

        return when {
            dealerCardValue > 22 -> {
                println("Dealer bust.")
                playerPoints += 10
                GameResult.PLAYER_WON
            }

            (playerCardValue > dealerCardValue) && playerCardValue < 22 -> {
                playerPoints += 10
                GameResult.PLAYER_WON
            }
            (dealerCardValue > playerCardValue) && dealerCardValue < 22 -> {
                playerPoints -= 10
                GameResult.PLAYER_LOSE
            }
            else -> {
                GameResult.DRAW
            }
        }
    }

    fun playerCardsValue() = playerCards.sumOf { it.value }

    fun dealerCardsValue() = dealerCards.sumOf { it.value }

    private fun isBlackjack(cards: ArrayList<Card>) =
        cards[0].value == 1 && cards[1].value == 10 ||
                cards[1].value == 1 && cards[0].value == 10

    fun dealerDraw() {
        drawCard(Participant.DEALER)
    }

    fun playerDraw() {
        drawCard(Participant.PLAYER)
    }

    private fun drawCard(participant: Participant) {
        /**
         * always draws a card from the gameDeck size.
         * */
        val deckIndex = Random.nextInt(0 until gameDeck.size)

        /**
         * add the card into playerCard or dealerCard
         * */
        when (participant) {
            Participant.PLAYER -> {
                playerCards.add(gameDeck[deckIndex])
            }
            Participant.DEALER -> {
                dealerCards.add(gameDeck[deckIndex])
            }
        }

        /**
         * remove the card from the gameDeck
         * */
        gameDeck.removeAt(deckIndex)
    }

    private fun resetDeck() {
        val deck = Deck()
        if (gameDeck.isNotEmpty()) {
            /**
             * reset the gameDeck to empty
             * */
            gameDeck = arrayListOf()
        }
        gameDeck = deck.getDeck()
    }

    private fun resetDrawnCards() {
        playerCards = arrayListOf()
        dealerCards = arrayListOf()
    }

    enum class GameResult {
        DRAW,
        PLAYER_BLACKJACK,
        DEALER_BLACKJACK,
        GAME_CONTINUE,

        PLAYER_WON,
        PLAYER_WON_21,
        PLAYER_LOSE
    }
}