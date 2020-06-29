package com.hystrix.client.test;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

import java.util.Iterator;

//利用HystrixObservableCommand代替HystrixCommand重构实验1的代码
public class CommandHelloWorld01 extends HystrixObservableCommand<String> {
    private  final String name;

    public CommandHelloWorld01(String name){
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.name = name;
    }

    @SuppressWarnings("deprecation")
    @Override
    protected Observable<String> construct() {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    if (!subscriber.isUnsubscribed()){
                        subscriber.onNext("Hello " + name + "! Thread: " + Thread.currentThread().getName());
                        subscriber.onCompleted();
                    }
                }catch (Exception e){
                    subscriber.onError(e);
                }
            }
        }).subscribeOn(Schedulers.io());
    }


    public static void main(String[] args) {
        //Observable<String> observable = new CommandHelloWorld01("Asynchronous_hystrix").observe();

        //必须有订阅者才能执行
        Observable<String> observable = new CommandHelloWorld01("Asynchronous_hystrix").toObservable();

        Iterator<String> iterator = observable.toBlocking().getIterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
