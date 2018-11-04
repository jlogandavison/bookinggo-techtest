package me.jackdavison.techtest.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import me.jackdavison.techtest.client.types.Location;
import me.jackdavison.techtest.client.types.Ride;
import me.jackdavison.techtest.client.Provider;

public class AggregateProvider implements Provider {

    private final Collection<Provider> providers;

    public AggregateProvider() {
        this.providers = new HashSet<Provider>();
    }

    public void includeProvider(Provider provider) {
        this.providers.add(provider);
    }

    public List<Ride> getRides(Location pickup, Location dropoff) {
        List<Ride> result = new ArrayList<Ride>();

        // THIS IS SLOW - has to wait for each request in sequence
        // better implementation process each provider async
        for(Provider provider: this.providers) {
            try {
                result.addAll(provider.getRides(pickup, dropoff));
            } catch(Exception exception) {
                // this is way too defensive probably
                // at least should be logging these errors
            }
        }

        return result;
    }
}

