package com.snow.webflux.function;

import com.alibaba.fastjson.support.hsf.HSFJSONUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class FunctionTest {

    @Test
    public void test1() {
        // Imperative programming
        Integer[] nums = {10, 20, 7, 30};
        Integer min = Integer.MAX_VALUE;
        for (Integer integer : nums) {
            if (integer < min) {
                min = integer;
            }
        }
        System.out.println("min value is " + min);
        // min value is 7
    }

    @Test
    public void test2() {
        GreetingInterface greetingInterface =
                message -> System.out.println("Hello " + message);
        greetingInterface.sayMessage("World");
        greetingInterface.greet();
        // Hello World
        // Wonderful!

        Function<Integer, Integer> function = x -> x * 2;
        System.out.println(function.apply(5));
        // 10
    }

    @Test
    public void test3() {
        // Method Reference
        // 1. 方法引用
        // Consumer<String> consumer = s -> System.out.println(s);
        Consumer<String> consumer = System.out::println;
        consumer.accept("Apple");
        // Apple

        // 2. 静态方法引用
        Consumer<Cat> staticConsumer = Cat::bark;
        Cat cat = new Cat();
        staticConsumer.accept(cat);
        // Totoro meow

        // 3.1. 非静态方法引用，使用对象实例
        // Function<Integer, Integer> function = cat::eat;
        UnaryOperator<Integer> function = cat::eat;
        function.apply(2);
        // eat 2 fish

        // 3.2. 非静态方法引用，使用类名
        BiFunction<Cat, Integer, Integer> eatFunction = Cat::eat;
        eatFunction.apply(cat, 3);
        // eat 3 fish

        // 4.1 构造函数的方法引用
        Supplier<Cat> supplier = Cat::new;
        System.out.println("another cat: " + supplier.get());
        // another cat: Totoro

        // 4.2 带参数的构造函数的方法引用
        Function<String, Cat> catFunction = Cat::new;
        System.out.println("new cat: " + catFunction.apply("Black Cat"));
        // new cat: Black Cat
    }

    @Test
    public void test4() {
        List<String> strings = Arrays.asList("apple", "", "banana", "orange", "", "peach");
        List<String> filtered = strings
                .stream()
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
        System.out.println("filter list: " + filtered);
        // filter list: [apple, banana, orange, peach]
    }

    @Test
    public void test5() {
        // 1. 定义发布者，发布数据类型为Integer
        SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>();

        // 2. 定义订阅者
        Flow.Subscriber<Integer> subscriber = new Flow.Subscriber<Integer>() {
            private Flow.Subscription subscription;

            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                // 保存订阅关系
                this.subscription = subscription;
                // 请求一个数据
                this.subscription.request(1);
            }

            @Override
            public void onNext(Integer item) {
                // 接受到数据，处理
                System.out.println("receive data: " + item);
                // 处理完再请求一个数据
                this.subscription.request(1);
                // // 或者不再接受
                // this.subscription.cancel();
            }

            @Override
            public void onError(Throwable throwable) {
                // 出现异常，可以不接受数据
                throwable.printStackTrace();
                this.subscription.cancel();
            }

            @Override
            public void onComplete() {
                // 数据处理完（发布者关闭）
                System.out.println("done");
            }
        };

        // 3. 发布者与订阅者建立订阅关系（神鬼契约）
        publisher.subscribe(subscriber);

        // 4. 生产数据，发布者发布
        for (int index = 100; index <= 110; index++) {
            publisher.submit(index);
        }

        // 5. 结束，发布者关闭
        publisher.close();

        // 主线程延迟停止，防止数据没有消费就退出
        try {
            Thread.currentThread().join(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // receive data: 100
        // receive data: 101
        // receive data: 102
        // receive data: 103
        // receive data: 104
        // receive data: 105
        // receive data: 106
        // receive data: 107
        // receive data: 108
        //receive data: 109
        // receive data: 110
        // done
    }

    @Test
    public void test6() {
        Flux.just(1, 2, 3).subscribe(System.out::print);
        Mono.just(1).subscribe(System.out::print);

        Integer[] array = {10, 20, 30};
        Flux.fromArray(array);

        List<Integer> list = Arrays.asList(11, 22, 33);
        Flux.fromIterable(list);

        Stream<Integer> stream = list.stream();
        Flux.fromStream(stream);

        Mono<String> mono = Mono.just("123.com");
        String[] receivers = {"tom@", "null", "jerry"};
        Flux.fromArray(receivers)
                .filter(receiver -> !receiver.equals("null"))
                .flatMap(receiver -> receiver.contains("@")
                        ? Mono.just(receiver) : Mono.just(receiver + "@"))
                .collectList()
                .zipWith(mono)
                .map(tupe2 -> {
                    List<String> receiverList = tupe2.getT1();
                    String com = tupe2.getT2();
                    receiverList = receiverList
                            .stream()
                            .map(recv -> recv + com)
                            .collect(Collectors.toList());
                    return receiverList;
                }).subscribe(System.out::println);
    }

    @Test
    public void test7() {
// 1. Static Fallback Value
// 在遇到异常的时候会fallback到一个静态的默认值
// 当Publish时，指定异常处理模式
Flux<?> flux= Flux.just(1, 2, 0)
        .map(i -> "100 / " + i + " = " + (100 / i))
        .onErrorReturn("Divided by zero :(");
flux.subscribe(System.out::println);

// 当subscribe时，指定异常处理模式
Flux<?> flux1= Flux.just(1, 2, 0)
        .map(i -> "100 / " + i + " = " + (100 / i));
flux1.subscribe(System.out::println,
        error -> System.err.println("Error: " + error));

try{
    Stream.of(1, 2, 0)
            .map(i -> "100 / " + i + " = " + (100 / i))
            .forEach(System.out::println);
}catch (Exception e){
    System.err.println("Error: " + e);
}

    }

    @Test
    public void test8() {
// 2. Fallback Method
// 在捕获异常之后可以调用其它的方法
Flux<?> flux2 = Flux.just(1, 2, 0)
        .map(i -> "100 / " + i + " = " + (100 / i))
        .onErrorResume(e -> System.out::println);
flux2.subscribe(System.out::println);

// 3. Dynamic Fallback Value
// 根据抛出的异常进行判断，通过定位不同的Error从而Fallback不同的值
Flux<?> flux3 = Flux.just(1, 2, 0)
        .map(i -> "100 / " + i + " = " + (100 / i))
        .onErrorResume(error -> Mono.just(
                MyWrapper.fromError(error)));
flux3.subscribe(System.out::println);

// 4. Catch and Rethrow
// 4.1 在onErrorResume中使用Flux.error构建一个新的Flux
Flux<?> flux41 = Flux.just(1, 2, 0)
        .map(i -> "100 / " + i + " = " + (100 / i))
        .onErrorResume(error -> Flux.error(
                new RuntimeException("Oops, ArithmeticException!", error)));
flux41.subscribe(System.out::println);

// 4.2 直接在onErrorMap中进行处理
Flux<?> flux42 = Flux.just(1, 2, 0)
        .map(i -> "100 / " + i + " = " + (100 / i))
        .onErrorMap(error -> new RuntimeException("Oops, ArithmeticException!", error));

flux42.subscribe(System.out::println);
// 5. Log or React on the Side
// 记录异常信息，而不破坏原来的结构
Flux<?> flux5 = Flux.just(1, 2, 0)
        .map(i -> "100 / " + i + " = " + (100 / i))
        .doOnError(error -> System.out.println("We got the error: "+ error));
flux5.subscribe(System.out::println);


    }

    @Test
    public void test_publish() {
//        当Publish时，指定异常处理模式
        Flux<?> flux = Flux.just(1, 20, 0, 10)
                .map(i -> "100 / " + i + " = " + (100 / i))
                .flatMap(x -> {
                    if (x.contains("20")) {
                        System.out.println("error: " + x + " is 20! ");
                        return Mono.error(new Throwable("error: " + x + " is 20! "));
                    }
                    return Mono.just(x);
                })
//                .map(x -> {
//                    if (x.contains("20")) {
//                        System.out.println("error: " + x + " is 20! ");
//                        return new Throwable("error: " + x + " is 20! ");
//                    }
//                    return x;
//                })
             .onErrorResume(throwable -> Mono.just(throwable.getMessage()));
//                .onErrorReturn(Throwable.class, " info +++ ");
        flux.subscribe(System.out::println);
        //100 / 1 = 100
        //error: 100 / 20 = 5 is 20!
        //error: 100 / 20 = 5 is 20!

    }
    @Test
    public void test_subscribe() {
        // 当subscribe时，指定异常处理模式
        Flux<?> flux1= Flux.just(1, 20, 0, 10)
                .map(i -> "100 / " + i + " = " + (100 / i))
                .flatMap(x -> {
                    if (x.contains("20")) {
                        // log.error
                        //System.out.println("log.error: error: " + x + " is 20! ");
                        // post api
                        //System.out.println("POST error:api/tools...");
                        // return Mono.error
                        return Mono.error(new Throwable("error: " + x + " is 20! "));
                    }
                    return Mono.just(x);
                });
        flux1.subscribe(System.out::println,
                error -> {
                    error.printStackTrace();

                    System.out.println("log.error: subscribe - error: " + error);
                    System.out.println("POST error: subscribe - api/tools");

                    System.out.println("error");
                    System.out.println(error);
                    System.out.println("error.getMessage()");
                    System.out.println(error.getMessage());
                    System.out.println("error.getCause().toString()");
                    System.out.println(error.getCause());
                    System.out.println("error.getLocalizedMessage()");
                    System.out.println(error.getLocalizedMessage());
                    System.out.println("Arrays.toString(error.getSuppressed())");
                    System.out.println(Arrays.toString(error.getSuppressed()));
                    System.out.println("Arrays.toString(error.getStackTrace())");
                    System.out.println(Arrays.toString(error.getStackTrace())); // 2.
                    System.out.println("error.toString()");
                    System.out.println(error.toString());   // 1.

                    System.out.println(error.fillInStackTrace().toString());
                });
        //100 / 1 = 100
        //error: 100 / 20 = 5 is 20!
        //java.lang.Throwable: error: 100 / 20 = 5 is 20!
    }
}

