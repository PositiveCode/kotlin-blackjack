import game.Card
import game.Game
import game.Participant

fun main(args: Array<String>) {
    println("Welcome to Kotlin Blackjack!\n")

    var startOver = false

    val game = Game()
    while (!startOver) {
        game.start()
        showCard(game.dealerCards, Participant.DEALER)
        println()
        showCard(game.playerCards, Participant.PLAYER)
        println()
        println("Your cards value are: ${game.playerCardsValue()}")
        println()

        when (game.initialResults(game.playerCards, game.dealerCards)) {
            Game.GameResult.DRAW -> println("Its a draw, no one wins.")
            Game.GameResult.PLAYER_BLACKJACK -> println("Blackjack! You won!")
            Game.GameResult.DEALER_BLACKJACK -> println("Blackjack! Dealer won, you loss.")
            Game.GameResult.GAME_CONTINUE -> {
                /**
                 * keeps asking until the player has gotten 5 cards or not bust.
                 * */
                gamePlayerContinue(game)
                if (game.playerCardsValue() <= 21) {
                    /**
                     * Dealer continues if player stays
                     * */
                    println()
                    gameDealerContinue(game)

                    when (game.finalResults(game.playerCardsValue(), game.dealerCardsValue())) {
                        Game.GameResult.PLAYER_WON,
                        Game.GameResult.PLAYER_WON_21 -> {
                            println("Your card value: ${game.playerCardsValue()}")
                            println("Dealer card value: ${game.dealerCardsValue()}")
                            println("You won!")
                        }
                        Game.GameResult.PLAYER_LOSE -> {
                            println("Your card value: ${game.playerCardsValue()}")
                            println("Dealer card value: ${game.dealerCardsValue()}")
                            println("You lose!")
                        }
                    }
                }
            }
        }

        println()
        println("Your current points: ${game.playerPoints}")
        println()
        println("Play another?")
        println("1    - Yes")
        println("else - No")
        val playAnother = readln()
        startOver = when (playAnother) {
            "1" -> {
                game.start()
                false
            }
            else -> {
                true
            }
        }

    }
}

fun showCard(drawnCards: ArrayList<Card>, participant: Participant) {
    val mParticipant = when (participant) {
        Participant.PLAYER -> {
            "You've"
        }
        Participant.DEALER -> {
            "Dealer"
        }
    }

    println("$mParticipant drawn:")
    for (card in drawnCards) {
        println("${card.cardType} ${card.faceCard}  ${card.value}")
    }
}

fun gamePlayerContinue(game: Game) {
    while (game.playerCards.size < 5 || game.playerCardsValue() < 15 || game.playerCardsValue() < 21) {
        println("Do you wish to stay or hit?")
        println("1    - Hit")
        println("else - Stay")
        val stay = readln()
        if (stay == "1") {
            game.playerDraw()
            showCard(game.playerCards, Participant.PLAYER)
            println()
            println("Your cards value are: ${game.playerCardsValue()}")

            if (game.playerCardsValue() >= 22) {
                game.playerPoints -= 10
                println("You bust! You lost.")
                break
            }
        } else {
            break
        }
    }
}

fun gameDealerContinue(game: Game) {
    /**
     * Dealer must draw if it is less than 15 and less than 22
     * */
    println("Dealer's turn to draw... ")
    while (game.dealerCards.size < 5 && game.dealerCardsValue() < 15 && game.dealerCardsValue() < 21) {
        if (game.dealerCardsValue() < 15) {
            println("Dealer cards value below 16, drawing next card...")
            println()
            Thread.sleep(500)
        }
        game.dealerDraw()
        showCard(game.dealerCards, Participant.DEALER)
        println()
        println("Dealer cards value are: ${game.dealerCardsValue()}")
        println()

        if (game.dealerCardsValue() >= 22) {
            /**
             * end the draw when bust.
             * */
            break
        }
    }
}