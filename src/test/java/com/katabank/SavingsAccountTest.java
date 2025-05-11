package com.katabank;

import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static com.katabank.AccountOperation.*;



import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SavingsAccountTest {

    private Account account = account = new SavingsAccount(1, BigDecimal.ZERO);

    @Test
    @Order(1)
    void deposit() {
        account.deposit(BigDecimal.valueOf(100.30));
        account.deposit(BigDecimal.valueOf(50));
        assertEquals("150.30", account.getBalance().setScale(2, RoundingMode.HALF_EVEN).toPlainString());
    }

    @Test
    @Order(2)
    void withdraw() {
        account.withdraw(BigDecimal.valueOf(30));
        assertEquals("120.30", account.getBalance().setScale(2, RoundingMode.HALF_EVEN).toPlainString());
    }

    @Test
    @Order(3)
    void withdrawNotEnoughFunds() {
        account.withdraw(BigDecimal.valueOf(300));
        assertEquals("120.30", account.getBalance().setScale(2, RoundingMode.HALF_EVEN).toPlainString());
    }

    @Test
    @Order(4)
    void getHistory() {
        List<Transaction> historyList = account.getHistory();
        assertEquals(4, historyList.size());
        historyList.forEach(System.out::println);
    }

    @Test
    @Order(5)
    void getHistoryWithdraw(){
        List<Transaction> historyWithdrawList = account.getHistory(WITHDRAW);
        assertEquals(1, historyWithdrawList.size());
        account.getHistory(WITHDRAW).forEach(System.out::println);
    }

    @Test
    @Order(6)
    void getHistoryDeposit(){
        List<Transaction> historyDepositList = account.getHistory(DEPOSIT);
        assertEquals(2, historyDepositList.size());
        account.getHistory(DEPOSIT).forEach(System.out::println);
        assertEquals("120.30", account.getBalance().setScale(2).toPlainString());
    }

    @Test
    @Order(7)
    void getBalance() {
        assertEquals("120.30", account.getBalance().setScale(2).toPlainString());
    }
}