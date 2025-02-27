package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class QuizView extends JFrame {
    JLabel question, option1, option2, option3, option4;
    JButton submit;

    public QuizView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);
        setBackground(Color.WHITE);
        setSize(600, 700);

        question = new JLabel("Hello this is the question");
        question.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        question.setBounds(100, 100, 1100, 22);

        option1 = createMaterialLabel("Option1");
        option2 = createMaterialLabel("Option2");
        option3 = createMaterialLabel("Option3");
        option4 = createMaterialLabel("Option4");

        submit = createMaterialButton("SUBMIT");

        // Set bounds for all the options
        option1.setBounds(100, 150, 360, 60);
        option2.setBounds(100, 230, 360, 60);
        option3.setBounds(100, 310, 360, 60);
        option4.setBounds(100, 390, 360, 60);


        // Set padding for options
//        option1.setOpaque(true);
//        option3.setOpaque(true);
//
//        option4.setOpaque(true);

        submit.setBounds(100, 470, 360, 60);

        add(question);
        add(option1);
        add(option2);
        add(option3);
        add(option4);
        add(submit);
    }

    public static JButton createMaterialButton(String buttonText) {
        JButton button = new JButton(buttonText);
        button.setPreferredSize(new Dimension(260, 50));
        Color primaryColor = new Color(33, 150, 243);  // Material Design Blue
        button.setBackground(primaryColor);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setFocusPainted(false);
        button.setMargin(new Insets(10, 10, 10, 10));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(primaryColor.darker());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(primaryColor);
            }
        });
        return button;
    }

        public static JLabel createMaterialLabel(String labelText) {
        JLabel label = new JLabel(labelText, SwingConstants.CENTER);
        label.setPreferredSize(new Dimension(360, 60));
        label.setFont(new Font("Arial", Font.PLAIN, 18));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setForeground(new Color(122, 128, 137));
        label.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        label.setOpaque(true);
        label.setBackground(Color.WHITE);
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return label;
    }

    public JButton getSubmitButton() {
        return submit;
    }

    public JLabel getOption1() {
        return option1;
    }

    public JLabel getOption2() {
        return option2;
    }

    public JLabel getOption3() {
        return option3;
    }

    public JLabel getOption4() {
        return option4;
    }

    public JLabel getQuestionLabel() {
        return question;
    }
    public static void main(String[] args) {
        new QuizView();
    }
}

