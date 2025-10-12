package com.portfolio.service;

import com.portfolio.RequestDTO.MarketPrice;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MarketPriceProducer {

  private static final String TOPIC = "market.prices";
  private final KafkaTemplate<String, MarketPrice> kafkaTemplate;

  public MarketPriceProducer(KafkaTemplate<String, MarketPrice> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  public void sendPrice(MarketPrice price) {
    kafkaTemplate.send(TOPIC, price.symbol(), price);
    System.out.println("✅ Published LTP: " + price.symbol() + " = " + price.ltp());
  }
}
