package controller;

public abstract class GpsLocator
{
   protected abstract void locationReceived(double latitude, double longitude, double altitude);
}
