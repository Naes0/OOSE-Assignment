package view;

import controller.*;
import model.Tracker;

import java.util.Scanner;

public class MainMode implements MenuInterface
{
    @Override
    public void menuDisplay(MenuController menu, Tracker tracker, RouteController rc)
    {
        int input = 0;
        while (input != 4)
        {
            System.out.println("# Main Menu #");
            System.out.println("1. Download routes");
            System.out.println("2. Show route table");
            System.out.println("3. Tracking");
            System.out.println("4. Exit");
            System.out.println("Input a number to select an option: ");

            Scanner sc = new Scanner(System.in);
            input = sc.nextInt();

            switch (input)
            {
                case 1:
                    try
                    {
                        rc.updateRoutes();
                    } catch (MakeRouteException e)
                    {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:

                    try
                    {
                        rc.printRouteTable();
                    } catch (SelectRouteException e)
                    {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    menu.setState(new Tracking());
                    menu.menuDisplay(rc, tracker);
                    break;
                default:
                    System.out.println("Please enter one of the following options.");
            }
        }
    }
}
