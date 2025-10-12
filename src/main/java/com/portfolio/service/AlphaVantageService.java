package com.portfolio.service;

import com.portfolio.RequestDTO.MarketPrice;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AlphaVantageService {

  @Value("${stock.data.api-key}")
  private String apiKey;

  private final RestTemplate restTemplate = new RestTemplate();

  public MarketPrice getLatestPrice(String symbol) {
    String url =
        String.format(
            "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=%s&interval=5min&apikey=%s",
            symbol, apiKey);
    String response = restTemplate.getForObject(url, String.class);
    JSONObject json = new JSONObject(response);

    // The time series object
    JSONObject timeSeries = json.getJSONObject("Time Series (5min)");

    // Get the latest timestamp key
    String latestTimestamp = timeSeries.keys().next();

    // Get the latest data entry
    JSONObject latestData = timeSeries.getJSONObject(latestTimestamp);

    // Get the closing price
    double ltp = latestData.getDouble("4. close");

    return new MarketPrice(symbol, ltp);
  }
}
