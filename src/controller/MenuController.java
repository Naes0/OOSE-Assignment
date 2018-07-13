package controller;


import model.Tracker;
import view.MainMode;
import view.MenuInterface;

public class MenuController
{
    private MenuInterface state = new MainMode();

    public void setState(MenuInterface newState)
    {
        state = newState;
    }

    public void menuDisplay(RouteController rc, Tracker tracker)
    {
        state.menuDisplay(this, tracker, rc);
    }
}
