package fr.bomberman.game.exception;

public class InvalidSpriteManager extends Exception {

    private String name;

    public InvalidSpriteManager(String name) {
        this.name = name;
    }

    public void describe() {
        System.out.println("The ressource with name " + this.name + " is not avaible. Please try another name.");
    }

}
