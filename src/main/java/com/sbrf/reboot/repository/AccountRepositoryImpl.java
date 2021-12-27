package com.sbrf.reboot.repository;

import com.sbrf.reboot.dto.Account;
import lombok.RequiredArgsConstructor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
public class AccountRepositoryImpl implements AccountRepository {

    private final String filePath;

    @Override
    public Set<Account> getAllAccountsByClientId(long id) {
        StringBuilder lineFromFile = readFile();
        List<String> strings = parseStringBuilder(lineFromFile);
        List<Account> accounts = createAccountList(strings);
        return createAccountSet(id, accounts);
    }

    private StringBuilder readFile() {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            System.out.printf("File reading errors: %s.\n%s", filePath, e);
        }
        return stringBuilder;
    }

    private List<String> parseStringBuilder(StringBuilder stringBuilder) {
        String[] strings = stringBuilder.toString().split("\\[|\\]|\\{|\\}");
        List<String> stringList = new ArrayList<>();
        for (String string : strings) {
            if (!string.trim().isEmpty() && !string.trim().equals(",")) {
                stringList.add(string.trim());
            }
        }
        return stringList;
    }

    private List<Account> createAccountList(List<String> stringList) {
        List<Account> accountList = new ArrayList<>();
        for (String string: stringList) {
            accountList.add(new Account(string.split(",\\s+")[1].replace("\"number\":", "").replace("\"", "").trim(),
                    Long.parseLong(string.split(",\\s+")[0].replace("\"clientId\":", "").replace("\"", "").trim())));
        }
        return accountList;
    }

    private Set<Account> createAccountSet(long id, List<Account> accountList) {
        if (accountList.isEmpty()) return null;
        Set<Account> accountSet = new HashSet<>();
        for (Account account : accountList) {
            if (account.getClientId() == id) {
                accountSet.add(account);
            }
        }
        return accountSet;
    }

}
