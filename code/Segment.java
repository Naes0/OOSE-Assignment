import java.io.*;
import java.util.*;

public class Segment implements RouteInterface
{
   private String name;
   private Set<Point> segment;
   private String description;

   public Segment(String inName, Point a, Point b, String desc)
   {
      name = inName;
      description = desc;
      segment = new HashSet<Point>();
      segment.add(a);
      segment.add(b);
   }

   @Override
   public Segment find(String name)
   {
      Segment found = null;
      if(this.name.equals(name))
      {
         found = this;
      }
      return found;
   }

   @Override
   public int segmentCount()
   {
      return 1;
   }

   public String toString()
   {
      return ("\nName: " + name + "\nDescritption: " + description);
   }

   @Override
   public void printOut()
   {
      System.out.println("\n\tSegment: " + name);
      System.out.println("\tDescritption: " + description);
   }
}
