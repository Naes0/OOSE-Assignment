package controller;

import java.io.IOException;

public class StringController
{
   public String[] stringDecon(String data, FactoryRoute factory) throws IOException, MakeRouteException
   {
      data.replaceAll("(?m)^[ \t]*", "");
      //System.out.println(data);
      String[] dataParts = data.split("\n");
      //System.out.println("datalength: " + dataParts.length);
      return dataParts;
   }

}
