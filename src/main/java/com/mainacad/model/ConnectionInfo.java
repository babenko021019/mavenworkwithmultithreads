package com.mainacad.model;

import java.io.Serializable;

public class ConnectionInfo implements Serializable {

    private Integer sessionId;
    private Long connectionTime;
    private String ip;

    @Override
    public String toString() {
        return sessionId + " " + connectionTime + " " + ip;
    }

    public ConnectionInfo(Integer sessionId, Long connectionTime, String ip) {
        this.sessionId = sessionId;
        this.connectionTime = connectionTime;
        this.ip = ip;
    }

    public ConnectionInfo() {
    }

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    public Long getConnectionTime() {
        return connectionTime;
    }

    public void setConnectionTime(Long connectionTime) {
        this.connectionTime = connectionTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
