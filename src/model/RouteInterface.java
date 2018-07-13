package model;

public interface RouteInterface
{
   RouteInterface find(String name);
   Segment findSeg(Point location);
   int segmentCount();
   Double calcVerticalDistance();
}
