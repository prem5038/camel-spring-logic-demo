package com.prem.bean;

import org.apache.camel.Exchange;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

public class EchoBean {

    private static Map<Long, ThreadInfoBean> threadInfoMap = new ConcurrentHashMap<>();

    public void echoBody(Exchange exchange){

        if(exchange!=null) {
            Object body = exchange.getIn().getBody();
            if(body!=null) {
                System.out.println("Runtime: " + body.getClass().getName());
                if (body.getClass().equals(String.class)){
                    System.out.println("Body: "+body.toString());
                }
                if(body.getClass().getName().contains("java.util.List")){

                    System.out.println("Body size: "+(List) body);
                }
            }
        }

    }

    public void addThreadInfo(){
        long threadId = Thread.currentThread().getId();
        String threadName = Thread.currentThread().getName();

        if(EchoBean.threadInfoMap.containsKey(threadId)){
            ThreadInfoBean threadInfoBean = EchoBean.threadInfoMap.get(threadId);
            int msgPrcd = threadInfoBean.getMessageProccessed();
            threadInfoBean.setMessageProccessed(++msgPrcd);
        } else {
            ThreadInfoBean threadInfoBean = new ThreadInfoBean();
            threadInfoBean.setThreadId(threadId);
            threadInfoBean.setThreadName(threadName);
            threadInfoBean.setMessageProccessed(1);
            EchoBean.threadInfoMap.put(threadId, threadInfoBean);
        }
    }

    public Map<Long,ThreadInfoBean> getThreadInfoMap(){
        return EchoBean.threadInfoMap;
    }


    public void echoThreadInfo(Exchange exchange){
        System.out.println("=========================================================");
        System.out.println("EXCHANGE ID: "+exchange.getExchangeId());
        System.out.println("CURRENT THREAD NAME: "+Thread.currentThread().getName());
        System.out.println("CURRENT THREAD ID: "+Thread.currentThread().getId());
        System.out.println("CURRENT THREAD STATE: "+Thread.currentThread().getState());
        System.out.println("=========================================================");
    }

    public void echoThreadInfoMap(){
        Map<Long,ThreadInfoBean> threadInfoMap = getThreadInfoMap();
        System.out.println("No. of threads used: "+threadInfoMap.size());
        threadInfoMap.entrySet().stream().forEach(es -> System.out.println("Thread ID: "+es.getKey()+", Thread Name: "+es.getValue().getThreadName()+", Messages Proccessed: "+es.getValue().getMessageProccessed()));
    }

}
