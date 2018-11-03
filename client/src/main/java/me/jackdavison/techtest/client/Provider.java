package me.jackdavison.techtest.client;

import java.util.ArrayList;

import me.jackdavison.techtest.client.types.Location;
import me.jackdavison.techtest.client.types.Ride;

public interface Provider {
    public ArrayList<Ride> getRides(Location pickup, Location destination);
}

