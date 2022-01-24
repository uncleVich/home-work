package com.sbrf.reboot.service;

import com.sbrf.reboot.dto.Account;

import com.sbrf.reboot.repository.AccountRepository;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AccountService {

    @NonNull
    AccountRepository accountRepository;

    public boolean isAccountExist(long id, Account account) {
        return accountRepository.getAllAccountsByClientId(id).contains(account);
    }
}
