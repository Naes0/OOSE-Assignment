package model;

import java.io.*;
import java.util.*;

public class Point
{
   private Double latitude;
   private Double longitude;
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
         longitude = longit;
      }
   }

   public Double getLatitude()
   {
      return latitude;
   }

   public void setLatitude(Double latitude)
   {
      this.latitude = latitude;
   }

   public Double getLongitude()
   {
      return longitude;
   }

   public void setLongitude(Double longitude)
   {
      this.longitude = longitude;
   }

   public Double getAltitude()
   {
      return altitude;
   }

   public void setAltitude(Double altitude)
   {
      this.altitude = altitude;
   }

   public void printOut()
   {
      System.out.println("\tlatitude: " + latitude + " longitude: " + longitude + " altitude: " + altitude);
   }

}
