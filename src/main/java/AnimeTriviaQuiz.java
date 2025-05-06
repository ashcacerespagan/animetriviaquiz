import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AnimeTriviaQuiz extends JFrame {
    private final JTextArea questionLabel;  // Change to JTextArea for line wrapping
    private final JRadioButton option1;
    private final JRadioButton option2;
    private final JRadioButton option3;
    private final JRadioButton option4;
    private final JButton submitButton;
    private final ButtonGroup optionsGroup;
    private final JLabel scoreLabel;

    private List<Question> questions;
    private List<Question> incorrectQuestions;  // Track incorrect questions
    private int currentQuestionIndex = 0;
    private int score = 0;
    private final StringBuilder incorrectQuestionsText = new StringBuilder("If not refreshed within 3 seconds, click around the screen to reveal your missed questions!\n\n");

    public AnimeTriviaQuiz() {
        // Initialize questions
        initializeQuestions();

        // Set up the frame
        setTitle("Anime Trivia Quiz");
        setSize(600, 400);  // Increase default size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set up components
        questionLabel = new JTextArea(3, 40);  // JTextArea for wrapping and larger width
        questionLabel.setWrapStyleWord(true);
        questionLabel.setLineWrap(true);
        questionLabel.setEditable(false);
        questionLabel.setOpaque(false);

        option1 = new JRadioButton();
        option2 = new JRadioButton();
        option3 = new JRadioButton();
        option4 = new JRadioButton();
        submitButton = new JButton("Submit");
        scoreLabel = new JLabel("Score: 0");

        // Group radio buttons
        optionsGroup = new ButtonGroup();
        optionsGroup.add(option1);
        optionsGroup.add(option2);
        optionsGroup.add(option3);
        optionsGroup.add(option4);

        // Set up layout
        setLayout(new BorderLayout());
        JPanel optionsPanel = new JPanel(new GridLayout(4, 1));
        optionsPanel.add(option1);
        optionsPanel.add(option2);
        optionsPanel.add(option3);
        optionsPanel.add(option4);

        add(questionLabel, BorderLayout.NORTH);
        add(optionsPanel, BorderLayout.CENTER);
        add(submitButton, BorderLayout.SOUTH);
        add(scoreLabel, BorderLayout.EAST);

        // Display the first question
        displayQuestion();

        // Add submit button action
        submitButton.addActionListener(_ -> checkAnswer());
    }

    private void initializeQuestions() {
        questions = new ArrayList<>();
        incorrectQuestions = new ArrayList<>();  // Initialize the list for incorrect questions

        // Questions for My Hero Academia
        questions.add(new Question("Who is the main protagonist in 'My Hero Academia'?",
                "Izuku Midoriya", "Sasuke Uchiha", "Naruto Uzumaki", "Ichigo Kurosaki", "Izuku Midoriya"));
        questions.add(new Question("Which hero name does Katsuki Bakugo choose in 'My Hero Academia'?",
                "Explodo Hero", "Great Explosion Murder God Dynamight", "Boom King", "Blast Master", "Great Explosion Murder God Dynamight"));

        // Questions for Death Note
        questions.add(new Question("What item gives Light Yagami his power in 'Death Note'?",
                "Sword", "Notebook", "Ring", "Mask", "Notebook"));
        questions.add(new Question("What is the alias Light Yagami uses in 'Death Note'?",
                "Zero", "Kira", "Shinigami", "Phantom", "Kira"));

        // Questions for Attack on Titan
        questions.add(new Question("Who is known as the 'Attack Titan' in 'Attack on Titan'?",
                "Eren Yeager", "Levi Ackerman", "Armin Arlert", "Erwin Smith", "Eren Yeager"));
        questions.add(new Question("What is the main goal of the Survey Corps in 'Attack on Titan'?",
                "To protect the walls", "To defeat the Titans", "To capture Titans", "To explore the sea", "To defeat the Titans"));

        // Questions for Fullmetal Alchemist
        questions.add(new Question("What is Edward Elric's goal in 'Fullmetal Alchemist'?",
                "Become the Hokage", "Defeat the Titans", "Find the Philosopher's Stone", "Protect the city", "Find the Philosopher's Stone"));
        questions.add(new Question("What is the law of equivalent exchange in 'Fullmetal Alchemist'?",
                "To gain something, something of equal value must be lost",
                "A philosopher's stone grants immortality",
                "Alchemy requires a transmutation circle",
                "Alchemy is forbidden",
                "To gain something, something of equal value must be lost"));

        // Questions for Inuyasha
        questions.add(new Question("What type of creature is Inuyasha?",
                "Human", "Dog Demon", "Dragon", "Spirit", "Dog Demon"));
        questions.add(new Question("What is the name of the sacred jewel in 'Inuyasha'?",
                "Dragon Stone", "Shikon Jewel", "Crystal Heart", "Sacred Pearl", "Shikon Jewel"));

        // Questions for Yu-Gi-Oh!
        questions.add(new Question("What is the name of the main character in 'Yu-Gi-Oh!' who possesses the Millennium Puzzle?",
                "Kaiba", "Joey", "Yugi", "Bakura", "Yugi"));
        questions.add(new Question("What card is Yugi's signature monster in 'Yu-Gi-Oh!'?",
                "Blue-Eyes White Dragon", "Dark Magician", "Red-Eyes Black Dragon", "Exodia", "Dark Magician"));

        // Questions for Naruto
        questions.add(new Question("Who is Naruto's best friend and rival?",
                "Sasuke Uchiha", "Gon Freecss", "Shinji Ikari", "Light Yagami", "Sasuke Uchiha"));
        questions.add(new Question("What creature is sealed inside Naruto?",
                "A dragon", "A fox", "A wolf", "A hawk", "A fox"));

        // Questions for Ranma 1/2
        questions.add(new Question("In 'Ranma 1/2', what happens to Ranma when he is splashed with cold water?",
                "He turns into a cat", "He turns into a girl", "He turns into a dog", "He turns into a dragon", "He turns into a girl"));
        questions.add(new Question("What martial arts style does Ranma practice?",
                "Karate", "Judo", "Anything-Goes Martial Arts", "Ninjutsu", "Anything-Goes Martial Arts"));

        // Questions for Jujutsu Kaisen
        questions.add(new Question("Who is the main character in 'Jujutsu Kaisen'?",
                "Yuji Itadori", "Megumi Fushiguro", "Satoru Gojo", "Nobara Kugisaki", "Yuji Itadori"));
        questions.add(new Question("What is the name of the powerful curse that Yuji consumes in 'Jujutsu Kaisen'?",
                "Sukuna's Finger", "Jogo's Eye", "Mahito's Soul", "Kenjaku's Heart", "Sukuna's Finger"));

        // Questions for Death Parade
        questions.add(new Question("What is the name of the bartender who judges souls in 'Death Parade'?",
                "Decim", "Nona", "Ginti", "Clavis", "Decim"));
        questions.add(new Question("What game do people play in the first episode of 'Death Parade'?",
                "Poker", "Bowling", "Darts", "Pool", "Darts"));

        // Randomize the questions
        Collections.shuffle(questions);
    }

    private void displayQuestion() {
        if (currentQuestionIndex < questions.size()) {
            Question currentQuestion = questions.get(currentQuestionIndex);
            questionLabel.setText(currentQuestion.question());
            option1.setText(currentQuestion.option1());
            option2.setText(currentQuestion.option2());
            option3.setText(currentQuestion.option3());
            option4.setText(currentQuestion.option4());
            optionsGroup.clearSelection();
        } else {
            // Quiz is over
            displayFinalResults();
        }
    }

    private void checkAnswer() {
        Question currentQuestion = questions.get(currentQuestionIndex);
        String selectedAnswer = getSelectedAnswer();

        if (selectedAnswer != null && selectedAnswer.equals(currentQuestion.correctAnswer())) {
            score++;
            scoreLabel.setText("Score: " + score);
        } else {
            // Add the question to the list of incorrect answers and pre-build missed questions text
            incorrectQuestions.add(currentQuestion);
            incorrectQuestionsText.append("- ").append(currentQuestion.question()).append("\n");
        }

        currentQuestionIndex++;
        displayQuestion();
    }

    private String getSelectedAnswer() {
        if (option1.isSelected()) {
            return option1.getText();
        } else if (option2.isSelected()) {
            return option2.getText();
        } else if (option3.isSelected()) {
            return option3.getText();
        } else if (option4.isSelected()) {
            return option4.getText();
        }
        return null;
    }

    private void displayFinalResults() {
        // Remove the question label to prevent overlap with the final score display
        remove(questionLabel);

        // Create a JPanel for the quiz-over message and final score
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel quizOverLabel = new JLabel("Quiz over!", SwingConstants.CENTER);
        JLabel finalScoreLabel = new JLabel(String.format("You scored %d out of %d!", score, questions.size()), SwingConstants.CENTER);

        // Add both labels to the top panel
        topPanel.add(quizOverLabel, BorderLayout.NORTH);
        topPanel.add(finalScoreLabel, BorderLayout.SOUTH);

        // Hide the options and submit button
        option1.setVisible(false);
        option2.setVisible(false);
        option3.setVisible(false);
        option4.setVisible(false);
        submitButton.setEnabled(false);

        // Add the top panel with score information to the NORTH
        add(topPanel, BorderLayout.NORTH);

        // Display pre-built missed questions text if any questions were missed
        if (!incorrectQuestions.isEmpty()) {
            JScrollPane scrollPane = createScrollPaneForText(incorrectQuestionsText.toString());
            add(scrollPane, BorderLayout.CENTER);  // Add the missed questions list in the center
        }

        // Force immediate refresh of the UI
        validate();
        repaint();
    }

    // Helper method to create a JScrollPane for a given text
    private JScrollPane createScrollPaneForText(String text) {
        JTextArea textArea = new JTextArea(text);
        textArea.setEditable(false);
        textArea.setOpaque(false);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        return scrollPane;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AnimeTriviaQuiz quiz = new AnimeTriviaQuiz();
            quiz.setVisible(true);
        });
    }
}

// Question record to store question data
record Question(String question, String option1, String option2, String option3, String option4, String correctAnswer) {
}
