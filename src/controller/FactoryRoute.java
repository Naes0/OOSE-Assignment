package controller;

import model.Point;
import model.Route;
import model.RouteInterface;
import model.Segment;

import java.lang.Double;

public class FactoryRoute
{
   /**
   * Checks two lines of String as they iterate through an array constantly checking what object should be created
   * based on the split length and first character.
   * whenevery null is returned it skips those lines and does not create anything
   * */
   public RouteInterface makeObject(String[] lineArray) throws MakeRouteException
   {
      RouteInterface routeOrSeg = null;
      Point pointA = null;
      Point pointB = null;
      String desc = "";
      String[] data;
      int i = 0;

      //route checks
      if( lineArray[0].isEmpty() || lineArray[1].isEmpty()) // neither of the elements is empty;
      {
         routeOrSeg = null; //skips
      }
      else if((lineArray[1].charAt(0) >= 'A' && lineArray[1].charAt(0) <= 'Z') || (lineArray[1].charAt(0) >= 'a' && lineArray[1].charAt(0) <= 'z') || lineArray[1].charAt(0) == '_') //skipping so you don't duplicate routes
      {
         routeOrSeg = null;
      }
      else if((lineArray[0].charAt(0) >= 'A' && lineArray[0].charAt(0) <= 'Z') || (lineArray[0].charAt(0) >= 'a' && lineArray[0].charAt(0) <= 'z') || lineArray[0].charAt(0) == '_') //if its an identifier its a route
      {
         data = lineArray[0].split(" ", 2);
         routeOrSeg = new Route(data[0], data[1]);
      }
      else  //waypoints checks
      {
         while (i < 2)
         {
            //creating segments
            data = lineArray[i].split(",", 4);
            if (data.length == 4) //waypoint with description
            {
               if (data[3].charAt(0) == '*') //waypoint with subroute
               {
                  if (i == 0)
                  {
                     routeOrSeg = new Route(data[3].substring(1), "stub");
                     i = 2;// if a waypoing with a subroute is the starting point of a segment dont create it
                  }
                  else
                  {
                     pointB = new Point(Double.parseDouble(data[0]), Double.parseDouble(data[1]), Double.parseDouble(data[2]));
                     i++;
                  }
               }
               else //waypoint
               {
                  if (i == 0)
                  {
                     pointA = new Point(Double.parseDouble(data[0]), Double.parseDouble(data[1]), Double.parseDouble(data[2]));

                     desc = data[3];
                     i++;
                  }
                  else
                  {
                     pointB = new Point(Double.parseDouble(data[0]), Double.parseDouble(data[1]), Double.parseDouble(data[2]));
                     i++;
                  }
               }
            }
            else if (data.length == 3) //last waypoint (no description)
            {
               pointB = new Point(Double.parseDouble(data[0]), Double.parseDouble(data[1]), Double.parseDouble(data[2]));
               i = 2;
            }
            else //its not any of the createable objects therefor input data must be wrong
            {
               throw new MakeRouteException("error creating Route or Segment");
            }
         }
         if (pointA != null && pointB != null) //if point A and B is null then it does not meet any of the conditions to produce a segment and therefore should skip it
         {
            routeOrSeg = new Segment(pointA, pointB, desc);
         }
      }
      return routeOrSeg;
   }
}
