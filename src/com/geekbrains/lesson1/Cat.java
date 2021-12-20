package com.geekbrains.lesson1;

public class Cat implements Actable {

    private int stamina;
    private int maxJumpHeight;
    private String name;
    private boolean isPassed = true;

    public Cat(String name, int stamina, int maxJumpHeight) {
        this.name = name;
        this.stamina = stamina;
        this.maxJumpHeight = maxJumpHeight;
    }

    public void run(int distance) {

        if (distance > this.stamina) {
            isPassed = false;
            System.out.println(this.name + " не пробежал дистанцию");
        } else {
            System.out.printf("%s пробежал %dм%s", this.name, distance, System.lineSeparator());
        }
    }

    public void jump(int height) {
        if (height > this.maxJumpHeight) {
            isPassed = false;
            System.out.println(this.name + " не перепрыгнул стену");
        } else {
            System.out.printf("%s перепрыгнул стену высотой %dм%s",
                    this.name, height, System.lineSeparator());
        }
    }

    public boolean isBarrierPassed() {
        return isPassed;
    }

}
