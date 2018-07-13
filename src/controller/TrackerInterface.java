package controller;

import model.Point;

public interface TrackerInterface
{
    void updateLocation(Point location);
    void trackThroughRoute() throws SelectRouteException;
}
