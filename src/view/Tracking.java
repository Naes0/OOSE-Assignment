package view;

import controller.*;
import model.Route;
import model.Tracker;

import java.util.Scanner;

public class Tracking implements MenuInterface
{
    @Override
    public void menuDisplay(MenuController menu, Tracker tracker, RouteController rc)
    {
        int input = 0;
        while (input != 2)
        {
            System.out.println("# TrackingSubMenu #");
            System.out.println("1. Select Route");
            System.out.println("2. Exit");
            System.out.println("Input a number to select an option: ");

            Scanner sc = new Scanner(System.in);
            input = sc.nextInt();

            switch (input)
            {
                case 1:
                    try
                    {
                        selectRoute(rc, tracker);
                    } catch (SelectRouteException e)
                    {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    System.out.println("Please enter one of the following options.");
            }
        }
    }

    public void selectRoute(RouteController rc, Tracker tracker) throws SelectRouteException
    {
        int input = 0;
        Route selectRoute = null;

        rc.printRouteTable();

        System.out.println("Select a route using the corresponding integer: ");

        Scanner sc = new Scanner(System.in);
        input = sc.nextInt();

        selectRoute = rc.find(input);
        if( selectRoute != null)
        {
            tracker.setSelectedRoute(selectRoute);
            GPSHandler gps = new GPSHandler(tracker);
            try
            {
                //this line would need to be changed as it starts the simulation.
                gps.simulateTracking();
            }
            catch (InterruptedException e)
            {
                throw new SelectRouteException("Error in tracking");
            }
        }
        else
        {
            throw new SelectRouteException("Could not locate Selected Route");
        }
    }
}
