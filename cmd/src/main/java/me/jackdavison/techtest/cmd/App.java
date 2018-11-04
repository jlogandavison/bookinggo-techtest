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
        // Parse commandline input
        Location pickup, dropoff;
        try {
            pickup = Location.parseLocation(System.getProperty("pickup"));
            dropoff = Location.parseLocation(System.getProperty("dropoff"));
        } catch(IllegalArgumentException exception) {
            System.err.println(exception.getMessage());
            System.exit(1);
        }

        // Get rides from provider(s)
        List<Ride> rides = provider.getRides(pickup, dropoff);

        // Sort output by price
        Collections.sort(rides, new Ride.PriceComparator());

        // Print output
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
