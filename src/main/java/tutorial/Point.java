package tutorial;

import javax.persistence.*;
import java.io.Serializable;


@Entity
public class Point implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue
    private long id;

    private int x;
    private int y;

    public Point() {
    }

    Point(long id,int x, int y) {
        this.id= id;
        this.x = x;
        this.y = y;
    }

    public Long getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return String.format("id:%d -> (x:%d, y:%d)",this.id, this.x, this.y);
    }
}
