package hu.roothema.homework.repository.entity;

import javax.persistence.*;

@Entity
@Table(name = "data")
public class DataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String data;

    protected DataEntity() {
    }

    public DataEntity(Long id, String data) {
        this.id = id;
        this.data = data;
    }

    public DataEntity(String data) {
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DataEntity that = (DataEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return data.equals(that.data);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + data.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "DataEntity{" +
                "id=" + id +
                ", data='" + data + '\'' +
                '}';
    }
}
