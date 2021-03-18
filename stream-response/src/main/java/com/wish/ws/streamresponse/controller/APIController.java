package com.wish.ws.streamresponse.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("/api")
public class APIController {

    private ExecutorService executor
            = Executors.newCachedThreadPool();

    @GetMapping("/rbe")
    public ResponseEntity<ResponseBodyEmitter> handleRbe() {
        ResponseBodyEmitter emitter = new ResponseBodyEmitter();
        executor.execute(() -> {
            try {

                for (int i = 1; i <= 1000; i++) {
                    try {
                        Thread.sleep(10);
                        emitter.send(
                                i+" /rbe" + " @ " + new Date()+"\n", MediaType.TEXT_PLAIN);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


                emitter.complete();
            } catch (Exception ex) {
                emitter.completeWithError(ex);
            }
        });
        return new ResponseEntity(emitter, HttpStatus.OK);
    }

    @GetMapping(value="/data")
    public ResponseEntity<StreamingResponseBody> streamData() {
        StreamingResponseBody responseBody = response -> {
            for (int i = 1; i <= 1000; i++) {
                try {
                    Thread.sleep(10);
                    response.write(("Data stream line - " + i + "\n").getBytes());
                    response.flush();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(responseBody);
    }




}
