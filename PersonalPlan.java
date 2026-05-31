
/**
 * Write a description of class PersonalPlan here.
 *
 * @author (Krish)
 */


public class PersonalPlan extends AIModel {

    private int promptsRemaining;

    public PersonalPlan(String modelName, double price, int parameterCount, int contextWindow, int promptsRemaining) {
        super(modelName, price, parameterCount, contextWindow);
        this.promptsRemaining = promptsRemaining;
    }

    public int getPromptsRemaining() {
        return promptsRemaining;
    }

    // Purchase additional prompts
    public String purchasePrompts(int amount) {

        if (amount <= 0) {
            return "Error: Enter a positive number of prompts.";
        }

        promptsRemaining += amount;
        return "Prompts added successfully. New quota: " + promptsRemaining;
    }

    // Use prompt (Dual Validation)
    public String usePrompt(String promptText, int outputTokens) {

        if (promptsRemaining <= 0) {
            return "Monthly quota exhausted!";
        }

        if (!calculateTokenUsage(promptText, outputTokens)) {
            return "Context limit exceeded!";
        }

        promptsRemaining--;
        return "Prompt processed successfully. Remaining quota: " + promptsRemaining;
    }

    // Override display
    @Override
    public String display() {
        return "Model Name: " + getModelName() +
               "\nPrice (NPR per 1 Lakh tokens): " + getPrice() +
               "\nParameter Count (Billion): " + getParameterCount() +
               "\nContext Window: " + getContextWindow() +
               "\nMonthly Prompts Remaining: " + promptsRemaining;
    }
}