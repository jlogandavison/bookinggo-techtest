package me.jackdavison.techtest.cmd;

import java.util.ArrayList;
import java.util.Arrays;

import me.jackdavison.techtest.client.Provider;
import me.jackdavison.techtest.client.types.Location;
import me.jackdavison.techtest.client.types.Ride;
import me.jackdavison.techtest.cmd.OutputBuilder;
import me.jackdavison.techtest.cmd.RideListOutputBuilder;

public class App implements Provider
{

    private static OutputBuilder outputbuilder = new RideListOutputBuilder();

    public static void main( String[] args )
    {
        String output = outputbuilder.buildOutput(
            new App().getRides(null, null));
        System.out.println(output);
    }

    public ArrayList<Ride> getRides(Location lat, Location lng) {
        Ride[] rides = {
            new Ride("Hello", 0),
            new Ride("World", 1)
        };
        return new ArrayList<Ride>(Arrays.asList(rides));
    }
}
