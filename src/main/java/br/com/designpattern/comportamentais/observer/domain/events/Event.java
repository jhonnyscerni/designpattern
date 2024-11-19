package br.com.designpattern.comportamentais.observer.domain.events;

public class Event {
    private String type;
    private String message;
    private Object data;

    public Event(String type, String message, Object data) {
        this.type = type;
        this.message = message;
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }
}

