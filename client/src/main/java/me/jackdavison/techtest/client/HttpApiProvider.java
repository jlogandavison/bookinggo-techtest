package me.jackdavison.techtest.client;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import me.jackdavison.techtest.client.Provider;
import me.jackdavison.techtest.client.types.Location;
import me.jackdavison.techtest.client.types.Ride;

public class HttpApiProvider implements Provider {

    private URI baseUri;

    public HttpApiProvider(URI uri) {
        this.baseUri = uri;
    }

    public List<Ride> getRides(Location pickup, Location dropoff) {
        String builtUri
            = UriComponentsBuilder.fromUri(this.baseUri)
                .queryParam("pickup", pickup)
                .queryParam("dropoff", dropoff)
                .build().toUriString();
        ResponseEntity<ResponsePOJO> response =
            new RestTemplate().getForEntity(builtUri, ResponsePOJO.class);

        List<Ride> result = new ArrayList<Ride>();
        for(ResponsePOJO.RidePOJO option: response.getBody().options) {
            result.add(new Ride(option.car_type, option.price));
        }
        return result;
    }

    private static class ResponsePOJO {

        protected String supplier_id;
        protected Location pickup;
        protected Location dropoff;
        @JsonProperty(value="options")
        protected List<RidePOJO> options;

        protected static class RidePOJO {
            @JsonProperty(value="car_type")
            protected String car_type;
            @JsonProperty(value="price")
            protected int price;
        }
    }
}
