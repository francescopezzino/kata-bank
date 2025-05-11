package com.katabank;

import java.math.BigDecimal;
import java.util.List;

public interface Account {
    /**
     * Deposit an amount
     * @param amount
     */
    void deposit(BigDecimal amount);

    /**
     * Withdraw an Amount
     * @param  amount
     */
    void withdraw(BigDecimal amount);

    /**
     * Get the account's current balance
     * @return the balance
     */
    BigDecimal getBalance();

    /**
     * Retrieve the history of transactions filtered by operation
     * @param operation
     * @return unmodifiable list of transaactions
     */
    List<Transaction> getHistory(AccountOperation operation);

    /**
     * Retrieve the history of transactions for the account
     * @return unmodifiable list of transaactions
     */
    List<Transaction> getHistory();
}
