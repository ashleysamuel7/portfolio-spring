package com.portfolio.ResponseDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class APIResponse<T> {

    private T data;
    private String status;
    private String message;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;

    public APIResponse(T data, String status, String message) {
        this.data = data;
        this.status = status;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }
}
