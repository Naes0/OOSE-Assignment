import java.io.*;
import java.util.*;

public class Main
{
   public static void main(String[] args)
   {
      Point start = new Point(0.0, 0.0, 0.0);
      Point m1 = new Point(10.0, 0.0, 0.0);
      Point m2 = new Point(20.0, 0.0, 0.0);
      Point end = new Point(30.0, 0.0, 0.0);

      Segment seg1 = new Segment("start-m1",start, m1, "Start to middle1");
      Segment seg2 = new Segment("m1-m2", m1, m2, "middle1 to middle2");
      Segment seg3 = new Segment("m2-end", m2, end, "middle2 to end");

      Route mainRoute = new Route("Main Route", "This is a main Route", start, end);
      mainRoute.addRouteOrSeg(seg1);
      mainRoute.addRouteOrSeg(seg3);

      Route subRoute = new Route("SubRoute", "This is a subRoute", m1, m2);
      subRoute.addRouteOrSeg(seg2);

      mainRoute.addRouteOrSeg(subRoute);
      mainRoute.printOut();
      System.out.println(mainRoute.segmentCount());
      System.out.println(mainRoute.find("m2-end"));

   }
}
