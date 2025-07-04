package net.kegui.framework.security.enums;

public enum DeviceType {
    PC("pc"),

    APP("app"),

    MINI_PROGRAM("MiniProgram");

    private final String device;

    DeviceType(String device) {
        this.device = device;
    }
}
