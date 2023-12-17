package az.ematrix.ticketbot.mailDto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

    @Data
    @RequiredArgsConstructor
    public class MailDto {
        String to;
        String from;
    }

