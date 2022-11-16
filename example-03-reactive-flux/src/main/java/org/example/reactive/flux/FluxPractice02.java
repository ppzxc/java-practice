package org.example.reactive.flux;

import java.time.Duration;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class FluxPractice02 {

  public static void main(String[] args) {
    Flux<Integer> firstPublisher = Flux.fromIterable(IntStream.range(1, 100).boxed().toList())
        .log()
        .delayElements(Duration.ofMillis(17));

    Flux<Integer> secondPublisher = Flux.fromIterable(IntStream.range(1, 100).boxed().toList())
        .log()
        .delayElements(Duration.ofMillis(38));

    Flux<Integer> thirdPublisher = Flux.fromIterable(IntStream.range(1, 100).boxed().toList())
        .log()
        .delayElements(Duration.ofMillis(54));

    Flux.zip(firstPublisher, secondPublisher, thirdPublisher)
        .log()
        .doOnNext(tuple3 -> log.info("################### {}", tuple3))
        .blockLast();
  }

}
