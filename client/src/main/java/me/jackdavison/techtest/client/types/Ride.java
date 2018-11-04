package me.jackdavison.techtest.client.types;

import java.util.Comparator;

public final class Ride {
    public final String type;
    public final int price;

    public Ride(String type, int price) {
        this.type = type;
        this.price = price;
    }

    public static class PriceComparator implements Comparator<Ride> {

        @Override
        public int compare(Ride ride1, Ride ride2) {
            return ride1.price - ride2.price;
        }
    }
}

