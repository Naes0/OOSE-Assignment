import java.io.*;
import java.util.*;

public class Route implements RouteInterface
{
   private String name;
   private String description;
   private Point start;
   private Point end;
   private List<RouteInterface> routes;

   public Route(String inName, String inDesc, Point a, Point b)
   {
      name = inName;
      description = inDesc;
      start = a;
      end = b;
      routes = new ArrayList<RouteInterface>();
   }

   @Override
   public Segment find(String name)
   {
      for (RouteInterface route : routes)
      {
         Segment found = route.find(name);
         if(found != null)
         {
            return found;
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

   public void printRoute()
   {
      System.out.println("\nRoute: " + name);
      System.out.println("Descritption: " + description);
   }

   public void addRouteOrSeg(RouteInterface routeOrSeg)
   {
      routes.add(routeOrSeg);
   }

   @Override
   public void printOut()
   {
      this.printRoute();
      for(RouteInterface route : routes)
      {
         route.printOut();
      }
   }
}
