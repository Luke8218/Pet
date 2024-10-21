import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;

public class Game {

    public Pet pet;
    public List<Interaction> interactions = new ArrayList<>();

    private JFrame mainFrame;
    private JPanel petStatsPanel;
    private JPanel petInteractions;
    private JPanel petImagePanel;

    public Game() {
        prepareGUI();
    }

    private void prepareGUI(){
        mainFrame = new JFrame("Pet Game");
        mainFrame.setSize(400,200);
        mainFrame.setLayout(new GridLayout(3, 1));
  
        JLabel headerLabel = new JLabel("Please enter a name for your pet", JLabel.CENTER);
        JTextField petNameTextField = new JTextField();
        JButton submitButton = new JButton("Submit");
        
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }        
        });    
        submitButton.addActionListener(e -> {
            String petName = petNameTextField.getText();
            if (petName.isEmpty()) {
                headerLabel.setText("Pet name cannot be empty.");
            } else {
                pet = new Pet(petName);
                mainFrame.dispose(); // Close the current frame
                prepareGameScreen();
            }
        });

        mainFrame.add(headerLabel);
        mainFrame.add(petNameTextField);
        mainFrame.add(submitButton);
        mainFrame.setVisible(true);
     }

    private void prepareGameScreen() {
        mainFrame = new JFrame("Pet Game");
        mainFrame.setSize(400,600);
        mainFrame.setLayout(new GridLayout(4, 1));

        // Pet Image
        petImagePanel = new JPanel();
        ImageIcon imageIcon = new ImageIcon("/Users/lcheskin/Documents/QMUL/Year 1/Projects/Pet/src/" + pet.getPetImageName());
        ImageIcon scaledImageIcon = new ImageIcon(imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        JLabel imageIconLabel = new JLabel(scaledImageIcon);
        petImagePanel.add(imageIconLabel);

        // Pet Stats Panel
        petStatsPanel = new JPanel();
        GridLayout petStatsPanelLayout = new GridLayout(0, 1);
        petStatsPanel.setLayout(petStatsPanelLayout);

        JLabel nameLabel = new JLabel("Name: " + pet.name, JLabel.CENTER);
        JLabel ageLabel = new JLabel("Age: " + pet.age, JLabel.CENTER);
        JLabel foodLabel = new JLabel("Food: " + pet.food, JLabel.CENTER);
        JLabel awakenessLabel = new JLabel("Awakeness: " + pet.awakeness, JLabel.CENTER);
        JLabel exerciseLabel = new JLabel("Exercise: " + pet.exercise, JLabel.CENTER);

        petStatsPanel.add(nameLabel);
        petStatsPanel.add(ageLabel);
        petStatsPanel.add(foodLabel);
        petStatsPanel.add(awakenessLabel);
        petStatsPanel.add(exerciseLabel);

        // Pet Interaction Buttons
        JPanel petButtons = new JPanel();
        GridLayout petButtonsPanelLayout = new GridLayout(0, 1);
        petButtons.setLayout(petButtonsPanelLayout);

        JButton feedButton = new JButton("Feed");
        JButton sleepButton = new JButton("Sleep");
        JButton exerciseButton = new JButton("Exercise");
        JButton quitButton = new JButton("Quit");

        feedButton.addActionListener(e -> feedPet());
        sleepButton.addActionListener(e -> sleepPet());
        exerciseButton.addActionListener(e -> exercisePet());
        quitButton.addActionListener(e -> System.exit(0));

        petButtons.add(feedButton);
        petButtons.add(sleepButton);
        petButtons.add(exerciseButton);
        petButtons.add(quitButton);

        // Pet Interaction History
        petInteractions = new JPanel();
        GridLayout petInteractionsPanelLayout = new GridLayout(0, 1);

        petInteractions.setLayout(petInteractionsPanelLayout);
        petInteractions.add(new JLabel("Interactions"));

        for (Interaction interaction : interactions) {
            petInteractions.add(new JLabel(interaction.type.toString() + " - " + new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(interaction.date)));
        }
        
        JScrollPane interactionsScrollPane = new JScrollPane(petInteractions);


        mainFrame.add(petImagePanel);
        mainFrame.add(petStatsPanel);
        mainFrame.add(petButtons);
        mainFrame.add(interactionsScrollPane);
        mainFrame.setVisible(true);  

        startGameTicker();
    }

    private void startGameTicker() {
        Thread gameTicker = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(5000); // Wait for 5 seconds
                    pet.age++;
                    pet.food -= 15;
                    pet.awakeness -= 10;
                    pet.exercise -= 5;
                    refreshStats();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        gameTicker.start();
    }

    private void refreshStats() {

        petImagePanel.removeAll();
        ImageIcon imageIcon = new ImageIcon("/Users/lcheskin/Documents/QMUL/Year 1/Projects/Pet/src/" + pet.getPetImageName());
        ImageIcon scaledImageIcon = new ImageIcon(imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        JLabel imageIconLabel = new JLabel(scaledImageIcon);
        petImagePanel.add(imageIconLabel);
        petImagePanel.revalidate();
        petImagePanel.repaint();

        petStatsPanel.removeAll();
        petStatsPanel.add(new JLabel("Name: " + pet.name, JLabel.CENTER));
        petStatsPanel.add(new JLabel("Age: " + pet.age, JLabel.CENTER));
        petStatsPanel.add(new JLabel("Food: " + pet.food, JLabel.CENTER));
        petStatsPanel.add(new JLabel("Awakeness: " + pet.awakeness, JLabel.CENTER));
        petStatsPanel.add(new JLabel("Exercise: " + pet.exercise, JLabel.CENTER));
        petStatsPanel.revalidate();
        petStatsPanel.repaint();

        petInteractions.removeAll();
        petInteractions.add(new JLabel("Interactions"));

        for (Interaction interaction : interactions) {
            petInteractions.add(new JLabel(interaction.type.toString() + " - " + new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(interaction.date)));
        }

        petInteractions.revalidate();
        petInteractions.repaint();
    }

    private void feedPet() {
        pet.feed();
        interactions.add(new Interaction(Interaction.Type.FEED));
        refreshStats();
    }

    private void sleepPet() {
        pet.sleep();
        interactions.add(new Interaction(Interaction.Type.SLEEP));
        refreshStats();
    }

    private void exercisePet() {
        pet.exercise();
        interactions.add(new Interaction(Interaction.Type.EXERCISE));
        refreshStats();
    }
}
