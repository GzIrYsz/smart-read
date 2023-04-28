package fr.cyu.smartread.deeplearning.activations;

public class NoTrainingComputationsPerformedException extends Exception {
    private static final long serialVersionUID = 4064032066764093258L;

    public NoTrainingComputationsPerformedException() {
        super("You have not performed any training calculation, please use method trainingComputation before using this function");
    }
}
