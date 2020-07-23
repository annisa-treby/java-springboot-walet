package com.enigma.spotify.exceptions;

public class BalanceException extends RuntimeException {

    public BalanceException() {
        super("Your balance is not enough! please top-up your balance");
    }
}
