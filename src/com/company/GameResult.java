package com.company;

public class GameResult {

    private int predicted;
    private int dice;
    private int result;

    public GameResult() {
    }

    public GameResult(int predicted, int dice, int result) {
        this.predicted = predicted;
        this.dice = dice;
        this.result = result;
    }

    public int getPredicted() {
        return predicted;
    }

    public void setPredicted(int predicted) {
        this.predicted = predicted;
    }

    public int getDice() {
        return dice;
    }

    public void setDice(int dice) {
        this.dice = dice;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return

                "----------+-------------------+--------------" + "\n" +
                        "          | Predicated: " + predicted + "\n" +
                        "          | Dice: " + dice + "\n" +
                        "          | Result: " + result + "\n" +
                        "----------+------------------+--------------\n";
    }
}
