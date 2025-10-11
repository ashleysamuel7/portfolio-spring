package com.portfolio.controller;

import com.portfolio.RequestDTO.SubscribeRequest;
import com.portfolio.service.SubscriptionService;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SubscriptionController {
  private final SubscriptionService subs;

  //    @PostMapping("/subscribe")
  //    public ResponseEntity<Void> subscribe(@RequestBody SubscribeRequest req, Principal
  // principal) {
  //        String userId = principal.getName();
  //        req.getSymbols().forEach(symbol -> subs.addSubscription(userId, symbol));
  //        return ResponseEntity.ok().build();
  //    }
  @PostMapping("/subscribe")
  public ResponseEntity<Void> subscribe(@RequestBody SubscribeRequest req) {
    String userId = req.getName();
    req.getSymbols().forEach(symbol -> subs.addSubscription(userId, symbol));
    return ResponseEntity.ok().build();
  }

  @PostMapping("/unsubscribe")
  public ResponseEntity<Void> unsubscribe(@RequestBody SubscribeRequest req, Principal principal) {
    String userId = principal.getName();
    req.getSymbols().forEach(symbol -> subs.removeSubscription(userId, symbol));
    return ResponseEntity.ok().build();
  }
}
