package me.jackdavison.techtest.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;

import me.jackdavison.techtest.client.AggregateProvider;
import me.jackdavison.techtest.client.types.Location;
import me.jackdavison.techtest.client.types.Ride;

public class AggregateProviderTest {

    @Test
    public void emptyAggregateTest() {
        AggregateProvider provider = new AggregateProvider();
        assertTrue(provider.getRides(null, null).isEmpty());
    }

    @Test
    public void simpleAggregateTest() {
        Ride[] testRides = {
            new Ride("Test_Type1", 1),
            new Ride("Test_Type2", 2),
            new Ride("Test_Type3", 3),
            new Ride("Test_Type4", 4)
        };

        Provider p1 = new MockProvider(
            Arrays.asList(testRides[0], testRides[2]));
        Provider p2 = new MockProvider(
            Arrays.asList(testRides[1], testRides[3]));

        AggregateProvider provider = new AggregateProvider();
        provider.includeProvider(p1);
        provider.includeProvider(p2);

        List<Ride> result = provider.getRides(null, null);

        for(Ride ride: testRides) {
            assertTrue(result.contains(ride));
        }
    }

    @Test
    public void parameterPassThroughTest() {
        Location pickup = new Location(0.0, 0.0);
        Location dropoff = new Location(1.0, 1.0);
        Provider p1 = new ExpectedParametersProvider(pickup, dropoff);

        AggregateProvider provider = new AggregateProvider();
        provider.includeProvider(p1);

        provider.getRides(pickup, dropoff);
    }

    @Test
    public void catchingExceptionsTest() {
        // NOTE: Not certain that this is to spec
        // A little uneasy that we are just swallowing errors here
        Ride[] testRides = {
            new Ride("Test_Type1", 1),
            new Ride("Test_Type2", 2)
        };
        Provider p1 = new MockProvider(Arrays.asList(testRides));
        Provider p2 = new FaultyProvider();

        AggregateProvider provider = new AggregateProvider();
        provider.includeProvider(p2);

        // One provider, one throws an error = empty list
        assertTrue(provider.getRides(null, null).isEmpty());

        provider = new AggregateProvider();
        provider.includeProvider(p1);
        provider.includeProvider(p2);

        // Two providers, one throws an error
        //     = still return other provider results
        List<Ride> result = provider.getRides(null, null);
        for(Ride ride: testRides) {
            assertTrue(result.contains(ride));
        }
    }

    private static class MockProvider implements Provider {
        private final List<Ride> result;

        MockProvider(List<Ride> result) {
            this.result = result;
        }

        public List<Ride> getRides(Location pickup, Location dropoff) {
            return this.result;
        }
    }

    private static class ExpectedParametersProvider implements Provider {
        private final Location expectedPickup;
        private final Location expectedDropoff;

        ExpectedParametersProvider(Location expectedPickup, Location expectedDropoff) {
            this.expectedPickup = expectedPickup;
            this.expectedDropoff = expectedDropoff;
        }

        public List<Ride> getRides(Location pickup, Location dropoff) {
            assertEquals(expectedPickup, pickup);
            assertEquals(expectedDropoff, dropoff);
            return new ArrayList<Ride>();
        }
    }

    public static class FaultyProvider implements Provider {
        public List<Ride> getRides(Location pickup, Location dropoff) {
           throw new RuntimeException(); 
        }
    }
}
