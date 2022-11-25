package me.bi.oembedapi.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.bi.oembedapi.service.OembedService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
@RequiredArgsConstructor
public class OembedController {

    private final OembedService oembedService;

    @GetMapping("/oembed")
    public ResponseEntity<?> viewOembedData(@RequestParam("url") String url) {
        log.info("oembedController");
        return ResponseEntity.ok(oembedService.getOembedJson(url));
    }
}
