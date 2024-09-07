package com.debarun.url_shortener.model;

import lombok.Data;

@Data
public class UrlMapping {
	private String originalURL;
	private String shortenedURL;
}
