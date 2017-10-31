package io.github.devas;

class Configuration {

    private int listeningPort;

    private String userName;
    private String targetIp;
    private int targetPort;

    Configuration(int listeningPort, String userName, String targetIp, int targetPort) {
        this.listeningPort = listeningPort;
        this.userName = userName;
        this.targetIp = targetIp;
        this.targetPort = targetPort;
    }

    int getListeningPort() {
        return listeningPort;
    }

    void setListeningPort(int listeningPort) {
        this.listeningPort = listeningPort;
    }

    String getUserName() {
        return userName;
    }

    void setUserName(String userName) {
        this.userName = userName;
    }

    String getTargetIp() {
        return targetIp;
    }

    void setTargetIp(String targetIp) {
        this.targetIp = targetIp;
    }

    int getTargetPort() {
        return targetPort;
    }

    void setTargetPort(int targetPort) {
        this.targetPort = targetPort;
    }
}
