package model;

import controller.GeoUtils;

import java.util.*;

public class Route implements RouteInterface
{
   private String name;
   private String description;
   private Point start;
   private Point end;
   private Double vertUp;
   private Double vertDown;
   private List<RouteInterface> routes;

   public Route(String inName, String inDesc)
   {
      name = inName;
      description = inDesc;
      start = null;
      end = null;
      routes = new ArrayList<RouteInterface>();
      vertDown = 0.0;
      vertUp = 0.0;
   }

   public Route(String inName, String inDesc, Point a, Point b)
   {
      name = inName;
      description = inDesc;
      start = a;
      end = b;
      routes = new ArrayList<RouteInterface>();
   }

   @Override
   public Segment findSeg(Point location)
   {
      Segment found = null;
      for (RouteInterface route : routes)
      {
            found = route.findSeg(location);
            if (found != null)
            {
               return found;
            }
      }
      return found;
   }

   @Override
   public RouteInterface find(String name)
   {
      for (RouteInterface route : routes)
      {
         RouteInterface found = route.find(name);
         if(found != null && this.name.equals(name))
         {
            return found;
         }
         else
         {
            throw new IllegalArgumentException("could not find route");
         }
      }
      return null;
   }

   @Override
   public int segmentCount()
   {
      int size = 0;
      for(RouteInterface route : routes)
      {
         size += route.segmentCount();
      }
      return size;
   }

   public void printRoute(GeoUtils gu)
   {
      Double horiDistance = 0.0;
      System.out.println("\nRoute: " + name);
      System.out.println("Description: " + description);
      System.out.print("Start: ");
      start.printOut();
      System.out.print("End: ");
      end.printOut();
      horiDistance = calcHoriDistance(gu);

      System.out.print("Horizontal Distance: " + horiDistance + "m");
      System.out.println("\tVertical Climb Distance: " + vertUp + "m");
      System.out.println("\tVertical Descent Distance: " + vertDown+ "m");
   }

   public Double calcHoriDistance(GeoUtils gu)
   {
      return gu.calcMetresDistance(start.getLatitude(), start.getLongitude(), end.getLatitude(), end.getLongitude());
   }

   @Override
   public Double calcVerticalDistance()
   {
      Double vertDistance = 0.0;
      for(RouteInterface route : routes)
      {
         vertDistance = route.calcVerticalDistance();
      }
      return vertDistance;
   }

   public void addVertUp(Double vertUp)
   {
      this.vertUp += vertUp;
   }

   public void addVertDown(Double vertDown)
   {
      this.vertDown = vertDown;
   }

   public void printOut()
   {
      System.out.println("\nRoute: " + name);
      System.out.println("Description: " + description);
      System.out.print("Start: ");
      start.printOut();
      System.out.print("End: ");
      end.printOut();
   }

   public void addRouteOrSeg(RouteInterface routeOrSeg)
   {
      routes.add(routeOrSeg);
   }

   public void addName(String inName)
   {
      name = inName;
   }

   public void addDesc(String inDesc)
   {
      description = inDesc;
   }

   public void addStart(Point a)
   {
      start = a;
   }

   public void addEnd(Point b)
   {
      end = b;
   }

   public Point getStart()
   {
       return start;
   }

   public Point getEnd()
   {
       return end;
   }

   public String getName()
   {
      return name;
   }

   public String getDescription()
   {
       return description;
   }

   public List<RouteInterface> getRoutes()
   {
      return routes;
   }

   public void setStart()
   {
     Point start = setStartRecursive();
     addStart(start);
   }
   public void setEnd()
   {
      addEnd(setEndRecursive());
   }

   public void setVertUp()
   {
      addVertUp(setVertUpRecursive());
   }

   public Double setVertUpRecursive()
   {
      Double vertUp = 0.0;
      for (RouteInterface routeOrSeg : routes)
      {
         if (routeOrSeg instanceof Segment)
         {
            if (((Segment) routeOrSeg).calcVerticalDistance() > 0)
            {
               vertUp = ((Segment) routeOrSeg).calcVerticalDistance();
            }
         }
         if (routeOrSeg instanceof Route)
         {
            vertUp = ((Route) routeOrSeg).setVertUpRecursive();
         }
      }
      return vertUp;
   }

   public Point setStartRecursive()
   {
      Point start = null;
      RouteInterface routeOrSeg = routes.get(0);
      if(routeOrSeg instanceof Segment)
      {
        start = ((Segment) routeOrSeg).getStart();
      }
      if(routeOrSeg instanceof Route)
      {
         start = ((Route) routeOrSeg).setStartRecursive();
      }
      return start;
   }

   public Point setEndRecursive()
   {
      Point end = null;
      RouteInterface routeOrSeg = routes.get(routes.size()-1);
      if(routeOrSeg instanceof Segment)
      {
         end = ((Segment) routeOrSeg).getEnd();
      }
      if(routeOrSeg instanceof Route)
      {
         end = ((Route) routeOrSeg).setEndRecursive();
      }
      return end;
   }

}
