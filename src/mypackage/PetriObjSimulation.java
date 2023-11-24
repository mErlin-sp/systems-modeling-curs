package mypackage;

import PetriObj.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PetriObjSimulation {

    private final static double avgShipArrival = 1.25;
    private final static double avgMaintenanceTime = 1;
    private final static int numOfCranes = 2;
    private final static double timeModeling = 10000.0;

    public static void main(String[] args) throws ExceptionInvalidTimeDelay {
        System.out.println("Modeling of Systems. Coursework. Oleksandr Popov. IT-z01");
        System.out.println();
        System.out.println("Input values:");
        System.out.println("Average Ship Arrival Interval: " + avgShipArrival);
        System.out.println("Average Ship Maintenance Time: " + avgMaintenanceTime);
        System.out.println("Num of Cranes: " + numOfCranes);
        System.out.println("Modeling time: " + timeModeling);
        System.out.println("------------------------------------------------");
        System.out.println();

        PetriObjModel model = getModel();
        model.setIsProtokol(false);
        model.go(timeModeling);

        System.out.println("Output values:");
        for (PetriP p : model.getListObj().get(0).getNet().getListP()) {
            if (Objects.equals(p.getName(), "End")) {
                List<PetriM> marks = p.getMarks();
                double min = marks.stream().mapToDouble(PetriM::getServiceTime).min().orElse(0.0);
                double max = marks.stream().mapToDouble(PetriM::getServiceTime).max().orElse(0.0);
                double avg = marks.stream().mapToDouble(PetriM::getServiceTime).average().orElse(0.0);
                System.out.printf("Ships maintained: %d. Min maintenance time: %f. Max maintenance time: %f. Mean maintenance time: %f.\n", p.getMarks().size(), min, max, avg);
            } else if (Objects.equals(p.getName(), "1-5")) {
                System.out.printf("Anchorage 1. Mean load: %f\n", (1 - p.getMean()));
            } else if (Objects.equals(p.getName(), "2-5")) {
                System.out.printf("Anchorage 2. Mean load: %f\n", (1 - p.getMean()));
            } else if (Objects.equals(p.getName(), "Cranes")) {
                System.out.printf("Cranes 1 & 2. Mean load: %f\n", (1 - (p.getMean() / 2)));
            }
        }
    }

    public static PetriObjModel getModel() throws ExceptionInvalidTimeDelay {
        ArrayList<PetriSim> list = new ArrayList<>();
        list.add(new PetriSim(getNet(avgShipArrival, avgMaintenanceTime, numOfCranes)));
        return new PetriObjModel(list);
    }

    public static PetriNet getNet(double avgShipArrival, double avgMaintenanceTime, int numOfCranes) throws ExceptionInvalidTimeDelay {
        ArrayList<PetriP> d_P = new ArrayList<>();
        ArrayList<PetriT> d_T = new ArrayList<>();
        ArrayList<ArcIn> d_In = new ArrayList<>();
        ArrayList<ArcOut> d_Out = new ArrayList<>();
        d_P.add(new PetriP("P1", 1));
        d_P.add(new PetriP("Queue", 0));
        d_P.getLast().setMarkParam("create");
        d_P.add(new PetriP("1-1", 0));
        d_P.getLast().setMarkParam("path");
        d_P.add(new PetriP("2-1", 0));
        d_P.getLast().setMarkParam("path");
        d_P.add(new PetriP("1-2", 0));
        d_P.getLast().setMarkParam("path");
        d_P.add(new PetriP("2-2", 0));
        d_P.getLast().setMarkParam("path");
        d_P.add(new PetriP("Cranes", numOfCranes));
        d_P.add(new PetriP("End", 0));
        d_P.getLast().setMarkParam("dispose");
        d_P.add(new PetriP("1-5", 1));
        d_P.add(new PetriP("2-5", 1));
        d_P.add(new PetriP("3", 0));
        d_P.add(new PetriP("1-3", 0));
        d_P.getLast().setMarkParam("path");
        d_P.add(new PetriP("1-4", 0));
        d_P.getLast().setMarkParam("path");
        d_P.add(new PetriP("2-3", 0));
        d_P.getLast().setMarkParam("path");
        d_P.add(new PetriP("2-4", 0));
        d_P.getLast().setMarkParam("path");
        d_T.add(new PetriT("T1", avgShipArrival));
        d_T.get(0).setDistribution("exp", d_T.get(0).getTimeServ());
        d_T.get(0).setParamDeviation(0.0);
        d_T.add(new PetriT("T2", 0.0));
        d_T.get(1).setPriority(1);
        d_T.add(new PetriT("T3", 0.0));
        d_T.get(2).setPriority(1);
        d_T.add(new PetriT("T4", avgMaintenanceTime));
        d_T.getLast().setDistribution("unif", d_T.getLast().getTimeServ());
        d_T.getLast().setParamDeviation(0.5);
        d_T.add(new PetriT("T6", avgMaintenanceTime));
        d_T.getLast().setDistribution("unif", d_T.getLast().getTimeServ());
        d_T.getLast().setParamDeviation(0.5);
        d_T.add(new PetriT("T12", 0.0));
        d_T.get(5).setPriority(1);
        d_T.add(new PetriT("T13", 0.0));
        d_T.get(6).setPriority(1);
        d_T.add(new PetriT("T16", avgMaintenanceTime));
        d_T.getLast().setDistribution("unif", d_T.getLast().getTimeServ());
        d_T.getLast().setParamDeviation(0.5);
        d_T.add(new PetriT("T22", avgMaintenanceTime));
        d_T.getLast().setDistribution("unif", d_T.getLast().getTimeServ());
        d_T.getLast().setParamDeviation(0.5);
        d_T.add(new PetriT("T1", 0.0));
        d_T.add(new PetriT("T2", avgMaintenanceTime));
        d_T.getLast().setDistribution("unif", d_T.getLast().getTimeServ());
        d_T.getLast().setParamDeviation(0.5);
        d_T.add(new PetriT("T4", avgMaintenanceTime));
        d_T.getLast().setDistribution("unif", d_T.getLast().getTimeServ());
        d_T.getLast().setParamDeviation(0.5);
        d_T.add(new PetriT("T5", 0.0));
        d_T.add(new PetriT("T6", 0.0));
        d_T.add(new PetriT("T7", 0.0));
        d_T.add(new PetriT("T1", avgMaintenanceTime));
        d_T.getLast().setDistribution("unif", d_T.getLast().getTimeServ());
        d_T.getLast().setParamDeviation(0.5);
        d_T.add(new PetriT("T2", avgMaintenanceTime));
        d_T.getLast().setDistribution("unif", d_T.getLast().getTimeServ());
        d_T.getLast().setParamDeviation(0.5);
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
