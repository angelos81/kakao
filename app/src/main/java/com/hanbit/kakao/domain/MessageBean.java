package com.hanbit.kakao.domain;

/**
 * Created by hb2009 on 2017-01-07.
 */

public class MessageBean {
    private int seq;
    private String sender, receiver, content, writeTime;


    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriteTime() {
        return writeTime;
    }

    public void setWriteTime(String writeTime) {
        this.writeTime = writeTime;
    }

    @Override
    public String toString() {
        return "MessageBean{" +
                "seq=" + seq +
                ", sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", content='" + content + '\'' +
                ", writeTime='" + writeTime + '\'' +
                '}';
    }
}
