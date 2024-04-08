package com.example.backend.http;

import java.io.IOException;
import org.json.simple.parser.ParseException;

public interface HttpClient {
    
    public String doHttpGet(String url) throws IOException, ParseException;
    
}