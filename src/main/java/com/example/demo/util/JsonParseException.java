package com.example.demo.util;

import java.io.IOException;

public class JsonParseException extends RuntimeException {

    public JsonParseException(IOException ex) {
        super(ex);
    }
}
