package controller;

import model.*;

import java.util.List;
import java.util.Scanner;

public class TrackerController implements TrackerInterface
{
    private Tracker tracker;

    public TrackerController(Tracker tracker)
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
        tracker.setLocation(location);
    }

    @Override
    public void trackThroughRoute() throws  SelectRouteException
    {
        Double remainHori = tracker.getRemainHori();
        Double remainVert = tracker.getRemainVert();
        Point location = tracker.getLocation();
        GeoUtils gu = tracker.getGU();
        Segment seg = findSegment(); // returns seg with the user is
        if (seg != null)
        {
            remainVert = seg.getEnd().getAltitude() - location.getAltitude();
            remainHori = gu.calcMetresDistance(location.getLatitude(), location.getLongitude(), seg.getEnd().getLatitude(), seg.getEnd().getLongitude());
            remainVert = Math.abs(remainVert);
            if(remainVert <= 2.0 && remainHori <= 10.0) //hit waypoint
            {
                location = seg.getEnd();
                System.out.println("\n Hitting Waypoint");
                remainHori = 0.0;
                remainVert = 0.0;
            }
            tracker.setCurrSeg(seg);
            tracker.setLocation(location);
            tracker.setRemainVert(remainVert);
            tracker.setRemainHori(remainHori);
            seg.printOut();
        }
        else
        {
            throw new SelectRouteException("User not within segment");
        }
    }

    public Segment findSegment()
    {
        Segment seg = null;
        Route selectedRoute = tracker.getSelectedRoute();
        Point location = tracker.getLocation();
        Double remainVert = tracker.getRemainVert();
        List<Segment> visted = tracker.getVisted();
        List<RouteInterface> path = selectedRoute.getRoutes();
        for (RouteInterface route : path)
        {
            seg = route.findSeg(location);
            if (seg != null)
            {
                remainVert = Math.abs(seg.getEnd().getAltitude() - location.getAltitude());
            }
            if(seg != null && (!visted.contains(seg)) && remainVert <= 2.0)
            {
                visted.add(seg);
                return seg;
            }
            else if (seg != null && (!visted.contains(seg)))
            {
                return seg;
            }
        }
        return seg;
    }



}
