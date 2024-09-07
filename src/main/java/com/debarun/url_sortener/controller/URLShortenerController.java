package com.debarun.url_sortener.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.debarun.url_shortener.service.URLShortenerService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
public class URLShortenerController {
	@Autowired
	private URLShortenerService urlShortenerService;
	
	@PostMapping("/shorten")
	public ResponseEntity<String> shortenUrl(@RequestBody String originalUrl) {
        String shortUrl = urlShortenerService.shortenURL(originalUrl);
        return ResponseEntity.ok(shortUrl);
    }
	
	@GetMapping("/{shortUrl}")
	public ResponseEntity<Void> redirectToOriginalUrl(@PathVariable String shortUrl, HttpServletResponse response) throws IOException{
		String originalUrl = urlShortenerService.getOriginalUrl(shortUrl);
        if (originalUrl == null) {
            return ResponseEntity.notFound().build();
        }
        response.sendRedirect(originalUrl);
        return ResponseEntity.status(HttpStatus.FOUND).build();
	}
	
	@GetMapping("/metrics")
    public ResponseEntity<List<Map.Entry<String, Integer>>> getMetrics() {
        return ResponseEntity.ok(urlShortenerService.getTopDomains());
    }
}
