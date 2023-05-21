import java.util.HashSet;

/**
 * Elevator
 */
public class Elevator {

    int currentFloor;
    int places;
    int maxPlaces;
    public HashSet<Person> infoPerson = new HashSet<Person>();

    Elevator(int maxPlaces) {
        this.maxPlaces = maxPlaces;
    }

    int places() {
        return (this.maxPlaces - this.places);
    }

    synchronized boolean accept(Person myPerson) {
        if (this.places() > 0) {
            this.places++;
            infoPerson.add(myPerson);
            System.out.format("[Elevator]: %s acceptée, il reste %d places \n", myPerson.name, this.places());
            System.out.format("Personnes dans l'ascenseur\n");
            System.out.println(infoPerson);
            int i;
            for (i = 0; i < ElevatorUI.tabElevator.length; i++) {
                if (ElevatorUI.tabElevator[i] == null) {
                    ElevatorUI.tabElevator[i] = myPerson;
                    break;
                }
            }
            ElevatorUI.draw();
            return true;
        } else {
            System.out.format("Elevator: %s refusée, il reste %d places \n", myPerson.name, this.places());
            return false;
        }
    }

    synchronized void leave(Person myPerson) {
        this.places--;
        infoPerson.remove(myPerson);
        System.out.format("Elevator: %s est sortie, il reste %d places \n", myPerson.name, this.places());
        for (int i = 0; i < ElevatorUI.tabElevator.length; i++) {
            if (ElevatorUI.tabElevator[i].name == myPerson.name) {
                ElevatorUI.tabElevator[i] = null;
                break;
            }
            System.out.format(myPerson.name + " est sortie de l'ascenseur\n");
        }
    }
}