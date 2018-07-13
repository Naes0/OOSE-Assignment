package controller;


import java.io.IOException;
import java.lang.Math;

public class GeoUtils // Note: same class as above
{
/**
* Attempts to contact the central server to retrieve the latest version
* of the route data.
*
* @return A string containing formatted route information.
* @throws IOException if the server cannot be contacted, or the
* connection is interrupted.
*/

   public String retrieveRouteData() throws IOException
   {
      String data = "theClimb [theClimb description]\n" +
              "    -31.94,115.75,47.1,[theClimb1description]\n" +
              "    -31.94,115.75,55.3,[theClimb2description]\n" +
              "    -31.94,115.75,71.0,[theClimb3description]\n" +
              "    -31.93,115.75,108.0,[theClimb4description]\n" +
              "    -31.93,115.75,131.9\n" +
              "mainRoute [mainRoute description]\n" +
              "    -31.96,115.80,63.0,[mainRoute1description]\n" +
              "    -31.95,115.78,45.3,[mainRoute2description]\n" +
              "    -31.95,115.77,44.8,*theStroll\n" +
              "    -31.94,115.75,47.1,[mainRoute3description]\n" +
              "    -31.93,115.72,40.1,[mainRoute4description]\n" +
              "    -31.94,115.75,47.1,*theClimb\n" +
              "    -31.93,115.75,131.9,[mainRoute5description]\n" +
              "    -31.92,115.74,128.1\n" +
              "theStroll [theStroll description]\n" +
              "    -31.95,115.77,44.8,[stroll1description]\n" +
              "    -31.93,115.76,43.0,[stroll2description]\n" +
              "    -31.94,115.75,47.1\n";
      return data;
   }

    public Double calcMetresDistance(double lat1, double long1, double lat2, double long2)
    {
        //System.out.println("lat1: " + lat1 + "long1: " + long1 + "lat2: " + lat2 + "long2: " + long2);
        Double distance = 6371000 * Math.acos( ( (Math.sin((Math.PI * lat1) / 180)) * (Math.sin((Math.PI * lat2) / 180)) ) + ( (Math.cos((Math.PI * lat1) / 180)) * (Math.cos((Math.PI * lat2) / 180)) * (Math.cos((Math.PI * Math.abs(long1 - long2)) / 180)) ) );
        //System.out.println("Distance: " + distance);
        distance= ((double) Math.round(distance * 100d) / 100d);
        return 9.0;
    }

}
