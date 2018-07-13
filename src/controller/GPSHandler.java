package controller;

import model.Point;
import model.Tracker;

import java.util.concurrent.TimeUnit;

public class GPSHandler extends GpsLocator
{
    private Tracker tracker;

    public GPSHandler(Tracker tracker)
    {
        this.tracker = tracker;
    }

    @Override
    protected void locationReceived(double latitude, double longitude, double altitude)
    {
        Point location = new Point(latitude, longitude, altitude);
        tracker.notifyObservers(location);
    }

    public void simulateTracking() throws InterruptedException
    {
        /*Double alt = 48.5;
        while(!(tracker.getLocation().equals(tracker.getEndPoint())));
        {
            TimeUnit.SECONDS.sleep(1);
            locationReceived(-31.94, 115.75, alt);
            alt += 26.2;
        }
        System.out.println("End of Route");*/

        TimeUnit.SECONDS.sleep(2);
        locationReceived(-31.94, 115.75, 48.5);
        TimeUnit.SECONDS.sleep(2);
        locationReceived(-31.94, 115.75, 49.3);
        TimeUnit.SECONDS.sleep(2);
        locationReceived(-31.94, 115.75, 50.3);
        TimeUnit.SECONDS.sleep(2);
        locationReceived(-31.94, 115.75, 54.3);
        TimeUnit.SECONDS.sleep(2);
        locationReceived(-31.94, 115.75, 60.3);
        TimeUnit.SECONDS.sleep(2);
        locationReceived(-31.94, 115.75, 70.3);
        TimeUnit.SECONDS.sleep(2);
        locationReceived(-31.94, 115.75, 80.3);
        TimeUnit.SECONDS.sleep(2);
        locationReceived(-31.94, 115.75, 90.3);
        TimeUnit.SECONDS.sleep(2);
        locationReceived(-31.94, 115.75, 120.3);
        TimeUnit.SECONDS.sleep(2);
        locationReceived(-31.94, 115.75, 130.3);
        System.out.println("End of Route");
    }
}
