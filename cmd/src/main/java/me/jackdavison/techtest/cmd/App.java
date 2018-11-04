package me.jackdavison.techtest.cmd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import me.jackdavison.techtest.client.Provider;
import me.jackdavison.techtest.client.types.Location;
import me.jackdavison.techtest.client.types.Ride;
import me.jackdavison.techtest.cmd.OutputBuilder;
import me.jackdavison.techtest.cmd.RideListOutputBuilder;

public class App implements Provider
{

    private static Provider provider = new App();
    private static OutputBuilder outputbuilder = new RideListOutputBuilder();

    public static void main( String[] args )
    {
        List<Ride> rides = provider.getRides(null, null);
        Collections.sort(rides, new Ride.PriceComparator());
        System.out.println(outputbuilder.buildOutput(rides));
    }

    public ArrayList<Ride> getRides(Location lat, Location lng) {
        Ride[] rides = {
            new Ride("Hello", 0),
            new Ride("World", 1)
        };
        return new ArrayList<Ride>(Arrays.asList(rides));
    }
}
