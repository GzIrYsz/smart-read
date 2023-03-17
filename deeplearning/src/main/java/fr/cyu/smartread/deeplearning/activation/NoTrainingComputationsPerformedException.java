package fr.cyu.smartread.deeplearning.activation;

public class NoTrainingComputationsPerformedException extends Exception {
    public NoTrainingComputationsPerformedException() {
        super("You have not performed any training calculation, please use method trainingComputation before using this function");
    }
}
