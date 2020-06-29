package com.hystrix.client;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import rx.Observable;
import rx.Observer;

public class RunTest {
    public static void main(String[] args) throws InterruptedException {
        //使用execute方法,以同步堵塞方式执行run()，只支持接收一个值对象。
        RunCommand command1 = new RunCommand("使用execute方法执行命令");
        command1.execute();

        //以异步非阻塞方式执行run()，只支持接收一个值对象。
        RunCommand command2 = new RunCommand("使用queue方法执行命令");
        command2.queue();

        //事件注册前执行run()/construct()，支持接收多个值对象，取决于发射源。
        // 调用observe()会返回一个hot Observable，
        // 也就是说，调用observe()自动触发执行run()/construct()，无论是否存在订阅者。
        RunCommand command3 = new RunCommand("使用observer方法执行命令");
        command3.observe();

        //使用toObservable
        RunCommand command4 = new RunCommand("使用observable方法执行命令");
        //toObservable()不会立即触发执行run()/construct()，必须有订阅者订阅Observable时才会执行。
        Observable<String> observable = command4.toObservable();
        observable.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("=========命令执行完成============");
            }

            @Override
            public void onError(Throwable throwable) {
            }

            @Override
            public void onNext(String s) {
                System.out.println("=======命令执行结果：=======" + s);
            }
        });
        Thread.sleep(100);
    }

    //测试命令
    static class RunCommand extends HystrixCommand<String>{
        String msg;

        public RunCommand(String msg){
            super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
            this.msg = msg;
        }

        @Override
        protected String run() throws Exception {
            System.out.println(msg);
            return "success";
        }
    }

}
