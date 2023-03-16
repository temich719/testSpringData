package com.example.testspringdata.message;

import lombok.*;
import org.springframework.stereotype.Component;

@EqualsAndHashCode
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class AnswerMessage {
    private String message;
    private String status;
}
