package hu.roothema.homework.model;

import java.time.LocalDateTime;

public class EventContext {

    private final long id;
    private final String data;
    private final String date;

    public EventContext(long id, String data) {
        this.id = id;
        this.data = data;
        this.date = LocalDateTime.now().toString();
    }

    public long getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    public String getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventContext that = (EventContext) o;

        if (id != that.id) return false;
        return data.equals(that.data);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + data.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "EventContext{" +
                "id=" + id +
                ", data='" + data + '\'' +
                '}';
    }
}
