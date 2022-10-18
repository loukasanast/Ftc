package io.anastasiou;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Connection {
    private String[] URLs;

    public Connection() {
        this.URLs = new String[3];
        this.URLs[0] = "https://www.google.com/";
        this.URLs[1] = "https://www.bing.com:443/?toWww=1&amp;redig=AF4AED8204E3490FA91A399E603E0AA3";
        this.URLs[2] = "https://www.yahoo.com/";
    }

    public String[] getData() throws IOException, InterruptedException {
        String[] result = new String[3];
        HttpClient client = HttpClient.newBuilder().build();

        for(int i = 0; i < URLs.length; i++) {
            HttpRequest req = HttpRequest.newBuilder()
                    .uri(URI.create(URLs[i]))
                    .build();

            HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());
            result[i] = String.format("%s%n%s%n%n", URLs[i].toUpperCase(), res.body().strip());
        }

        return result;
    }
}
