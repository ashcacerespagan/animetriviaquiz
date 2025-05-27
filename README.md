# Anime Trivia Quiz

Anime Trivia Quiz is a fun, beginner-friendly Java GUI application that tests your knowledge across a variety of popular anime series. It was built using **Swing** and features multiple quiz modes, randomized questions, real-time feedback, and score tracking — all in a clean and interactive interface.

## 🎮 Features

* 🔀 Two Quiz Modes

  * Random Mix: 20 questions pulled from all available anime files
  * Single Show Mode: 7 questions from a user-selected anime series

* 🧐 Multiple Series Supported

  * My Hero Academia
  * Death Note
  * Attack on Titan
  * Fullmetal Alchemist
  * Inuyasha
  * Yu-Gi-Oh!
  * Naruto
  * Ranma 1/2
  * Jujutsu Kaisen
  * Death Parade
  * Solo Leveling
  * Chainsaw Man
  * Dragon Ball Z
  * Frieren
  * Dan Da Dan

* 📂 Scalable Design

  * Each anime has its own `.txt` file in the `questions/` folder
  * Easy to add more series or questions without modifying source code

* 🎯 Answer Tracking

  * Tracks score and displays a breakdown of incorrect answers at the end of the quiz

## 📁 Folder Structure

```
AnimeTriviaQuiz/
├── questions/
│   ├── my_hero_academia.txt
│   ├── death_note.txt
│   └── ...etc
├── src/
│   └── main/
│       └── java/
│           ├── AnimeTriviaQuiz.java
│           ├── Question.java
│           └── QuestionLoader.java
```

## 🚀 How to Run

1. Ensure Java 11 or higher is installed (Java 21+ recommended)
2. Clone or download this project
3. Open it in IntelliJ IDEA or any Java IDE
4. Compile and run `AnimeTriviaQuiz.java`

Or from terminal:

```bash
javac src/main/java/*.java
java -cp src/main/java AnimeTriviaQuiz
```

> ⚠️ Make sure the `questions/` folder is in the project root. The app will not function without it.

## 👤 Author Notes

This project started as a way to combine anime with programming in a meaningful way. It helped reinforce file handling, GUI design, and object-oriented design while keeping things fun and expandable.

## 📌 Future Ideas

* Add audio or visual feedback on correct/incorrect answers
* Implement a leaderboard or scoring history
* Introduce difficulty levels or a timed mode
* Add support for media-rich questions (images or sounds)

## 📄 License

This project is open for educational and personal use. Feel free to build on it!
