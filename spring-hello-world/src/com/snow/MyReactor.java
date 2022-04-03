package com.snow;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class MyReactor {

    public static void main(String[] args) {
        // 声明方式
        Flux.just(1, 2, 3).subscribe(System.out::print);
        Mono.just(1).subscribe(integer -> System.out.print(integer));


        Integer[] array = {10, 20, 30};
        Flux.fromArray(array);

        List<Integer> list = Arrays.asList(11, 22, 33);
        Flux.fromIterable(list);

        Stream<Integer> stream = list.stream();
        Flux.fromStream(stream);


    }
}
