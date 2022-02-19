package com.sbrf.reboot.repository;

import com.sbrf.reboot.dto.Account;
import lombok.RequiredArgsConstructor;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
public class JsonAccountRepository implements AccountRepository {

    private final String pathToFile;

    private static final String CLIENT = "clientId";
    private static final String NUMBER = "number";
    private static final String REGEX = "\\s*\\{\\s*\"clientId\": (?<" + CLIENT + ">\\d+),\\s*\"number\": \"(?<" + NUMBER + ">\\S+)\"\\s*}";

    private static final Pattern PATTERN = Pattern.compile(REGEX);

    @Override
    public Set<Account> getAllAccountsByClientId(long id) throws IOException {
        String fileContents = readFile();
        return parseJsonFile(fileContents, id);
    }

    public boolean updateClientNumber(long id, String currentNumber, String updateNumber) throws IOException {
        String fileContents = readFile();
        StringBuilder sb = new StringBuilder(fileContents);
        Matcher matcher = PATTERN.matcher(fileContents);
        while (matcher.find()) {
            if (matcher.group(CLIENT).equals(Long.toString(id)) && matcher.group(NUMBER).equals(currentNumber)) {
                int start = matcher.start(NUMBER);
                int end = matcher.end(NUMBER);
                sb.replace(start, end, updateNumber);
                writeFile(sb.toString());
                return true;
            }
        }
        return false;
    }

    private Set<Account> parseJsonFile(String fileContents, long id) {
        Set<Account> accounts = new HashSet<>();
        Matcher matcher = PATTERN.matcher(fileContents);
        while (matcher.find()) {
            long clientId;
            String number;
            if ((clientId = Long.parseLong(matcher.group(CLIENT))) == id) {
                number = matcher.group(NUMBER);
                accounts.add(new Account(clientId, number));
            }
        }
        return accounts;
    }

    private String readFile() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(pathToFile)))) {
            int symbol;
            while ((symbol = bufferedReader.read()) != -1) {
                stringBuilder.append((char) symbol);
            }
        }
        return stringBuilder.toString();
    }

    private void writeFile(String fileContents) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pathToFile)))) {
            bufferedWriter.write(fileContents);
        }
    }
}