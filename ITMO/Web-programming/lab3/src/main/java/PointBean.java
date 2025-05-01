import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ManagedBean(name = "point")
@ApplicationScoped
@NoArgsConstructor
public class PointBean {
    @Getter
    @Setter
    private double x;
    @Getter
    @Setter
    private double y;
    @Getter
    @Setter
    private double r;
    @Getter
    @Setter
    private boolean isHit;
    @Getter
    @Setter
    private long attemptTime;
    @Getter
    @Setter
    private double executionTime;
    @ManagedProperty(value = "#{table}")
    @Getter
    @Setter
    private transient DataBaseBean table;

//    @PostConstruct
//    public void init() {
//        if (r == 0) {
//            r = 1;
//        }
//    }

    public PointBean(double x, double y, double r, boolean isHit, long attemptTime, double executionTime) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.isHit = isHit;
        this.attemptTime = attemptTime;
        this.executionTime = executionTime;
    }

    public void checkHit() {
        long start = System.nanoTime();
        long attemptTime = System.currentTimeMillis();
        isHit = false;
        long executionTime = System.nanoTime() - start;
        if (table != null) {

        }
    }
}
//TODO: нахуя пустой конструктор?