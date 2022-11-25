package me.bi.oembedapi.service;

import me.bi.oembedapi.exception.CustomException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import static me.bi.oembedapi.exception.ErrorCode.*;

@Component
public class OembedService {

    @Value("${oembed.providers.url}")
    private String oembedProvidersUrl;

    @Value("${oembed.facebook.accessToken}")
    private String accessToken;

    public String getOembedJson() {
        return null;
    }

    public JSONArray urlToJsonObject(String url) {
        JSONArray jsonArray = null;

        try {
            URL providersUrl = new URL(url);
            URLConnection urlConnection = providersUrl.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            jsonArray = (JSONArray) new JSONParser().parse(br);
        } catch (Exception e) {
            throw new CustomException(NOT_SUPPORTED_URL);
        }
        return jsonArray;
    }

    /**
     *  Facebook의 API를 사용하는경우 access_Token이 필요하며 해당 access_Token 앱은 oembed Read 기능이 Advanced Access 수준이어야한다.
     *  Advanced Access 수준이 되려면 앱 검수를 받아 승인되야함. 승인 과정은 5일정도 소요.
     *  https://graph.facebook.com/v14.0/instagram_oembed?url=https://www.instagram.com/p/BUawPlPF_Rx/&access_token={accessToken}
     */
    public String findOembedFullUrl(String oembedUrl, String encodeUrl) {
        // vimeo.oembedUrl = "https://vimeo.com/api/oembed.{format}" => https://vimeo.com/api/oembed.json
        oembedUrl = oembedUrl.replace("{format}", "json");

        // https://vimeo.com/api/oembed.json?url=encodeUrl
        oembedUrl += "?url=" + encodeUrl + (oembedUrl.contains("json") ? "" : "&format=json");

        // instagram => &format=json&access_token={accessToken}
        oembedUrl += oembedUrl.contains("facebook") ? "&access_token=" + accessToken : "";

        return oembedUrl;
    }

    public String findOembedUrl(JSONArray oembedProvidersJsonArray, String searchUrlHost) {
        String oembedUrl = "";
        try {
            for (Object oembedProvidersJsonObject : oembedProvidersJsonArray) {
                JSONObject oembedProvidersJson = (JSONObject) oembedProvidersJsonObject;
                String providerUrlValue = oembedProvidersJson.get("provider_url").toString();
                if (findHost(providerUrlValue).equals(searchUrlHost)) {
                    JSONArray endpointsValue = (JSONArray) oembedProvidersJson.get("endpoints");
                    oembedUrl = ((JSONObject) endpointsValue.get(0)).get("url").toString();
                    break;
                }
            }
        } catch (Exception e) {
            throw new CustomException(INVALID_PROTOCOL);
        }

        return oembedUrl;
    }

    public JSONArray urlToJsonArray(String url) {
        JSONArray jsonArray = null;

        try {
            URL providersUrl = new URL(url);
            URLConnection urlConnection = providersUrl.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            jsonArray = (JSONArray) new JSONParser().parse(br);
        } catch (Exception e) {
            throw new CustomException(NOT_SUPPORTED_URL);
        }
        return jsonArray;
    }

    public String findHost(String url) {
        String provider = "";
        try {
            String host = new URL(url).getHost();
            String[] hostSplit = host.split("\\.");
            provider = hostSplit.length > 2 ? hostSplit[1] : hostSplit[0];
        } catch (MalformedURLException e) {
            throw new CustomException(INVALID_PROTOCOL);
        }
        return provider;
    }

    public String findEncodeUrl(String url) {
        String encodeUrl = "";
        try {
            encodeUrl = URLEncoder.encode(url, "utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new CustomException(NOT_SUPPORTED_URL);
        }
        return encodeUrl;
    }
}
