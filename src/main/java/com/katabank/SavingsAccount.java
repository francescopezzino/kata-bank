package com.katabank;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import static com.katabank.AccountOperation.*;

public class SavingsAccount implements Account {

    private final Integer accntNumber;
    private BigDecimal balance;
    private final List<Transaction> transactions = new ArrayList<>();

    public SavingsAccount(Integer accntNumber, BigDecimal balance) {
        this.accntNumber = accntNumber;
        this.balance = balance;
    }

    @Override
    public void deposit(BigDecimal amount) {
        if (null != amount && amount.intValue() > 0) {
            balance = balance.add(amount);
            transactions.add(new Transaction(DEPOSIT, LocalDateTime.now(), amount, balance));
        }
    }

    @Override
    public void withdraw(BigDecimal amount) {
        if (null != amount && balance.subtract(amount).intValue() < 0) {
            transactions.add(new Transaction(NOT_ENOUGH_FUNDS, LocalDateTime.now(), amount, balance));
        } else {
            balance = balance.subtract(amount);
            transactions.add(new Transaction(WITHDRAW, LocalDateTime.now(), amount, balance));
        }
    }

    @Override
    public BigDecimal getBalance() {
        return balance;
    }

    @Override
    public List<Transaction> getHistory(AccountOperation operation) {
        return transactions.stream()
                .filter(op -> op.operation() == operation)
                .toList();
    }

    @Override
    public List<Transaction> getHistory() {
        return Collections.unmodifiableList(transactions);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SavingsAccount that = (SavingsAccount) o;
        return Objects.equals(accntNumber, that.accntNumber) && Objects.equals(balance, that.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accntNumber, balance);
    }
}
