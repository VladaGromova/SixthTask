package com.epam.tsk.sixth;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private final static String FILENAME = "src/main/resources/input.json";
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        Callers callersWrapper = mapper.readValue(new File(FILENAME), Callers.class);
        List<Caller> callers = callersWrapper.getCallers();

        ExecutorService executor = Executors.newFixedThreadPool(callers.size());
        callers.forEach(caller -> executor.submit(caller));
        executor.shutdown();
    }
}
