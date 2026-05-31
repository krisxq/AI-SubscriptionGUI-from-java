# 🤖 AI Subscription System

A Java desktop application for managing AI model subscriptions — supporting **Personal** and **Pro** plans — with a Swing-based GUI, token validation, team management, and persistent data storage.

---

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Project Structure](#project-structure)
- [Class Hierarchy](#class-hierarchy)
- [Getting Started](#getting-started)
- [How to Run](#how-to-run)
- [Usage Guide](#usage-guide)
- [Data Persistence](#data-persistence)
- [Author](#author)

---

## Overview

The **AI Subscription System** simulates a subscription management platform for AI language models. Users can register AI models under two plan types, submit prompts with token-limit validation, manage team members, and save/load all data between sessions.

Pricing is expressed in **NPR per 1 Lakh tokens**, making this suitable for a Nepali market context.

---

## Features

- **Two subscription plans** — Personal (quota-based) and Pro (unlimited, team-enabled)
- **Token usage validation** — Estimates token count from prompt length and checks against the model's context window
- **Team management** — Add or remove members from a Pro Plan with slot tracking
- **GUI interface** — Built with Java Swing; no external libraries required
- **Data persistence** — Export and load model data via Java Object Serialization (`data.dat`)
- **Plan type detection** — Identify whether a saved model is Personal or Pro at runtime

---

## Project Structure

```
AI-Subscription-System/
│
├── AIModel.java          # Abstract base class for all AI models
├── PersonalPlan.java     # Extends AIModel — quota-based prompt plan
├── ProPlan.java          # Extends AIModel — unlimited plan with team slots
├── SubscriptionGUI.java  # Main GUI class (entry point)
├── ai.png                # Application image asset
└── data.dat              # Auto-generated on export (serialized model data)
```

---

## Class Hierarchy

```
AIModel (abstract, Serializable)
├── PersonalPlan
└── ProPlan
```

### `AIModel` (Abstract)
| Field            | Type     | Description                        |
|------------------|----------|------------------------------------|
| `modelName`      | String   | Name of the AI model               |
| `price`          | double   | Cost in NPR per 1 Lakh tokens      |
| `parameterCount` | int      | Model size in billions             |
| `contextWindow`  | int      | Maximum tokens per request         |

Key method: `calculateTokenUsage(String promptText, int outputTokens)` — estimates input tokens as `promptText.length() / 4`, adds 50 system tokens, and validates against the context window.

### `PersonalPlan` extends `AIModel`
| Field              | Description                          |
|--------------------|--------------------------------------|
| `promptsRemaining` | Monthly prompt quota                 |

- `usePrompt(prompt, outputTokens)` — validates quota **and** token limit before processing
- `purchasePrompts(amount)` — adds prompts to the remaining quota

### `ProPlan` extends `AIModel`
| Field            | Description                        |
|------------------|------------------------------------|
| `availableSlots` | Number of team member slots        |

- `usePrompt(prompt, outputTokens)` — validates token limit only (unlimited prompts)
- `addTeamMember(name)` / `removeTeamMember(name)` — slot management

---

## Getting Started

### Prerequisites

- **Java JDK 8 or higher** — [Download here](https://www.oracle.com/java/technologies/downloads/)
- Any IDE (BlueJ, IntelliJ IDEA, Eclipse, VS Code) or a terminal

### Clone the Repository

```bash
git clone https://github.com/your-username/ai-subscription-system.git
cd ai-subscription-system
```

---

## How to Run

### Option 1 — Terminal

```bash
# Compile all files
javac AIModel.java PersonalPlan.java ProPlan.java SubscriptionGUI.java

# Run the application
java SubscriptionGUI
```

### Option 2 — BlueJ

1. Open BlueJ and select **Project > Open Project**
2. Navigate to the project folder
3. Right-click `SubscriptionGUI` → **void main(String[] args)** → OK

### Option 3 — IntelliJ / Eclipse

1. Create a new Java project and add all `.java` files to the `src` folder
2. Set `SubscriptionGUI` as the main class
3. Click **Run**

> **Note:** Place `ai.png` in the same directory as the compiled `.class` files for the image to load correctly.

---

## Usage Guide

### Adding a Personal Plan
1. Fill in **Model Name**, **Price**, **Parameters**, **Context Window**
2. Enter a **Prompt Quota** (e.g. `100`)
3. Click **Add Personal Plan**

### Adding a Pro Plan
1. Fill in **Model Name**, **Price**, **Parameters**, **Context Window**
2. Enter **Team Slots** (e.g. `5`)
3. Click **Add Pro Plan**

### Submitting a Prompt (Personal Plan)
1. Enter the model's list **Index**
2. Type your **Prompt** text and expected output **Length** (tokens)
3. Click **Give Prompt**

### Team Management (Pro Plan)
1. Enter the model's **Index** and a **Member Name**
2. Click **Add Member** or **Remove Member**

### Other Actions
| Button         | Description                                  |
|----------------|----------------------------------------------|
| Display All    | Lists all models with their details          |
| Check Plan Type | Shows whether the selected model is Personal or Pro |
| Clear          | Resets all input fields                      |
| Export         | Saves model list to `data.dat`               |
| Load           | Loads model list from `data.dat`             |

---

## Data Persistence

The application uses **Java Object Serialization** to save and restore data.

- **Export** writes `modelList` to `data.dat` in the working directory
- **Load** reads it back and refreshes the display
- `AIModel` implements `Serializable`, so both subclasses are automatically serializable

> The `data.dat` file is created automatically on first export. Keep it in the same folder as the compiled classes.

---

## Author

**Krish**  
Built as a Java OOP project demonstrating abstract classes, inheritance, polymorphism, Swing GUI, and file I/O.
