import java.io.*;
import java.util.*;

public class Point
{
   private Double latitude;
   private Double longitdue;
   private Double altitude;

   public Point(Double lat, Double longit, Double alt)
   {
      setLat(lat);
      setLong(longit);
      altitude = alt;
   }

   public void setLat(Double lat) throws IllegalArgumentException
   {
      if(lat < -90.0 || lat > 90.0)
      {
         throw new IllegalArgumentException("Out of latitude range");
      }
      else
      {
         latitude = lat;
      }
   }

   public void setLong(Double longit) throws IllegalArgumentException
   {
      if(longit < -180.0 || longit > 180.0)
      {
         throw new IllegalArgumentException("Out of longitude range");
      }
      else
      {
         longitdue = longit;
      }
   }

}
