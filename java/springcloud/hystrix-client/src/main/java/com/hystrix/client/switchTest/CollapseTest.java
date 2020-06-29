package com.hystrix.client.switchTest;

import com.netflix.config.ConfigurationManager;
import com.netflix.hystrix.HystrixCollapser;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

import java.util.*;
import java.util.concurrent.Future;

//合并请求
public class CollapseTest {
    public static void main(String[] args) throws Exception {
        //收集1秒内发生的请求，合并为一个命令执行
        ConfigurationManager.getConfigInstance().setProperty(
                "hystrix.collapser.default.timerDelayInMilliseconds", 1000
        );
        ConfigurationManager.getConfigInstance().setProperty(
                "hystrix.collapser.default.execution.timeout.enabled", false
        );
        //请求上下文
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        //创建请求合并处理器
        MyHystrixCollapser collapser1 = new MyHystrixCollapser("Angus");
        MyHystrixCollapser collapser2 = new MyHystrixCollapser("Crazyit");
        MyHystrixCollapser collapser3 = new MyHystrixCollapser("Sune");
        MyHystrixCollapser collapser4 = new MyHystrixCollapser("Paris");
        //异步执行
        Future<Person> future1 = collapser1.queue();
        Future<Person> future2 = collapser2.queue();
        Future<Person> future3 = collapser3.queue();
        Future<Person> future4 = collapser4.queue();
        System.out.println(future1.get());
        System.out.println(future2.get());
        System.out.println(future3.get());
        System.out.println(future4.get());

        context.shutdown();

    }

    static class Person{
        String id, name;
        Integer age;

        public String toString(){
            return "id: " + id + ", name: " + name + ", age: " + age;
        }
    }

    //需要有一个执行请求命令，将全部参数整理，然后调用外部服务
    static class CollapserCommand extends HystrixCommand<Map<String, Person>>{
        //请求集合，第一个类型是单个请求返回的数据类型，第二个请求参数的类型
        Collection<HystrixCollapser.CollapsedRequest<Person, String>> requests;

        private  CollapserCommand(
                Collection<HystrixCollapser.CollapsedRequest<Person, String>> requests){
            super(Setter.withGroupKey(HystrixCommandGroupKey.Factory
                    .asKey("ExampeGroup")));
            this.requests = requests;
        }

        @Override
        protected Map<String, Person> run() throws Exception {
            System.out.println("收集参数后执行命令，参数数量：" + requests.size());
            //处理参数
            List<String> personNames = new ArrayList<String>();
            for (HystrixCollapser.CollapsedRequest<Person, String> request : requests){
                personNames.add(request.getArgument());
            }
            Map<String, Person> result = callService(personNames);
            return result;
        }

        //模拟服务返回
        private Map<String, Person> callService(List<String> personNames){
            Map<String, Person> result = new HashMap<String, Person>();
            for (String personName : personNames){
                Person person = new Person();
                person.id = UUID.randomUUID().toString();
                person.name = personName;
                person.age = new Random().nextInt(30);
                result.put(personName, person);
            }
            return result;
        }
    }

    //需要有一个合并处理器，用于收集请求，以及处理结果
    /**
     * 合并处理器
     * 第一个类型为批处理返回的结果类型
     * 第二个为单请求返回的结果类型
     * 第三个请求参数类型
     */
    static class MyHystrixCollapser extends HystrixCollapser<Map<String, Person>, Person, String>{
        String personName;
        public MyHystrixCollapser(String personName){
            this.personName = personName;
        }

        //收集请求和请求的参数
        @Override
        public String getRequestArgument() {
            return personName;
        }

        //利用命令对象完成命令
        @Override
        protected HystrixCommand<Map<String, Person>> createCommand(Collection<CollapsedRequest<Person, String>> collection) {
            return new CollapserCommand(collection);
        }

        @Override
        protected void mapResponseToRequests(Map<String, Person> stringPersonMap, Collection<CollapsedRequest<Person, String>> collection) {
            //让请求与结果关联
            for (CollapsedRequest<Person, String> request : collection){
                //获取单个响应返回的结果
                Person personResult = stringPersonMap.get(request.getArgument());
                request.setResponse(personResult);
            }
        }
    }

}
