package me.jackdavison.techtest.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import me.jackdavison.techtest.client.types.Location;
import me.jackdavison.techtest.client.types.Ride;
import me.jackdavison.techtest.client.AggregateProvider;
import me.jackdavison.techtest.client.HttpApiProvider;
import me.jackdavison.techtest.client.Provider;

@RestController
public class RideSearchController {

    private final Provider provider;

    RideSearchController() throws URISyntaxException {
        // This kind of configuration probably belongs in a config file IRL
        AggregateProvider provider = new AggregateProvider();
        provider.includeProvider(new HttpApiProvider(new URI("https://techtest.rideways.com/dave/")));
        provider.includeProvider(new HttpApiProvider(new URI("https://techtest.rideways.com/eric/")));
        provider.includeProvider(new HttpApiProvider(new URI("https://techtest.rideways.com/jeff/")));
        this.provider = provider;
    }

    @GetMapping("/")
    List<Ride> getRides(
            @RequestParam(value="pickup", required=true) String str_pickup,
            @RequestParam(value="dropoff", required=true) String str_dropoff) {

        Location pickup = Location.parseLocation(str_pickup);
        Location dropoff = Location.parseLocation(str_dropoff);

        // Get rides from provider(s)
        List<Ride> rides = provider.getRides(pickup, dropoff);

        // Sort output by price
        Collections.sort(rides, new Ride.PriceComparator());
        return rides;
    }

}

