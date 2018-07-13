import controller.*;

import controller.MenuController;
import model.Tracker;
import view.TrackerObsever;


public class Main
{
   public static void main(String[] args)
   {
       GeoUtils gu = new GeoUtils();
       StringController sp = new StringController();
       FactoryRoute fac = new FactoryRoute();
       RouteController rc = new RouteController(gu, sp, fac);
       MenuController menu = new MenuController();
       Tracker tracker = new Tracker(gu);
       TrackerObsever trackerObsever = new TrackerObsever(tracker);
       TrackerController trackerController = new TrackerController(tracker);
       trackerController.setup();
       trackerObsever.setup();

       menu.menuDisplay(rc,tracker);
   }
}
