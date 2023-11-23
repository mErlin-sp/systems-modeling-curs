package PetriObj;

public class PetriM {
    private double creationTime;
    private double dispositionTime;

    public PetriM() {
        creationTime = Integer.MAX_VALUE;
        dispositionTime = Integer.MAX_VALUE;
    }

    public PetriM(double creationTime, double dispositionTime) {
        this.creationTime = creationTime;
        this.dispositionTime = dispositionTime;
    }

    public double getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(double creationTime) {
        this.creationTime = creationTime;
    }

    public double getDispositionTime() {
        return dispositionTime;
    }

    public void setDispositionTime(double dispositionTime) {
        this.dispositionTime = dispositionTime;
    }

    public double getServiceTime() {
        return dispositionTime - creationTime;
    }

    @Override
    public String toString() {
        return String.format("Mark. Creation time: %f. Disposition time: %f.", this.creationTime, this.dispositionTime);
    }
}
