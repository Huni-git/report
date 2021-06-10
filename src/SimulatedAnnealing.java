
import java.util.ArrayList;
import java.util.Random;

public class SimulatedAnnealing {
    private double t;
    private double a;
    private int niter;
    //이 클래스 내에서 계속 사용할 변수 지정.
    public ArrayList<Double> hist;
    public ArrayList<Double> hista;

    public SimulatedAnnealing(double t, double a, int niter) {

        this.niter = niter;
        this.a=a;
        this.t=t;
        hist = new ArrayList<>();//함수 값을 볼 것이다. -> 최적해를 찾아가고 있는 지 판단.
        hista = new ArrayList<>();//a 즉 x의 변화량을 볼 것이다.

    }

    public double solve(Problem p, double lower, double upper) {
        Random r = new Random(); //random 변수를 입력받는다.
        double value = r.nextDouble() * (upper - lower) + lower;
        double f0 = p.fit(value);
        hist.add(f0);
        hista.add(value);


        for(int i=0; i<niter; i++) {    // Loop 일정 조건까지 반복.
            int kt = (int) Math.round(t * 10);
            // 여기서 k가 매우 중요한 역할을 하게 되는데, Step size를 결정하는 역할이다.
            for(int j=0; j<kt; j++) {
                double a0 = r.nextDouble() * (upper - lower) + lower;
                double f1 = p.fit(a0);
                if(p.isNeighborBetter(f0, f1)) {    // 이웃해, 즉 다른 후보해가 더 좋은 해이기 때문에 그 값을 저장하자.
                    value = a0;
                    f0 = f1;
                    hist.add(f0);
                    hista.add(value);

                } else {    // 기존해 즉 원래 가지고 있던 기존해가 더 좋은 해를 가짐으로 기존해를 갖자.
                    double d = f1 - f0;
                    double p0 = Math.exp(-d/t);
                    if(r.nextDouble() < p0) {
                        value = a0;
                        f0 = f1;
                        hist.add(f0);
                        hista.add(value);

                    }
                }
            }
            t *= a;
        }
        return value;
    }
}