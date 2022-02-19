package com.sbrf.reboot.service;

import com.sbrf.reboot.dto.Account;

import com.sbrf.reboot.repository.AccountRepository;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Optional;
import java.util.Set;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AccountService {

    @NonNull
    AccountRepository accountRepository;

    private Set<Account> getAccounts(long id) throws IOException {
        return accountRepository.getAllAccountsByClientId(id);
    }

    public boolean isAccountExist(long id, Account account) throws IOException {
        return accountRepository.getAllAccountsByClientId(id).contains(account);
    }

    public Optional<Account> getMaxAccountBalance(long id) throws IOException {
        Set<Account> accountsSet = getAccounts(id);
        return accountsSet.stream().max(Comparator.comparing(Account::getBalance));
    }

    public Stream<Account> getAllAccountsByDateMoreThen(long id, LocalDate date) throws IOException {
        Set<Account> accountSet = getAccounts(id);
        return accountSet.stream().filter(account -> date.isBefore(account.getCreateDate()));
    }

    public BigDecimal getSumOfBalancesAccountsByClientId(long id) throws IOException {
        Set<Account> accountSet = getAccounts(id);
        return accountSet.stream().map(Account::getBalance).reduce(BigDecimal::add).orElse(BigDecimal.valueOf(0));
    }
}