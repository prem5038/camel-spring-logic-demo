package com.prem.bean;

public class ThreadInfoBean {

    long threadId;

    String threadName;

    int messageProccessed;

    public long getThreadId() {
        return threadId;
    }

    public void setThreadId(long threadId) {
        this.threadId = threadId;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public int getMessageProccessed() {
        return messageProccessed;
    }

    public void setMessageProccessed(int messageProccessed) {
        this.messageProccessed = messageProccessed;
    }
}
