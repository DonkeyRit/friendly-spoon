package com.github.donkeyrit.exceptions;

public class JacksonJsonParsingException extends Exception
{

    public JacksonJsonParsingException(Throwable cause) {
        super(cause);
    }

    public JacksonJsonParsingException(String message, Throwable cause) {
        super(message, cause);
    }
}
