package com.sbrf.reboot.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class Account {

    @Setter(AccessLevel.NONE)
    private long id;
    private String number;
    private BigDecimal balance;
    private LocalDate createDate = LocalDate.now();

    public Account(String number) {
        this.number = number;
    }

    public Account(long id, String number) {
        this.id = id;
        this.number = number;
    }


    public LocalDate getCreateDate() {
        return createDate;
    }
}