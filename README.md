# Anime Trivia Quiz

Anime Trivia Quiz is a fun, beginner-friendly Java GUI application that tests your knowledge across a variety of popular anime series. Built with **Swing**, it features multiple quiz modes, randomized questions, and real-time scoring.

---

## 🎮 Features

* 🔀 **Two Quiz Modes**

  * **Random Mix:** 20 randomized questions across all series
  * **Single Show:** 7 questions from a specific anime
* 🌟 Tracks correct and incorrect answers
* 📂 Uses individual `.txt` files for each anime for easy expansion
* 🧠 Includes popular anime like My Hero Academia, Death Note, Solo Leveling, and more

---

## 📸 Screenshots

### 🕽️ Quiz Mode Selection

<p float="left">
  <img src="screenshots/screenshot_mode_dropdown.png" width="300"/>
  <img src="screenshots/screenshot_mode_prompt.png" width="300"/>
</p>

### ❓ Randomized Questions In-Game

<p float="left">
  <img src="screenshots/screenshot_question_1.png" width="400"/>
  <img src="screenshots/screenshot_question_2.png" width="400"/>
</p>

### 📊 Final Score Summary

<p>
  <img src="screenshots/screenshot_score_summary.png" width="500"/>
</p>

---

## 📁 Folder Structure

```
AnimeTriviaQuiz/
├── questions/
│   ├── my_hero_academia.txt
│   ├── ... (other series)
├── src/
│   └── main/java/
│       ├── AnimeTriviaQuiz.java
│       ├── Question.java
│       └── QuestionLoader.java
├── screenshots/
│   ├── screenshot_mode_dropdown.png
│   ├── screenshot_mode_prompt.png
│   ├── screenshot_question_1.png
│   ├── screenshot_question_2.png
│   └── screenshot_score_summary.png
```

---

## 🚀 How to Run

1. Ensure you have **Java 11 or newer**
2. Clone the repo and run:

```bash
javac src/main/java/*.java
java -cp src/main/java AnimeTriviaQuiz
```

✅ The app will load questions from the `/questions` folder

---

## 🛠️ To-Do

* Add time limits per question
* Save high scores locally
* Add a leaderboard screen
* Export results to file

---

## 📄 License

This project is for educational and personal use only. Enjoy, improve it, and have fun!
