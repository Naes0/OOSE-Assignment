package controller;

import model.Point;
import model.Route;
import model.RouteInterface;
import model.Segment;

import java.io.IOException;
import java.util.*;

public class RouteController
{
    private GeoUtils geoutils;
    private StringController stringProcessor;
    private FactoryRoute factory;
    private List<Route> routeList;
    private int count;

    public RouteController(GeoUtils gu, StringController sp, FactoryRoute fac)
    {
        geoutils = gu;
        stringProcessor = sp;
        factory = fac;
        count = 0;
        routeList = new ArrayList<Route>();
    }

    public void addRouteOrSeg(RouteInterface routeOrSeg)
    {
        if (routeOrSeg instanceof Route && ( !((Route) routeOrSeg).getDescription().equals("stub") ))
        {
            routeList.add(count, (Route)routeOrSeg);
            count++;
        }
        else
        {
            routeList.get(count-1).addRouteOrSeg(routeOrSeg);
        }
    }

    public void printRouteTable() throws SelectRouteException
    {
        if(!routeList.isEmpty())
        {
            System.out.println("\t\t\t\t# Route Table #");
            for (int i = 0; i < routeList.size(); i++)
            {
                System.out.print("\n" + (i+1) + ".");
                routeList.get(i).printRoute(geoutils);
            }
        }
        else
        {
            throw new SelectRouteException("\nRoute List is empty. Please download route data.");
        }
    }

    public void updateRoutes() throws MakeRouteException
    {
        routeList.clear();
        count = 0;
        try
        {
            String data = geoutils.retrieveRouteData();
            String[] dataParts = stringProcessor.stringDecon(data, factory);
            String lineArray[] = new String[2];
            for (int i = 0; i < dataParts.length - 1; i++)
            {
                lineArray[0] = dataParts[i];
                lineArray[1] = dataParts[i + 1];
                RouteInterface ri = factory.makeObject(lineArray);
                if (ri != null)
                {
                    addRouteOrSeg(ri);
                }
            }
            for (Route route : routeList)
            {
                route.setStart();
                route.setEnd();
            }
            attachSubRoutes();
            calcVertDistance();
        }
        catch(IOException e)
        {
            throw new MakeRouteException("Error retrieving data from sever");
        }
        System.out.println("Data retrieval successful!");
    }

    public void calcVertDistance()
    {
        for (Route route : routeList)
        {
            route.setVertUp();
        }
    }

    private void attachSubRoutes() throws MakeRouteException
    {
        List<RouteInterface>  routes;
        Double horizontal = -1.0;
        Double vertical = -1.0;
        Point foundStart = null;
        Point routeStart = null;
        String search = "";
        Route found;
        for(Route totalRoutes : routeList)
        {
            routes = totalRoutes.getRoutes();
            for (RouteInterface route : routes)
            {
                if(route instanceof Route)
                {
                    search = ((Route) route).getName();
                    found = find(search);
                    foundStart = found.getStart();
                    routeStart = ((Segment) routes.get((routes.indexOf(route)-1))).getEnd();
                    horizontal = geoutils.calcMetresDistance(routeStart.getLatitude(), routeStart.getLongitude(), foundStart.getLatitude(), foundStart.getLongitude());
                    vertical = Math.abs(routeStart.getAltitude() - foundStart.getAltitude());
                    if(horizontal < 10.0 && vertical < 2.0)
                    {
                        routes.set(routes.indexOf(route), found);
                    }
                    else
                    {
                        throw new MakeRouteException("Error linking Subroute");
                    }
                }
            }
        }
    }

    public Route find(int i)
    {
        Route found = null;
        found = routeList.get(i-1);
        return found;
    }

    public Route find(String name)
    {
        Route found = null;
        for (Route route : routeList)
        {
            if(route.getName().equals(name))
            {
                found = route;
            }
        }
        return found;
    }

}
