import java.io.*;
import java.util.*;

public class Route
{
   private String name;
   private String description;
   private Point start;
   private Point end;
   private List<Segment> path;

   public Route(String inName, String inDesc, Point a, Point b)
   {
      name = inName;
      description = inDesc;
      start = a;
      end = b;
      path = new ArrayList<Segment>();
   }

   public void addSegment(Segment seg)
   {
      path.add(seg);
   }
}
