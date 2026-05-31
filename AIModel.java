 
/**
 * Write a description of class AIModel here.
 *
 * @author (Krish)
 */

import java.io.Serializable;

public abstract class AIModel implements Serializable {

    private String modelName;
    private double price;          // NPR per 1 Lakh tokens
    private int parameterCount;    // in billions
    private int contextWindow;     // max tokens per request

    // Constructor
    public AIModel(String modelName, double price, int parameterCount, int contextWindow) {
        this.modelName = modelName;
        this.price = price;
        this.parameterCount = parameterCount;
        this.contextWindow = contextWindow;
    }

    // Getters
    public String getModelName() {
        return modelName;
    }

    public double getPrice() {
        return price;
    }

    public int getParameterCount() {
        return parameterCount;
    }

    public int getContextWindow() {
        return contextWindow;
    }

    // Core Token Validation Logic
    public boolean calculateTokenUsage(String promptText, int outputTokens) {

        int inputTokens = promptText.length() / 4;   //1 token = 4 characters
        int systemTokens = 50; 

        int totalTokens = inputTokens + outputTokens + systemTokens;

        return totalTokens <= contextWindow;
    }

    // Abstract display method
    public abstract String display();
}