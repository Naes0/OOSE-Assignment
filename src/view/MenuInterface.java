package view;

import controller.MenuController;
import controller.RouteController;
import model.Tracker;

public interface MenuInterface
{
    void menuDisplay(MenuController menu, Tracker tracker, RouteController rc);
}
