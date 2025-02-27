package Controller;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

import Model.QuestionDAO;
import Model.QuestionModel;
import View.QuizView;
import View.ResultView;

public class QuizController {
    private QuizView quizView;
    private ArrayList<QuestionModel> questionList;
    private int selectedOptionPosition = 0;
    private int currentPosition = 1;
    private int correctAnswers = 0;

    public QuizController(QuizView view, String userName) {
        this.quizView = view;
        this.questionList = QuestionDAO.getQuestionsFromDB();

        setQuestion();

        quizView.getOption1().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectedOptionView(quizView.getOption1(), 1);
            }
        });

        quizView.getOption2().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectedOptionView(quizView.getOption2(), 2);
            }
        });

        quizView.getOption3().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectedOptionView(quizView.getOption3(), 3);
            }
        });

        quizView.getOption4().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectedOptionView(quizView.getOption4(), 4);
            }
        });

        quizView.getSubmitButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                submitAnswer(userName);
            }
        });
    }

    public void setQuestion() {
        resetOptionView();
        QuestionModel question = questionList.get(currentPosition - 1);

        quizView.getQuestionLabel().setText(question.getQuestion());
        quizView.getOption1().setText(question.getOption1());
        quizView.getOption2().setText(question.getOption2());
        quizView.getOption3().setText(question.getOption3());
        quizView.getOption4().setText(question.getOption4());

        if (currentPosition == questionList.size()) {
            quizView.getSubmitButton().setText("FINISH");
        } else {
            quizView.getSubmitButton().setText("SUBMIT");
        }
    }

    private void selectedOptionView(JLabel option, int selectedNumber) {
        resetOptionView();
        selectedOptionPosition = selectedNumber;
        option.setBackground(Color.decode("#9013FE"));
        option.setForeground(Color.WHITE);
    }

    private void resetOptionView() {
        quizView.getOption1().setBackground(Color.WHITE);
        quizView.getOption1().setForeground(new Color(122, 128, 137));
        quizView.getOption2().setBackground(Color.WHITE);
        quizView.getOption2().setForeground(new Color(122, 128, 137));
        quizView.getOption3().setBackground(Color.WHITE);
        quizView.getOption3().setForeground(new Color(122, 128, 137));
        quizView.getOption4().setBackground(Color.WHITE);
        quizView.getOption4().setForeground(new Color(122, 128, 137));
    }

    private void submitAnswer(String userName) {
        if (selectedOptionPosition == 0) {
            currentPosition++;
            if (currentPosition <= questionList.size()) {
                setQuestion();
            } else {
                // Show result here
                new ResultView(userName, correctAnswers);
            }
        } else {
            QuestionModel question = questionList.get(currentPosition - 1);
            if (question.getCorrectOption() != selectedOptionPosition) {
                highlightAnswer(selectedOptionPosition, "#FF4444");
            } else {
                correctAnswers++;
            }
            highlightAnswer(question.getCorrectOption(), "#99CC00");

            if (currentPosition == questionList.size()) {
                quizView.getSubmitButton().setText("FINISH");
            } else {
                quizView.getSubmitButton().setText("NEXT");
            }
            selectedOptionPosition = 0;
        }
    }

    private void highlightAnswer(int option, String color) {
        switch (option) {
            case 1 -> {
                quizView.getOption1().setBackground(Color.decode(color));
                quizView.getOption1().setForeground(Color.WHITE);
            }
            case 2 -> {
                quizView.getOption2().setBackground(Color.decode(color));
                quizView.getOption2().setForeground(Color.WHITE);
            }
            case 3 -> {
                quizView.getOption3().setBackground(Color.decode(color));
                quizView.getOption3().setForeground(Color.WHITE);
            }
            case 4 -> {
                quizView.getOption4().setBackground(Color.decode(color));
                quizView.getOption4().setForeground(Color.WHITE);
            }
        }
    }
}