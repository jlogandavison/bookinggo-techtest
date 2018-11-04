package me.jackdavison.techtest.client;

import java.util.List;

import me.jackdavison.techtest.client.types.Location;
import me.jackdavison.techtest.client.types.Ride;

public interface Provider {
    public List<Ride> getRides(Location pickup, Location destination);
}

