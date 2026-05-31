
/**
 * Write a description of class ProPlan here.
 *
 * @author (Krish)
 */

public class ProPlan extends AIModel {

    private int availableSlots;

    public ProPlan(String modelName, double price, int parameterCount, int contextWindow, int availableSlots) {
        super(modelName, price, parameterCount, contextWindow);
        this.availableSlots = availableSlots;
    }

    public int getAvailableSlots() {
        return availableSlots;
    }

    public String addTeamMember(String memberName) {

        if (availableSlots <= 0) {
            return "No available slots!";
        }

        availableSlots--;
        return memberName + " added successfully. Remaining slots: " + availableSlots;
    }

    public String removeTeamMember(String memberName) {

        availableSlots++;
        return memberName + " removed successfully. Remaining slots: " + availableSlots;
    }

    // Single Validation (Only Context Check)
    public String usePrompt(String promptText, int outputTokens) {

        if (!calculateTokenUsage(promptText, outputTokens)) {
            return "Context limit exceeded!";
        }

        return "Prompt processed successfully (Unlimited plan).";
    }

    @Override
    public String display() {
        return "Model Name: " + getModelName() +
               "\nPrice (NPR per 1 Lakh tokens): " + getPrice() +
               "\nParameter Count (Billion): " + getParameterCount() +
               "\nContext Window: " + getContextWindow() +
               "\nAvailable Team Slots: " + availableSlots;
    }
}