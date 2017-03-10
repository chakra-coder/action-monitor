package hu.roothema.homework.model;

public class Data {

    private long id;
    private String data;

    public Data() {
    }

    public Data(String data) {
        this.data = data;
    }

    public Data(long id, String data) {
        this.id = id;
        this.data = data;
    }

    public long getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Data data1 = (Data) o;

        if (id != data1.id) return false;
        return data.equals(data1.data);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + data.hashCode();
        return result;
    }
}
