import javax.swing.*;

public class ElevatorUI extends JFrame {
    private static final int WIDTH = 390;
    private static final int HEIGHT = 575;
    private static final String TITLE = "Elevator";
    private static final String BUILDING_IMAGE_PATH = "/Building.png";
    private static final String ELEVATOR_IMAGE_PATH = "/Elevator.png";
    private static final int ELEVATOR_WIDTH = 1000;
    private static final int ELEVATOR_HEIGHT = 900;
    public static Person[] tabElevator = new Person[10];
    public static int currentFloor = 1;

    private JLabel buildingLabel;
    private static JLabel elevatorLabel;

    public ElevatorUI() {
        initComponents();
    }

    private void initComponents() {
        this.setResizable(false);
        this.setTitle(TITLE);
        this.getContentPane().setLayout(null);

        buildingLabel = new JLabel();
        buildingLabel.setIcon(new ImageIcon(getClass().getResource(BUILDING_IMAGE_PATH)));
        buildingLabel.setBounds(0, 0, WIDTH, HEIGHT);

        elevatorLabel = new JLabel();
        elevatorLabel.setIcon(new ImageIcon(getClass().getResource(ELEVATOR_IMAGE_PATH)));
        elevatorLabel.setBounds(-90, 50, ELEVATOR_WIDTH, ELEVATOR_HEIGHT);
        buildingLabel.add(elevatorLabel);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        this.getContentPane().add(buildingLabel);
        this.setVisible(true);
    }

    public static void draw() {
        for (int i = 0; i < tabElevator.length; i++) {
            if (tabElevator[i] != null) {
                int ystep = 10;
                while (tabElevator[i].FloorFinal > ElevatorUI.currentFloor) {
                    ElevatorUI.currentFloor++;
                    elevatorLabel.setLocation(-90, elevatorLabel.getY() - ystep);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    tabElevator[i].FloorInit++;
                }
                while (tabElevator[i].FloorFinal < ElevatorUI.currentFloor) {
                    ElevatorUI.currentFloor--;
                    elevatorLabel.setLocation(-90, elevatorLabel.getY() + ystep);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    tabElevator[i].FloorInit--;
                }
            }
        }
    }
}