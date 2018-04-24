import java.io.*;
import java.util.*;

public class Segment
{
   private Set<Point> segment;
   private String description;

   public Segment(Point a, Point b, String desc)
   {
      segment = new TreeSet<Point>();
      segment.add(a);
      segment.add(b);
      description = desc;
   }
}
