public class Question {
    private final String question;
    private final String[] choices;
    private final String answer;

    public Question(String question, String choice1, String choice2, String choice3, String choice4, String answer) {
        this.question = question;
        this.choices = new String[]{choice1, choice2, choice3, choice4};
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getChoices() {
        return choices;
    }

    public String getAnswer() {
        return answer;
    }
}
