package com.debarun.url_shortener.service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class URLShortenerService {
	private final Map <String, String> urlMap = new ConcurrentHashMap<String, String>();
	private final Map <String, Integer> domainMetrics = new ConcurrentHashMap<String, Integer>();
	
	public String shortenURL(String originalURL) {
		if (urlMap.containsKey(originalURL)) {
            return urlMap.get(originalURL);
        }
		String shortenedURL = generateShortUrl();
		urlMap.put(originalURL, shortenedURL);
		trackDomain(originalURL);
		return shortenedURL;
	}
	
	private void trackDomain(String originalURL) {
		String domain = getDomainFromUrl(originalURL);
		domainMetrics.put(domain, domainMetrics.getOrDefault(domain, 0) + 1);
	}

	private String getDomainFromUrl(String url) {
		try {
			return new URL(url).getHost();
		} catch (MalformedURLException e) {
			throw new RuntimeException("Invalid URL");
		}
	}

	private String generateShortUrl() {
        return UUID.randomUUID().toString().substring(0, 6);  // Simple UUID-based code
    }
	
	public String getOriginalUrl(String shortenedURL) {
        return urlMap.entrySet().stream()
            .filter(entry -> entry.getValue().equals(shortenedURL))
            .map(Map.Entry::getKey)
            .findFirst()
            .orElse(null);
    }
	
	public List<Map.Entry<String, Integer>> getTopDomains() {
        return domainMetrics.entrySet().stream()
            .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
            .limit(3)
            .collect(Collectors.toList());
    }
}
