/**
 * Person
 */
public class Person implements Runnable {

    String name;
    int FloorInit;
    int FloorFinal;
    Elevator E;

    public Person(String name, int FloorInit, int FloorFinal, Elevator E) {
        this.name = name;
        this.FloorInit = FloorInit;
        this.FloorFinal = FloorFinal;
        this.E = E;
    }

    public void run() {
        System.out.format("[%s]: Je débute ! \n", this.name);
        try {
            while (true) {
                Thread.sleep((long) (50000 * Math.random()));
                System.out.format("[%s]: Je demande à rentrer \n", this.name);
                this.rentrer();
                System.out.format("[%s]: Je viens d'entrer \n", this.name);
                Thread.sleep((long) (50000 * Math.random()));
                System.out.format("[%s]: Je demande à sortir \n", this.name);
                this.E.leave(this);
            }
        } catch (InterruptedException e) {
            System.out.format("[%s]: Je meurs ! \n", this.name);
        }
    }

    public void rentrer() throws InterruptedException {
        while (!this.E.accept(this)) {
            Thread.sleep((long) (50000 * Math.random()));
            System.out.format("[%s]: Je redemande à rentrer \n", this.name);
        }
    }

    public void setInit(int x) {
        this.FloorInit = x;
    }

    public void setFinal(int x) {
        this.FloorFinal = x;
    }

    public static void main(String[] args) {
        ElevatorUI myUI = new ElevatorUI();
        Elevator myElevator = new Elevator(10);
        Thread[] people = new Thread[20];
        for (int i = 0; i < people.length; i++) {
            int floor = (int) (Math.random() * 4);
            int floor2 = (int) (Math.random() * 4);
            people[i] = new Thread(new Person("Personne " + i, floor, floor2, myElevator));
            people[i].start();
            ElevatorUI.draw();
        }
    }
}