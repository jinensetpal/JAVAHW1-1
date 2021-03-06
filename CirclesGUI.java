import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CirclesGUI {

    private static JFrame mainFrame;
    private static JPanel mainPanel, navPanel, inputPanel, buttonPanel;
    private static JLabel radLabel, colLabel;
    private static JTextField navField, radField, colField;
    private static JButton upButton, downButton, addButton, modifyButton, saveButton, averageButton;

    private static int count = 0, nav = 0;
    private static Circle circles[] = new Circle[20];

    public static void main(String[] args) {
        loadCircleData();
        init();
    }

    private static void init() {
        mainFrame = new JFrame("Circles");
        mainPanel = new JPanel(new GridLayout(3, 1));

        navPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        downButton = new JButton("<");
        navField = new JTextField(20);
        navField.setEditable(false);
        navField.setText(circles[nav].toString());
        upButton = new JButton(">");

        navPanel.add(downButton);
        navPanel.add(navField);
        navPanel.add(upButton);

        inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        radLabel = new JLabel("Radius");
        radField = new JTextField(5);
        colLabel = new JLabel("Color");
        colField = new JTextField(10);
        inputPanel.add(radLabel);
        inputPanel.add(radField);
        inputPanel.add(colLabel);
        inputPanel.add(colField);

        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        addButton = new JButton("Add");
        modifyButton = new JButton("Modify");
        saveButton = new JButton("Save");
        averageButton = new JButton("Average");
        saveButton.setEnabled(false);
        buttonPanel.add(addButton);
        buttonPanel.add(modifyButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(averageButton);

        mainPanel.add(navPanel);
        mainPanel.add(inputPanel);
        mainPanel.add(buttonPanel);

        mainFrame.add(mainPanel);
        mainFrame.setSize(350, 300);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);

        upButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nav >= count - 1) {
                    JOptionPane.showMessageDialog(null, "No more circles to show!");
                    nav = count;
                } else
                    navField.setText(circles[++nav].toString());
            }
        });

        downButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nav <= 0) {
                    JOptionPane.showMessageDialog(null, "No more circles to show!");
                    nav = 0;
                } else
                    navField.setText(circles[--nav].toString());
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (count >= 20)
                    JOptionPane.showConfirmDialog(null, "Array full! Maximum 20 circles reached.");
                else {
                    if (radField.getText().equals("") || colField.getText().equals(""))
                        JOptionPane.showMessageDialog(null, "Please enter the circle data to add one!");
                    else {
                        double radius = Double.parseDouble(radField.getText().trim());
                        String color = colField.getText().trim();
                        Circle circle = new Circle(radius, color);
                        circles[count++] = circle;
                        nav = count - 1;
                        navField.setText(circles[nav].toString());
                        radField.setText("");
                        colField.setText("");
                        JOptionPane.showMessageDialog(null, "Circle added successfully! Number of circles = " + count + ".");
                        count++;
                    }
                }
            }
        });

        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addButton.setEnabled(false);
                modifyButton.setEnabled(false);
                averageButton.setEnabled(false);
                saveButton.setEnabled(true);
                radField.setText(circles[nav].getRadius() + "");
                colField.setText(circles[nav].getColor());
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (radField.getText().equals("") || colField.getText().equals(""))
                    JOptionPane.showMessageDialog(null, "Please enter the circle data to add one!");
                else {
                    double rad = Double.parseDouble(radField.getText().trim());
                    String col = colField.getText().trim();
                    circles[nav].setRadius(rad);
                    circles[nav].setColor(col);
                    radField.setText("");
                    colField.setText("");
                    addButton.setEnabled(true);
                    modifyButton.setEnabled(true);
                    saveButton.setEnabled(false);
                    averageButton.setEnabled(true);
                    JOptionPane.showMessageDialog(null, "Changes to circle at index " + (nav + 1) + " saved successfully.");
                    navField.setText(circles[nav].toString());
                }
            }
        });
    }

    private static void loadCircleData() {
        circles[count++] = new Circle(5, "White");
        circles[count++] = new Circle(7, "Black");
        circles[count++] = new Circle(9, "Gray");
        circles[count++] = new Circle(11, "Blue");
        circles[count++] = new Circle(13, "Red");
    }
    
  private static double getAverage() { 
    void res = 0; 
    int counter = 0;
    while (counter < count) {
        res += circles[counter].getRadius(); 
        counter++;
    averageButton.addActionListener(e -> {
    JOptionPane.showMessageDialog(null, "The average of the circles is: " + res / circles.length);
});
        
    }
    }
    
}
