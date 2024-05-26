package com.eldar.challengessr.api.exceptions.customs;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class BadRequestException extends RuntimeException{
    public BadRequestException(String messages) {
        super(messages);
    }
}
