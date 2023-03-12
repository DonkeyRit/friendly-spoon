package com.github.amazonnotifier.site;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.Builder;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.json.JSONObject;

public class AmazonScrapper {
    private final static String GET_CSRF_TOKEN_FROM_COOKIE_URL = "https://www.amazon.com/portal-migration/hz/glow/get-rendered-address-selections?deviceType=desktop&pageType=Detail&storeContext=pc&actionSource=desktop-modal";
    private final static String CSRF_TOKEN_FROM_COOKIE_REGEX = "CSRF_TOKEN : \"(.+?)\"";
    private final static String CSRF_TOKEN_HEADER_NAME_KEY = "anti-csrftoken-a2z";
    private final static String USER_AGENT_HEADER_NAME_KEY = "User-Agent";

    public String parse(String url, String country) throws IOException, InterruptedException {
        Map<String, String> httpHeaders = new HashMap<>();
        httpHeaders.put(USER_AGENT_HEADER_NAME_KEY,
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36");

        addCSRFTokenToHeaders(url, httpHeaders);
        retrieveCSRFTokenFromCookie(httpHeaders);

        // Set up the request headers and cookies
        Connection connection = Jsoup.connect(url)
                .header("User-Agent",
                        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36")
                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                .header("Accept-Language", "en-US,en;q=0.5")
                .header("Referer", "https://www.google.com/")
                .header("Connection", "keep-alive")
                .cookie("name", "value")
                .cookie("another_name", "another_value");

        // Send the request and retrieve the response
        Document doc = connection.get();

        System.out.println(doc.outerHtml());

        Element deliveryMessage = doc.selectFirst("#deliveryBlockMessage");
        if (deliveryMessage != null) {
            return deliveryMessage
                    .text()
                    .trim();
        }

        return null;
    }

    /**
     * Method add 'anti-csrftoken-a2z' token to headers map.
     * Firstly we should retrieve the token 'anti-csrftoken-a2z' from the amazon
     * page.
     * For this:
     * - Make a request to amazon site with a specific user-agent to be identified
     * as browser;
     * - Get token by XPATH selector.
     * 
     * @param url         - amazon url
     * @param httpHeaders - amazon headers
     * @throws IOException
     * @throws InterruptedException
     */
    private void addCSRFTokenToHeaders(String url, Map<String, String> httpHeaders) throws IOException, InterruptedException
    {
        HttpClient httpClient = HttpClient
            .newBuilder()
            .build();
        
        HttpRequest request = HttpRequest
            .newBuilder()
            .uri(URI.create(url))
            .build();

        HttpResponse<InputStream> response = httpClient.send(request, HttpResponse.BodyHandlers.ofInputStream());

        InputStream inputStream = new GZIPInputStream(response.body());
        String responseText = new String(inputStream.readAllBytes(), "UTF-8");


        Connection connection = Jsoup.connect(url);
        for (Entry<String, String> header : httpHeaders.entrySet()) {
            connection.header(header.getKey(), header.getValue());
        }

        String data = connection
                .get()
                .select("#nav-global-location-data-modal-action")
                .attr("data-a-modal");

        if (data == null || data.isEmpty()) {
            throw new IllegalArgumentException("Invalid page content");
        }

        String token = new JSONObject(data)
                .getJSONObject("ajaxHeaders")
                .getString("anti-csrftoken-a2z");

        httpHeaders.put(CSRF_TOKEN_HEADER_NAME_KEY, token);
    }

    /**
     * Method retrieves CSRF token from cookie and replace in http headers.
     * 
     * @param httpHeaders - amazon headers
     * @throws InterruptedException
     * @throws IOException
     */
    private void retrieveCSRFTokenFromCookie(Map<String, String> httpHeaders) throws IOException, InterruptedException 
    {
    
        HttpClient httpClient = HttpClient
                .newBuilder()
                .build();

        Builder requestBuilder = HttpRequest.newBuilder().uri(URI.create(GET_CSRF_TOKEN_FROM_COOKIE_URL));
        for (Entry<String, String> header : httpHeaders.entrySet()) {
            requestBuilder.header(header.getKey(), header.getValue());
        }

        HttpRequest request = requestBuilder.build();
        HttpResponse<InputStream> response = httpClient.send(request, HttpResponse.BodyHandlers.ofInputStream());

        InputStream inputStream = new GZIPInputStream(response.body());
        String responseText = new String(inputStream.readAllBytes(), "UTF-8");


        String token = GetByRegex(responseText, CSRF_TOKEN_FROM_COOKIE_REGEX);
        httpHeaders.put(CSRF_TOKEN_HEADER_NAME_KEY, token);
    }

    private void changeAddress() {

    }

    private static String decodeGzip(byte[] compressedData) throws IOException {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(compressedData);
                GZIPInputStream gzis = new GZIPInputStream(bais);
                InputStreamReader reader = new InputStreamReader(gzis)) {
            StringBuilder sb = new StringBuilder();
            char[] buffer = new char[1024];
            int length;
            while ((length = reader.read(buffer)) != -1) {
                sb.append(buffer, 0, length);
            }
            return sb.toString();
        }
    }

    private static String GetByRegex(String target, String regex) {
        Pattern pattern = Pattern.compile("CSRF_TOKEN : \"(.+?)\"");
        Matcher matcher = pattern.matcher(target);

        // Check if the pattern matches and extract the CSRF_TOKEN
        if (matcher.find()) {
            return matcher.group(1);
        }
        throw new IllegalArgumentException("Couldn't find anything by regex. Regex - " + regex);
    }
}
