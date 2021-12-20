package com.geekbrains.lesson1;

public class Main {

    public static void main(String[] args) {

        Barrier[] barriers = new Barrier[]{
                new RunningTrack(20),
                new Wall(5),
                new RunningTrack(25),
                new Wall(10),
                new RunningTrack(30),
                new Wall(15),
        };

        Actable[] participants = new Actable[]{
                new Man("John Wick", 20, 5),
                new Cat("Pushok", 25, 10),
                new Robot("Verter", 30, 15),
        };

        for (int i = 0; i < participants.length; i++) {
            for (int j = 0; j < barriers.length; j++) {
                if (barriers[j] instanceof RunningTrack) {
                    participants[i].run(barriers[j].getBarrierValue());
                }
                if (barriers[j] instanceof Wall) {
                    participants[i].jump(barriers[j].getBarrierValue());
                }
                if (!participants[i].isBarrierPassed()) break;
            }
        }
    }
}
