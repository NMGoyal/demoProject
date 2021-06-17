package org.tutorialsNinja.GeneralExceptions;

public class GeneralExceptions extends Exception {

    public GeneralExceptions() {
        super();
    }

    public GeneralExceptions(String message) {
        super(message);
    }

    public GeneralExceptions(String message, Throwable error) {
        super(message, error);
    }
}
