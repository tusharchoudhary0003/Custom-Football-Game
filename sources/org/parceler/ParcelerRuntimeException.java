package org.parceler;

public class ParcelerRuntimeException extends RuntimeException {
    public ParcelerRuntimeException(String message) {
        super(message);
    }

    public ParcelerRuntimeException(String s, Exception e) {
        super(s, e);
    }
}
