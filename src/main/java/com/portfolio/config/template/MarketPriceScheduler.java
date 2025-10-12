package com.portfolio.config.template;

import com.portfolio.RequestDTO.MarketPrice;
import com.portfolio.service.AlphaVantageService;
import com.portfolio.service.MarketPriceProducer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MarketPriceScheduler {

  private final AlphaVantageService alphaService;
  private final MarketPriceProducer producer;

  public MarketPriceScheduler(AlphaVantageService alphaService, MarketPriceProducer producer) {
    this.alphaService = alphaService;
    this.producer = producer;
  }

  // Fetch every 1 minute (adjust as needed)
  @Scheduled(fixedRate = 60000)
  public void fetchAndPublishPrices() {
    String[] symbols = {"IBM"};

    for (String symbol : symbols) {
      try {
        MarketPrice price = alphaService.getLatestPrice(symbol);
        producer.sendPrice(price);
      } catch (Exception e) {
        System.err.println("❌ Failed to fetch for " + symbol + ": " + e.getMessage());
      }
    }
  }
}
