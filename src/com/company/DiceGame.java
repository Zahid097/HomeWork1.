package com.company;

import java.util.*;

import static com.company.GamePlayer.COMPUTER;
import static com.company.GamePlayer.USER;

public class DiceGame {

    Random random = new Random();

    public GameResult usersTurn() {
        Scanner scan = new Scanner(System.in);
        Scanner scan2 = new Scanner(System.in);
        int guess = 0;
        int x = 0;
        int probability = 2;
        System.out.println("Game start!");
        do {
            System.out.println("You need enter num [2-12]: ");
            guess = scan.nextInt();


        } while (guess < 2 || guess > 12);
        System.out.println("Do wanna cheat?[y/n]");
        String ans = scan2.nextLine();
        boolean minus = true;
        if (ans.equals("y")) {
            int cheating1 = random.nextInt(1, probability + 1);
            int cheating2 = random.nextInt(1, probability + 1);
            minus = false;
            System.out.println(cheating1 + " - " + cheating2 + " " + probability);
            if (cheating1 == cheating2) {
                guess = getResultOfRolling();
                minus = true;
            }
        }

        int resultOfRolling = getResultOfRolling();

        getResult(USER,
                resultOfRolling,
                guess,
                "You are winning!!!",
                "You are losing..."
        );

        int result = getResult(resultOfRolling, guess);

        GameResult gameResult = new GameResult(guess, resultOfRolling, result);
        return gameResult;
    }


    public GameResult computersTurn() {
        Random random = new Random();
        System.out.println("Computer start!");
        int guess = random.nextInt(1, 12);

        int resultOfRolling = getResultOfRolling();

        getResult(COMPUTER,
                resultOfRolling,
                guess,
                "Computer is winning!!!",
                "Computer is losing..."
        );

        int result = getResult(resultOfRolling, guess);

        GameResult gameResult = new GameResult(guess, resultOfRolling, result);
        System.out.println("\n--------------------Finish game--------------" + "\n--------------------RESULTS:----------------");
        return gameResult;
    }

    private int getResultOfRolling() {
        Dice dice = new Dice();
        System.out.println("Scrolling a dice...");
        int resultOfRolling = dice.roll();

        Dice dice2 = new Dice();
        System.out.println("Scrolling a second dice...");
        resultOfRolling += dice2.roll();

        return resultOfRolling;
    }

    public void playPart3() {
        Map<GamePlayer, List<GameResult>> results = new HashMap<>(Map.of(
                USER, new ArrayList<>(),
                COMPUTER, new ArrayList<>()
        ));


        for (int i = 0; i < 6; i++) {
            if (i % 2 == 0) {
                GameResult gameResult = usersTurn();
                results.get(USER).add(gameResult);
            } else {
                GameResult gameResult = computersTurn();
                results.get(COMPUTER).add(gameResult);
            }
        }

        printResult(results);
    }

    private void printResult(Map<GamePlayer, List<GameResult>> results) {
        System.out.println(USER.name());
        List<GameResult> usersResult = results.get(USER);
        usersResult.forEach(System.out::print);

        System.out.println(COMPUTER.name());
        List<GameResult> computerResults = results.get(COMPUTER);
        computerResults.forEach(System.out::print);
    }

    private void getResult(GamePlayer gamePlayer,
                           int diceScore,
                           int userGuess,
                           String winningMessage,
                           String losingMessage) {
        int result = getResult(diceScore, userGuess);
        if (gamePlayer.equals(USER)) {
            System.out.println("Score : " + diceScore);
            System.out.println("Your guess : " + userGuess);
            System.out.println("TotalScore : " + result);
        } else {
            System.out.println("Score : " + diceScore);
            System.out.println("Computer guess : " + userGuess);
            System.out.println("TotalScore : " + result);
        }
        System.out.println(result > 0 ? winningMessage : losingMessage);
    }

    private int getResult(int diceScore, int userGuess) {
        return diceScore - Math.abs(diceScore - userGuess) * 2;
    }
}
