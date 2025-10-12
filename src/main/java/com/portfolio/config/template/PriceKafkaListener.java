package com.portfolio.config.template;

import com.portfolio.RequestDTO.MarketPrice;
import com.portfolio.service.SubscriptionService;
import java.util.Set;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

// Kafka listener
@Component
@AllArgsConstructor
public class PriceKafkaListener {

  private final SimpMessagingTemplate messagingTemplate;
  private final SubscriptionService subscriptionService; // maps symbol->user/session

  @KafkaListener(topics = "market.prices", groupId = "price-forwarder")
  public void onPrice(MarketPrice price) {
    // find subscribers for this symbol
    Set<String> userIds = subscriptionService.subscribersForSymbol(price.symbol());
    for (String userId : userIds) {
      // send to user-specific queue (requires user destination prefix config)
      messagingTemplate.convertAndSendToUser(userId, "/queue/prices", price);
    }
  }
}
