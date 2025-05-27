import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class AnimeTriviaQuiz extends JFrame {
    private List<Question> questions;
    private int currentQuestionIndex;
    private int score;
    private final JLabel questionLabel;
    private final JRadioButton[] options;
    private final JLabel scoreLabel;
    private final JLabel quizModeLabel;
    private List<Question> incorrectQuestions;

    public AnimeTriviaQuiz() {
        setTitle("Anime Trivia Quiz");
        setSize(600, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // GUI components
        questionLabel = new JLabel("Question");
        add(questionLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(4, 1));
        options = new JRadioButton[4];
        ButtonGroup optionGroup = new ButtonGroup();
        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            optionGroup.add(options[i]);
            centerPanel.add(options[i]);
        }
        add(centerPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new GridLayout(2, 1));
        JButton nextButton = new JButton("Next");
        scoreLabel = new JLabel("Score: 0");
        quizModeLabel = new JLabel("Quiz Mode: ");
        bottomPanel.add(scoreLabel);
        bottomPanel.add(quizModeLabel);
        add(bottomPanel, BorderLayout.SOUTH);
        add(nextButton, BorderLayout.EAST);

        nextButton.addActionListener(e -> checkAnswer());

        // Load questions
        try {
            loadQuestions();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load questions.");
            System.exit(1);
        }

        displayQuestion();
    }

    private void loadQuestions() throws IOException {
        String[] options = {
                "Random Mix",
                "My Hero Academia", "Death Note", "Attack on Titan", "Fullmetal Alchemist",
                "Inuyasha", "Yu-Gi-Oh!", "Naruto", "Ranma 1/2", "Jujutsu Kaisen",
                "Death Parade", "Solo Leveling", "Chainsaw Man", "Dragon Ball Z", "Frieren", "Dan Da Dan"
        };

        Map<String, String> showToFile = Map.ofEntries(
    Map.entry("My Hero Academia", "my_hero_academia.txt"),
    Map.entry("Death Note", "death_note.txt"),
    Map.entry("Attack on Titan", "attack_on_titan.txt"),
    Map.entry("Fullmetal Alchemist", "fullmetal_alchemist.txt"),
    Map.entry("Inuyasha", "inuyasha.txt"),
    Map.entry("Yu-Gi-Oh!", "yu_gi_oh.txt"),
    Map.entry("Naruto", "naruto.txt"),
    Map.entry("Ranma 1/2", "ranma_1_2.txt"),
    Map.entry("Jujutsu Kaisen", "jujutsu_kaisen.txt"),
    Map.entry("Death Parade", "death_parade.txt"),
    Map.entry("Solo Leveling", "solo_leveling.txt"),
    Map.entry("Chainsaw Man", "chainsaw_man.txt"),
    Map.entry("Dragon Ball Z", "dragon_ball_z.txt"),
    Map.entry("Frieren", "frieren.txt"),
    Map.entry("Dan Da Dan", "dan_da_dan.txt")
);

        String selected = (String) JOptionPane.showInputDialog(
                null,
                "Choose a quiz mode:",
                "Anime Trivia Quiz",
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]
        );

        if (selected == null) {
            JOptionPane.showMessageDialog(null, "No selection made. Exiting.");
            System.exit(0);
        }

        incorrectQuestions = new ArrayList<>();

        if (selected.equals("Random Mix")) {
            questions = QuestionLoader.loadAll("questions");
            Collections.shuffle(questions);
            questions = questions.subList(0, Math.min(20, questions.size()));
        } else {
            questions = QuestionLoader.loadFromSeries("questions/" + showToFile.get(selected));
            Collections.shuffle(questions);
            questions = questions.subList(0, Math.min(7, questions.size()));
        }

        quizModeLabel.setText("Quiz Mode: " + selected);
        score = 0;
        currentQuestionIndex = 0;
    }

    private void displayQuestion() {
        if (currentQuestionIndex >= questions.size()) {
            endQuiz();
            return;
        }

        Question q = questions.get(currentQuestionIndex);
        questionLabel.setText("Q" + (currentQuestionIndex + 1) + ": " + q.getQuestion());

        List<String> choices = Arrays.asList(q.getChoices());
        Collections.shuffle(choices);
        for (int i = 0; i < 4; i++) {
            options[i].setText(choices.get(i));
            options[i].setSelected(false);
        }
    }

    private void checkAnswer() {
        for (int i = 0; i < 4; i++) {
            if (options[i].isSelected()) {
                String selected = options[i].getText();
                if (selected.equals(questions.get(currentQuestionIndex).getAnswer())) {
                    score++;
                    scoreLabel.setText("Score: " + score);
                } else {
                    incorrectQuestions.add(questions.get(currentQuestionIndex));
                }
                currentQuestionIndex++;
                displayQuestion();
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Please select an answer.");
    }

    private void endQuiz() {
        String message = "Quiz Over!\nScore: " + score + " / " + questions.size();
        if (!incorrectQuestions.isEmpty()) {
            message += "\n\nIncorrect Questions:\n";
            for (Question q : incorrectQuestions) {
                message += "- " + q.getQuestion() + "\nCorrect Answer: " + q.getAnswer() + "\n\n";
            }
        }
        JOptionPane.showMessageDialog(this, message);
        System.exit(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AnimeTriviaQuiz().setVisible(true));
    }
}
