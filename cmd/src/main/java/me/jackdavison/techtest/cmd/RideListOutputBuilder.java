package me.jackdavison.techtest.cmd;

import java.lang.StringBuilder;
import java.util.Collection;

import me.jackdavison.techtest.client.types.Ride;
import me.jackdavison.techtest.cmd.OutputBuilder;

public class RideListOutputBuilder implements OutputBuilder<Ride> {

    public String buildOutput(Collection<Ride> rides) {
        StringBuilder builder = new StringBuilder();

        for(Ride ride: rides) {
            builder.append(String.format(
                "%s - %s - %d",
                ride.type,
                "supplier",
                ride.price
            ));
            builder.append(System.getProperty("line.separator"));
        }
        return builder.toString();
    }
}

