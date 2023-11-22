package mypackage;

import PetriObj.*;

import java.util.ArrayList;

public class PetriObjSimulation {
    public PetriObjSimulation() {
    }

    public static void main(String[] args) throws ExceptionInvalidTimeDelay, ExceptionInvalidNetStructure {
        PetriObjModel model = getModel();
        model.setIsProtokol(false);
        double timeModeling = 10000.0;
        model.go(timeModeling);

        System.out.println("Results:");
        for (PetriP p : model.getListObj().get(0).getNet().getListP()) {
            System.out.println(p.getName() + "  " + p.getMean() + "  " + p.getMark());
        }
    }

    public static PetriObjModel getModel() throws ExceptionInvalidTimeDelay, ExceptionInvalidNetStructure {
        ArrayList<PetriSim> list = new ArrayList<>();
        list.add(new PetriSim(getNet()));
        return new PetriObjModel(list);
    }

    public static PetriNet getNet() throws ExceptionInvalidTimeDelay {
        ArrayList<PetriP> d_P = new ArrayList<>();
        ArrayList<PetriT> d_T = new ArrayList<>();
        ArrayList<ArcIn> d_In = new ArrayList<>();
        ArrayList<ArcOut> d_Out = new ArrayList<>();
        d_P.add(new PetriP("P1", 1));
        d_P.add(new PetriP("P2", 0));
        d_P.add(new PetriP("P3", 0));
        d_P.add(new PetriP("P4", 0));
        d_P.add(new PetriP("P9", 0));
        d_P.add(new PetriP("P10", 0));
        d_P.add(new PetriP("P12", 2));
        d_P.add(new PetriP("P18", 0));
        d_P.add(new PetriP("P1", 1));
        d_P.add(new PetriP("P2", 1));
        d_P.add(new PetriP("P3", 0));
        d_P.add(new PetriP("P4", 0));
        d_P.add(new PetriP("P5", 0));
        d_P.add(new PetriP("P6", 0));
        d_P.add(new PetriP("P7", 0));
        d_T.add(new PetriT("T1", 1.25));
        d_T.get(0).setDistribution("exp", d_T.get(0).getTimeServ());
        d_T.get(0).setParamDeviation(0.0);
        d_T.add(new PetriT("T2", 0.0));
        d_T.get(1).setPriority(1);
        d_T.add(new PetriT("T3", 0.0));
        d_T.get(2).setPriority(1);
        d_T.add(new PetriT("T4", 1.0));
        d_T.add(new PetriT("T6", 1.0));
        d_T.add(new PetriT("T12", 0.0));
        d_T.get(5).setPriority(1);
        d_T.add(new PetriT("T13", 0.0));
        d_T.get(6).setPriority(1);
        d_T.add(new PetriT("T16", 1.0));
        d_T.add(new PetriT("T22", 1.0));
        d_T.add(new PetriT("T1", 0.0));
        d_T.add(new PetriT("T2", 1.0));
        d_T.add(new PetriT("T4", 1.0));
        d_T.add(new PetriT("T5", 0.0));
        d_T.add(new PetriT("T6", 0.0));
        d_T.add(new PetriT("T7", 0.0));
        d_T.add(new PetriT("T1", 1.0));
        d_T.add(new PetriT("T2", 1.0));
        d_In.add(new ArcIn(d_P.get(4), d_T.get(5), 1));
        d_In.add(new ArcIn(d_P.get(6), d_T.get(5), 1));
        d_In.add(new ArcIn(d_P.get(10), d_T.get(5), 1));
        d_In.add(new ArcIn(d_P.get(1), d_T.get(12), 1));
        d_In.add(new ArcIn(d_P.get(9), d_T.get(12), 1));
        d_In.add(new ArcIn(d_P.get(6), d_T.get(12), 1));
        d_In.add(new ArcIn(d_P.get(1), d_T.get(14), 1));
        d_In.add(new ArcIn(d_P.get(8), d_T.get(14), 1));
        d_In.add(new ArcIn(d_P.get(10), d_T.get(14), 1));
        d_In.add(new ArcIn(d_P.get(11), d_T.get(10), 1));
        d_In.add(new ArcIn(d_P.get(5), d_T.get(15), 1));
        d_In.add(new ArcIn(d_P.get(10), d_T.get(13), 1));
        d_In.add(new ArcIn(d_P.get(1), d_T.get(13), 1));
        d_In.add(new ArcIn(d_P.get(9), d_T.get(13), 1));
        d_In.add(new ArcIn(d_P.get(3), d_T.get(4), 1));
        d_In.add(new ArcIn(d_P.get(5), d_T.get(6), 1));
        d_In.add(new ArcIn(d_P.get(6), d_T.get(6), 1));
        d_In.add(new ArcIn(d_P.get(10), d_T.get(6), 1));
        d_In.add(new ArcIn(d_P.get(0), d_T.get(0), 1));
        d_In.add(new ArcIn(d_P.get(13), d_T.get(11), 1));
        d_In.add(new ArcIn(d_P.get(12), d_T.get(7), 1));
        d_In.add(new ArcIn(d_P.get(2), d_T.get(3), 1));
        d_In.add(new ArcIn(d_P.get(1), d_T.get(9), 1));
        d_In.add(new ArcIn(d_P.get(8), d_T.get(9), 1));
        d_In.add(new ArcIn(d_P.get(6), d_T.get(9), 1));
        d_In.add(new ArcIn(d_P.get(14), d_T.get(8), 1));
        d_In.add(new ArcIn(d_P.get(1), d_T.get(1), 1));
        d_In.add(new ArcIn(d_P.get(6), d_T.get(1), 2));
        d_In.add(new ArcIn(d_P.get(8), d_T.get(1), 1));
        d_In.add(new ArcIn(d_P.get(4), d_T.get(16), 1));
        d_In.add(new ArcIn(d_P.get(1), d_T.get(2), 1));
        d_In.add(new ArcIn(d_P.get(6), d_T.get(2), 2));
        d_In.add(new ArcIn(d_P.get(9), d_T.get(2), 1));
        d_Out.add(new ArcOut(d_T.get(5), d_P.get(6), 2));
        d_Out.add(new ArcOut(d_T.get(5), d_P.get(7), 1));
        d_Out.add(new ArcOut(d_T.get(5), d_P.get(8), 1));
        d_Out.add(new ArcOut(d_T.get(12), d_P.get(13), 1));
        d_Out.add(new ArcOut(d_T.get(14), d_P.get(11), 1));
        d_Out.add(new ArcOut(d_T.get(10), d_P.get(12), 1));
        d_Out.add(new ArcOut(d_T.get(15), d_P.get(9), 1));
        d_Out.add(new ArcOut(d_T.get(15), d_P.get(7), 1));
        d_Out.add(new ArcOut(d_T.get(15), d_P.get(6), 1));
        d_Out.add(new ArcOut(d_T.get(13), d_P.get(13), 1));
        d_Out.add(new ArcOut(d_T.get(4), d_P.get(5), 1));
        d_Out.add(new ArcOut(d_T.get(6), d_P.get(6), 2));
        d_Out.add(new ArcOut(d_T.get(6), d_P.get(7), 1));
        d_Out.add(new ArcOut(d_T.get(6), d_P.get(9), 1));
        d_Out.add(new ArcOut(d_T.get(0), d_P.get(0), 1));
        d_Out.add(new ArcOut(d_T.get(0), d_P.get(1), 1));
        d_Out.add(new ArcOut(d_T.get(11), d_P.get(14), 1));
        d_Out.add(new ArcOut(d_T.get(7), d_P.get(6), 1));
        d_Out.add(new ArcOut(d_T.get(7), d_P.get(7), 1));
        d_Out.add(new ArcOut(d_T.get(7), d_P.get(8), 1));
        d_Out.add(new ArcOut(d_T.get(3), d_P.get(4), 1));
        d_Out.add(new ArcOut(d_T.get(9), d_P.get(11), 1));
        d_Out.add(new ArcOut(d_T.get(8), d_P.get(6), 1));
        d_Out.add(new ArcOut(d_T.get(8), d_P.get(7), 1));
        d_Out.add(new ArcOut(d_T.get(8), d_P.get(9), 1));
        d_Out.add(new ArcOut(d_T.get(1), d_P.get(2), 1));
        d_Out.add(new ArcOut(d_T.get(1), d_P.get(10), 1));
        d_Out.add(new ArcOut(d_T.get(16), d_P.get(8), 1));
        d_Out.add(new ArcOut(d_T.get(16), d_P.get(6), 1));
        d_Out.add(new ArcOut(d_T.get(16), d_P.get(7), 1));
        d_Out.add(new ArcOut(d_T.get(2), d_P.get(3), 1));
        d_Out.add(new ArcOut(d_T.get(2), d_P.get(10), 1));
        PetriNet d_Net = new PetriNet("curs", d_P, d_T, d_In, d_Out);
        PetriP.initNext();
        PetriT.initNext();
        ArcIn.initNext();
        ArcOut.initNext();

        return d_Net;
    }
}
