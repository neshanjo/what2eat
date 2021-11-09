package de.schneider21.what2eat.meal.data;

import java.util.Objects;

public class DataWithTime<E> {

    private final E data;
    private final long time;

    public DataWithTime(E data, long time) {
        this.data = data;
        this.time = time;
    }

    public E getData() {
        return data;
    }

    public long getTime() {
        return time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataWithTime<?> that = (DataWithTime<?>) o;
        return time == that.time && Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, time);
    }

    @Override
    public String toString() {
        return "DataWithTime{" +
                "data=" + data +
                ", time=" + time +
                '}';
    }
}
