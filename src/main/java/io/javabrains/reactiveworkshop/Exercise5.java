package io.javabrains.reactiveworkshop;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;

import java.io.IOException;

public class Exercise5 {

    public static void main(String[] args) throws IOException {


        // Subscribe to a flux using an implementation of BaseSubscriber
        // TODO: Write code here
        ReactiveSources.userFlux().subscribe(new MySubscriber<>());


        // Use ReactiveSources.intNumberFlux() and ReactiveSources.userMono()

        // Subscribe to a flux using the error and completion hooks
        // TODO: Write code here
        ReactiveSources.intNumbersFlux().subscribe(
                number -> System.out.println(number),
                error -> System.out.println(error.getMessage()),
                () -> System.out.println("intNumberFlux Completed")
        );

        ReactiveSources.userMono().subscribe(
                user -> System.out.println(user),
                error -> System.out.println(error.getMessage()),
                () -> System.out.println("userMono Completed")
        );

        System.out.println("Press a key to end");
        System.in.read();
    }

}

class MySubscriber<T> extends BaseSubscriber<T> {
    public void hookOnSubscribe(Subscription subscription) {
        System.out.println("Subscription Happend");
        //give me n items when it is ready
        request(1);
    }

    public void hookOnNext(T value) {
        System.out.println("Received value = " + value);
        //request for sending n more number of items
        request(3);
    }
}