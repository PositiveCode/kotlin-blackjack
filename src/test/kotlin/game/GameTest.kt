package game

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class GameTest {

    val game = Game()

    @Test
    fun `test both player and dealer blackjack results in DRAW`() {
        val playerCards = arrayListOf<Card>()
        playerCards.add(Card(1, CardType.CLOVER, FaceCard.NUMBER))
        playerCards.add(Card(10, CardType.CLOVER, FaceCard.NUMBER))

        val dealerCards = arrayListOf<Card>()
        dealerCards.add(Card(1, CardType.CLOVER, FaceCard.NUMBER))
        dealerCards.add(Card(10, CardType.CLOVER, FaceCard.NUMBER))

        val result = game.initialResults(playerCards, dealerCards)
        assertEquals(Game.GameResult.DRAW, result)

        val playerPts = game.playerPoints
        assertEquals(0, playerPts)
    }

    @Test
    fun `test player blackjack results in PLAYER_BLACKJACK`() {
        val playerCards = arrayListOf<Card>()
        playerCards.add(Card(1, CardType.CLOVER, FaceCard.NUMBER))
        playerCards.add(Card(10, CardType.CLOVER, FaceCard.NUMBER))

        val dealerCards = arrayListOf<Card>()
        dealerCards.add(Card(1, CardType.CLOVER, FaceCard.NUMBER))
        dealerCards.add(Card(1, CardType.CLOVER, FaceCard.NUMBER))

        val result = game.initialResults(playerCards, dealerCards)
        assertEquals(Game.GameResult.PLAYER_BLACKJACK, result)

        val playerPts = game.playerPoints
        assertEquals(15, playerPts)
    }

    @Test
    fun `test dealer blackjack results in DEALER_BLACKJACK`() {
        val playerCards = arrayListOf<Card>()
        playerCards.add(Card(2, CardType.CLOVER, FaceCard.NUMBER))
        playerCards.add(Card(10, CardType.CLOVER, FaceCard.NUMBER))

        val dealerCards = arrayListOf<Card>()
        dealerCards.add(Card(1, CardType.CLOVER, FaceCard.NUMBER))
        dealerCards.add(Card(10, CardType.CLOVER, FaceCard.NUMBER))

        val result = game.initialResults(playerCards, dealerCards)
        assertEquals(Game.GameResult.DEALER_BLACKJACK, result)

        val playerPts = game.playerPoints
        assertEquals(-10, playerPts)
    }

    @Test
    fun `test player and dealer same card value results in DRAW`() {
        val result = game.finalResults(16, 16)
        assertEquals(Game.GameResult.DRAW, result)

        val playerPts = game.playerPoints
        assertEquals(0, playerPts)
    }

    @Test
    fun `test player card value bigger than dealer results in PLAYER_WON`() {
        val result = game.finalResults(20, 16)
        assertEquals(Game.GameResult.PLAYER_WON, result)

        val playerPts = game.playerPoints
        assertEquals(10, playerPts)
    }

    @Test
    fun `test dealer card value bigger than player results in PLAYER_LOSE`() {
        val result = game.finalResults(16, 20)
        assertEquals(Game.GameResult.PLAYER_LOSE, result)

        val playerPts = game.playerPoints
        assertEquals(-10, playerPts)
    }
}