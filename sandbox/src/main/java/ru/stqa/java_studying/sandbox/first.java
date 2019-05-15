package ru.stqa.java_studying.sandbox;

public class first {

    public static void main(String[] args) {

        Point x = new Point(1,2);
        Point y = new Point(1,2);
        System.out.println("Расстояние между точками (" + x.p1 + "; " + x.p2 + ") и (" + y.p1 + "; " + y.p2 + ") = " + x.distance() );
    }

    public static double distance(Point x, Point y) {

        return Math.sqrt((x.p2 - x.p1)*(x.p2 - x.p1)+(y.p2 - y.p1)*(y.p2 - y.p1));
    }
}