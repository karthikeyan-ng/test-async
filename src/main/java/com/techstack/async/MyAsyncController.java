package com.techstack.async;

import com.google.common.base.Stopwatch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MyAsyncController {

    private final MySampleService mySampleService;

    @GetMapping(value="/", produces = { MediaType.TEXT_HTML_VALUE })
    public String taskExecutor() throws InterruptedException, ExecutionException {

        Stopwatch stopwatch = Stopwatch.createStarted();

        Future<List<Test>> asyncResult1 = mySampleService.callAsync(1);
        Future<List<Test>> asyncResult2 = mySampleService.callAsync(2);
        Future<List<Test>> asyncResult3 = mySampleService.callAsync(3);
        Future<List<Test>> asyncResult4 = mySampleService.callAsync(4);


        /******/
//        List<Future<Long>> listOfFutures = List.of(asyncResult1, asyncResult2, asyncResult3, asyncResult4);

        //For each Future ==>

        //=======



        List<Future<List<Test>>> listOfFutures = List.of(asyncResult1, asyncResult2, asyncResult3, asyncResult4);

        for(Future<List<Test>> future : listOfFutures) {
            log.info("result of took: " + future.get().size());
        }

//        log.info("result 1 took: " + asyncResult1.get());
//        log.info("result 2 took: " + asyncResult2.get());
//        log.info("result 3 took: " + asyncResult3.get());
//        log.info("result 4 took: " + asyncResult4.get());

        stopwatch.elapsed(TimeUnit.MILLISECONDS);

        return "time it took to perform work " + stopwatch;
    }

    @GetMapping(value="/sync", produces = { MediaType.TEXT_HTML_VALUE })
    public String taskExecutor1() throws InterruptedException, ExecutionException {

        Stopwatch stopwatch = Stopwatch.createStarted();

        mySampleService.callSync(1);
        mySampleService.callSync(1);
        mySampleService.callSync(1);
        mySampleService.callSync(1);

//        Future<List<Test>> asyncResult1 = mySampleService.callAsync(1);
//        Future<List<Test>> asyncResult2 = mySampleService.callAsync(2);
//        Future<List<Test>> asyncResult3 = mySampleService.callAsync(3);
//        Future<List<Test>> asyncResult4 = mySampleService.callAsync(4);
//        List<Future<List<Test>>> listOfFutures = List.of(asyncResult1, asyncResult2, asyncResult3, asyncResult4);

//        for(Future<List<Test>> future : listOfFutures) {
//            log.info("result of took: " + future.get().size());
//        }

//        log.info("result 1 took: " + asyncResult1.get());
//        log.info("result 2 took: " + asyncResult2.get());
//        log.info("result 3 took: " + asyncResult3.get());
//        log.info("result 4 took: " + asyncResult4.get());

        stopwatch.elapsed(TimeUnit.MILLISECONDS);

        return "time it took to perform work " + stopwatch;
    }
}
