package me.jackdavison.techtest.cmd;

import java.util.ArrayList;
import java.util.Arrays;

import me.jackdavison.techtest.client.Provider;
import me.jackdavison.techtest.client.types.Location;
import me.jackdavison.techtest.client.types.Ride;

/**
 * Hello world!
 *
 */
public class App implements Provider
{
    public static void main( String[] args )
    {
        System.out.println(new App().getRides(
            new Location(0,0),
            new Location(1,1)
        ));
    }

    public ArrayList<Ride> getRides(Location lat, Location lng) {
        Ride[] rides = {
            new Ride("Hello", 0),
            new Ride("World", 1)
        };
        return new ArrayList<Ride>(Arrays.asList(rides));
    }
}
