package routingprogram.model;

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

   public void setLat(Double lat) throws SetLatException
   {
      if(lat < -90.0 || lat > 90.0)
      {
         throw new SetLatException("Out of latitude range", e);
      }
      else
      {
         latitude = lat;
      }
   }

   public void setLong(Double longit) throws SetLongException
   {
      if(longit < -180.0 || longit > 180.0)
      {
         throw new SetLongtException("Out of longitude range", e);
      }
      else
      {
         longitdue = longit;
      }
   }

}
