package com.portfolio.service;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionService {
  // For single instance: ConcurrentHashMap. For multiple instances: use Redis sets.
  private final ConcurrentMap<String, Set<String>> symbolToUsers = new ConcurrentHashMap<>();

  public void addSubscription(String userId, String symbol) {
    symbolToUsers.computeIfAbsent(symbol, k -> ConcurrentHashMap.newKeySet()).add(userId);
  }

  public void removeSubscription(String userId, String symbol) {
    Optional.ofNullable(symbolToUsers.get(symbol)).ifPresent(set -> set.remove(userId));
  }

  public Set<String> subscribersForSymbol(String symbol) {
    return symbolToUsers.getOrDefault(symbol, Collections.emptySet());
  }
}
