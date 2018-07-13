package view;

import controller.SelectRouteException;
import model.RouteInterface;
import model.Segment;
import model.Tracker;
import controller.TrackerInterface;
import model.Point;

import java.util.List;
import java.util.Scanner;


public class TrackerObsever implements TrackerInterface
{
    private Tracker tracker;

    public TrackerObsever(Tracker tracker)
    {
        this.tracker = tracker;
    }

    public void setup()
    {
        tracker.addObserver(this);
    }

    @Override
    public void updateLocation(Point location)
    {
        Point tlocation = tracker.getLocation();
        System.out.println("Current Location: ");
        tlocation.printOut();
    }

    @Override
    public void trackThroughRoute() throws SelectRouteException
    {
        System.out.print("Remaining Horizontal: " + tracker.getRemainHori() + "Remaining Vertical: " + tracker.getRemainVert() + "\n");
        midTrackingMenu();
    }

    public void midTrackingMenu()
    {
        int input = 0;
        while (input != 3)
        {
            System.out.println("# Tracking Menu #");
            System.out.println("1. Move To Next WayPoint");
            System.out.println("2. Manual Enter");
            System.out.println("3. Exit");
            System.out.println("Input a number to select an option: ");

            Scanner sc = new Scanner(System.in);
            input = sc.nextInt();

            switch (input)
            {
                case 1:
                    setNextWayPoint();
                    break;
                case 2:
                    manualEnter();
                    input = 3;
                    break;
                default:
                    System.out.println("Please enter one of the following options.");
            }
        }
    }

    public void manualEnter()
    {
        System.out.println("Please Enter Coordinates for Latitude, longitude and altitude respectively: ");
        Double lat = 0.0;
        Double longit = 0.0;
        Double alt = 0.0;

        Scanner sc = new Scanner(System.in);
        lat = sc.nextDouble();
        longit = sc.nextDouble();
        alt = sc.nextDouble();

        Point newLocation = new Point(lat, longit, alt);
        tracker.notifyObservers(newLocation);
    }

    public void setNextWayPoint()
    {
        tracker.notifyObservers(tracker.getCurrSeg().getEnd());
    }
}
