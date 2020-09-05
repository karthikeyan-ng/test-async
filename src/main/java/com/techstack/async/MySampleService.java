package com.techstack.async;

import com.google.common.base.Stopwatch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Slf4j
@RequiredArgsConstructor
@Service
public class MySampleService {

    private final RestTemplate restTemplate;

    @Async
    public Future<List<Test>> callAsync(int taskCall) throws InterruptedException {

        Stopwatch stopwatch = Stopwatch.createStarted();

        log.info("Async task " + taskCall + " starting");

        //Thread.sleep(500);

        List<Test> listOfValues = restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts", List.class);

        stopwatch.elapsed(TimeUnit.MILLISECONDS);

        log.info("Async task " + taskCall + " completed in " + stopwatch);

//        return new AsyncResult<Long>(stopwatch.elapsed(TimeUnit.MILLISECONDS));
        return new AsyncResult<List<Test>>(listOfValues);
    }

    public List<Test> callSync(int taskCall) throws InterruptedException {

        Stopwatch stopwatch = Stopwatch.createStarted();

        log.info("Sync task " + taskCall + " starting");

        //Thread.sleep(500);

        List<Test> listOfValues = restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts", List.class);

        stopwatch.elapsed(TimeUnit.MILLISECONDS);

        log.info("Sync task " + taskCall + " completed in " + stopwatch);

//        return new AsyncResult<Long>(stopwatch.elapsed(TimeUnit.MILLISECONDS));
        return listOfValues;
    }

}
