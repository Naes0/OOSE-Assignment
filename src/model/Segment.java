package model;


public class Segment implements RouteInterface
{
   private String name;
   private Point start;
   private Point end;
   private String description;
   private Double distance;

   public void setName(String name)
   {
      this.name = name;
   }

   public Segment(Point a, Point b, String desc)
   {
      name = "Segment";
      description = desc;
      start = a;
      end = b;
      distance = 0.0;
   }

   @Override
   public RouteInterface find(String name)
   {
      RouteInterface found = null;
      if(this.name.equals(name))
      {
         found = this;
      }
      return found;
   }

   @Override
   public Segment findSeg(Point location)
   {
      Segment seg = null;
      if(inRange(location))
      {
         seg = this;
      }
      return seg;
   }

   public boolean inRange(Point location)
   {
      boolean alt = false;
      boolean lat = false;
      boolean lon = false;
      boolean ret = false;
      if((location.getAltitude() <= end.getAltitude() && location.getAltitude() >= start.getAltitude()) || (location.getAltitude() >= end.getAltitude() && location.getAltitude() <= start.getAltitude()) )
      {
         alt = true;
      }
      if(location.getLatitude() <= end.getLatitude() && location.getLatitude() >= start.getLatitude())
      {
         lat = true;
      }
      if(location.getLongitude() <= end.getLongitude() && location.getLongitude() >= start.getLongitude())
      {
         lon = true;
      }
      if(alt && lat && lon)
      {
         ret = true;
      }
      return ret;
   }

   public String getDescription()
   {
      return description;
   }

   public Point getStart()
   {
      return start;
   }

   public Point getEnd()
   {
      return end;
   }

   @Override
   public int segmentCount()
   {
      return 1;
   }

   @Override
   public Double calcVerticalDistance()
   {
      distance = end.getAltitude() - start.getAltitude();
      return distance;
   }

   public Double getDistance()
   {
       return distance;
   }

   public void printOut()
   {
      System.out.print("\nName: " + name + "\nDescritption: " + description);
      System.out.print("Start: ");
      start.printOut();
      System.out.print("End: ");
      end.printOut();
   }

}
