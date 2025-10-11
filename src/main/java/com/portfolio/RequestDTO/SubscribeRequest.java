package com.portfolio.RequestDTO;

import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubscribeRequest {

  private List<String> symbols;
  private String name;

  public SubscribeRequest(List<String> symbols) {
    this.symbols = symbols;
  }

  public List<String> getSymbols() {
    return symbols;
  }

  public void setSymbols(List<String> symbols) {
    this.symbols = symbols;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof SubscribeRequest)) return false;
    SubscribeRequest that = (SubscribeRequest) o;
    return Objects.equals(symbols, that.symbols);
  }

  @Override
  public int hashCode() {
    return Objects.hash(symbols);
  }

  @Override
  public String toString() {
    return "SubscribeRequest{" + "symbols=" + symbols + '}';
  }
}
