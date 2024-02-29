package com.example.testtask.model.base;

import lombok.*;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Component
public class ValidationResponse {

    private boolean isValid = true;
    private List<String> messages = new ArrayList<>();

    public void addMessage(String message) {
        this.messages.add(message);
    }
}