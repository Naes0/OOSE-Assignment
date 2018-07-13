package model;

import controller.GeoUtils;
import controller.SelectRouteException;
import controller.TrackerInterface;

import java.util.ArrayList;
import java.util.List;

public class Tracker
{
    private Point location;
    private Route selectedRoute;
    private Double remainVert;
    private Double remainHori;
    private List<Segment> visted;
    private GeoUtils gu;
    private Segment currSeg;
    private List<TrackerInterface> observers;

    public Tracker(GeoUtils inGu)
    {
        location = new Point(0.0,0.0,0.0);
        observers = new ArrayList<TrackerInterface>();
        selectedRoute = null;
        currSeg = null;
        remainHori = 0.0;
        remainVert = 0.0;
        gu = inGu;
        visted = new ArrayList<Segment>();
    }

    public void addObserver(TrackerInterface obs)
    {
        observers.add(obs);
    }

    public void removeObserver(TrackerInterface obs)
    {
        observers.remove(obs);
    }

    public void setSelectedRoute(Route selectedRoute)
    {
        this.selectedRoute = selectedRoute;
        location.setLat(selectedRoute.getStart().getLatitude());
        location.setLongitude(selectedRoute.getStart().getLongitude());
        location.setAltitude(selectedRoute.getStart().getAltitude());
    }

    public void notifyObservers(Point inLocation)
    {
        for (TrackerInterface obs : observers)
        {
            obs.updateLocation(inLocation);
            try
            {
                obs.trackThroughRoute();
            } catch (SelectRouteException e)
            {
                System.out.println(e.getMessage());
            }
        }
    }

    public Segment getCurrSeg()
    {
        return currSeg;
    }

    public void setCurrSeg(Segment currSeg)
    {
        this.currSeg = currSeg;
    }

    public Point getLocation()
    {
        return location;
    }

    public Double getRemainVert()
    {
        return remainVert;
    }

    public Double getRemainHori()
    {
        return remainHori;
    }

    public List<Segment> getVisted()
    {
        return visted;
    }

    public void setLocation(Point location)
    {
        this.location = location;
    }

    public void setRemainVert(Double remainVert)
    {
        this.remainVert = remainVert;
    }

    public void setRemainHori(Double remainHori)
    {
        this.remainHori = remainHori;
    }

    public GeoUtils getGU()
    {
        return gu;
    }

    public Route getSelectedRoute()
    {
        return selectedRoute;
    }


    public Point getEndPoint()
    {
        List<RouteInterface> route = selectedRoute.getRoutes();
        RouteInterface seg = route.get(route.size()-1);
        return ((Segment)seg).getEnd();
    }

    public boolean pEquals(Point p)
    {
        if(p.getAltitude().equals(getEndPoint().getAltitude()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
