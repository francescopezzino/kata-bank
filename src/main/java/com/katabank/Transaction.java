package com.katabank;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Transaction (AccountOperation operation, LocalDateTime date, BigDecimal amount, BigDecimal balance) {}
