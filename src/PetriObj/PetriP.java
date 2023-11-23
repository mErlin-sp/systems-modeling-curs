package PetriObj;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * This class for creating the place of Petri net.
 *
 * @author Inna V. Stetsenko
 */
public class PetriP extends PetriMainElement implements Cloneable, Serializable {

    private List<PetriM> marks;
    private String name;
    private int number;
    private double mean;
    private static int next = 0;//додано 1.10.2012, лічильник об"єктів
    private int observedMax;
    private int observedMin;
    // whether mark is a parameter; added by Katya 08.12.2016
    private boolean markIsParam = false;
    // param name
    private String markParamName = null;

    private String id; // for json unique number


    /**
     * @param n name of place
     * @param m quantity of markers
     */
    public PetriP(String n, int m) {
        name = n;
        marks = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            marks.add(new PetriM());
        }
        mean = 0;
        number = next; //додано 1.10.2012
        next++;
        observedMax = m;
        observedMin = m;
        id = null;
    }

    public PetriP(String n, List<PetriM> m) {
        name = n;
        marks = m;
        mean = 0;
        number = next; //додано 1.10.2012
        next++;
        observedMax = m.size();
        observedMin = m.size();
        id = null;
    }

    /**
     * @param n - the name of place
     */
    public PetriP(String n) { //changed by Inna 21.03.2018
        this(n, 0);

    }

    /**
     * @param id unique number for saving in server
     * @param n  name of place
     * @param m  quantity of markers
     */
    public PetriP(String id, String n, int m) { //added by Inna 21.03.2018
        this(n, m);
        this.id = id;
    }

    /**
     * @param id unique number for saving in server
     * @param n  - the name of place
     */
    public PetriP(String id, String n) { //added by Inna 21.03.2018
        this(id, n, 0);

    }

    public PetriP(PetriP position) {
        this(position.getName(), position.getMarks());
        number = next;
        next++;
    }

    public boolean markIsParam() {
        return markIsParam;
    }

    public String getMarkParamName() {
        return markParamName;
    }

    public void setMarkParam(String paramName) {
        if (paramName == null) {
            markIsParam = false;
            markParamName = null;
        } else {
            markIsParam = true;
            markParamName = paramName;
            marks = new ArrayList<>();
        }
    }

    /**
     * Set the counter of places to zero.
     */
    public static void initNext() { //ініціалізація лічильника нульовим значенням

        next = 0;
    }

    /**
     * /**
     * Recalculates the mean value
     *
     * @param a value for recalculate of mean value (value equals product of
     *          marking and time divided by time modeling)
     */
    public void changeMean(double a) {
        mean = mean + (marks.size() - mean) * a;
    }

    /**
     * @return mean value of quantity of markers
     */
    public double getMean() {
        return mean;
    }

    /**
     * @param marks value on which increase the quantity of markers
     */
    public void addMarks(List<PetriM> marks) {
//        System.out.println("Add Marks to place " + this.getName() + ": ");
//        System.out.println(marks);
        this.marks.addAll(marks);
        if (observedMax < this.marks.size()) {
            observedMax = this.marks.size();
        }
        if (observedMin > this.marks.size()) {
            observedMin = this.marks.size();
        }

    }

    /**
     * @param marks value on which decrease the quantity of markers
     */
    public void removeMarks(int marks) {
        for (int i = 0; i < marks; i++) {
            this.marks.removeFirst();
        }
        if (observedMax < this.marks.size()) {
            observedMax = this.marks.size();
        }
        if (observedMin > this.marks.size()) {
            observedMin = this.marks.size();
        }
    }

    /**
     * @return current quantity of markers
     */
    public List<PetriM> getMarks() {
        return marks;
    }

    /**
     * Set quantity of markers
     *
     * @param marks quantity of markers
     */
    public void setMarks(List<PetriM> marks) {
        this.marks = marks;
        if (observedMax < this.marks.size()) {
            observedMax = this.marks.size();
        }
        if (observedMin > this.marks.size()) {
            observedMin = this.marks.size();
        }
    }


    public int getObservedMax() {
        return observedMax;
    }

    public int getObservedMin() {
        return observedMin;
    }

    /**
     * @return name of the place
     */
    public String getName() {
        return name;
    }

    /**
     * @param s - the new name of place
     */
    public void setName(String s) {
        name = s;
    }

    /**
     * @return number of the place
     */
    public int getNumber() {
        return number;
    }

    /**
     * @param n - the new number of place
     */
    public void setNumber(int n) {
        number = n;
    }


    /**
     * @return PetriP object with parameters which copy current parameters of
     * this place
     * @throws java.lang.CloneNotSupportedException if Petri net has invalid structure
     */
    @Override
    public PetriP clone() throws CloneNotSupportedException {
        super.clone();
        PetriP P = new PetriP(name, this.getMarks()); // 14.11.2012
        P.setNumber(number); //номер зберігається для відтворення зв"язків між копіями позицій та переходів
        return P;
    }

    public void printParameters() {
        System.out.println("Place " + name + "has such parametrs: \n"
                + " number " + number + ", mark " + marks);
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

}
