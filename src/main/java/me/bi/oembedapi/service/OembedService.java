package me.bi.oembedapi.service;

import me.bi.oembedapi.exception.CustomException;
import org.json.simple.JSONArray;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import static me.bi.oembedapi.exception.ErrorCode.*;

@Component
public class OembedService {

    public String getOembedJson() {
        return null;
    }

    public JSONArray urlToJsonObject() {
        return null;
    }

    public String findOembedFullUrl() {
        return null;
    }

    public String findOembedUrl() {
        return null;
    }

    public String urlToJsonArray() {
        return null;
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
