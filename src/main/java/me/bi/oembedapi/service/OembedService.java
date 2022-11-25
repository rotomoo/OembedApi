package me.bi.oembedapi.service;

import me.bi.oembedapi.exception.CustomException;
import org.json.simple.JSONArray;
import org.springframework.stereotype.Component;

import java.net.URLEncoder;

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

    public String findHost() {
        return null;
    }

    public String findEncodeUrl(String url) {
        String encodeUrl = "";
        try {
            encodeUrl = URLEncoder.encode(url, "utf-8");
        } catch (Exception e) {
            throw new CustomException();
        }
        return encodeUrl;
    }
}
