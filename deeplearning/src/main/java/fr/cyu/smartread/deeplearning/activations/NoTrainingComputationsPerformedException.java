package fr.cyu.smartread.deeplearning.activations;

public class NoTrainingComputationsPerformedException extends Exception {
    public NoTrainingComputationsPerformedException() {
        super("You have not performed any training calculation, please use method trainingComputation before using this function");
    }
}
