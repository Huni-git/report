public class Main {

    public static void main(String[] args) {

        SimulatedAnnealing sa = new SimulatedAnnealing(1, 0.97, 100);
        sa.solve(new Problem() {
            @Override
            public double fit(double a) {

                return 2*a*a*a-9*a*a+12*a+1 ;
                //f'(x)=6a^3-18a+12=0
                //a=2에서 극소값을 갖는다. 다만 이는 최적해는 아니고 지역 최적해이다.
            }

            @Override
            public boolean isNeighborBetter(double f0, double f1) {
                return f1 > f0;
            }
        }, 1, 20);

        System.out.println(sa.hist);
        System.out.println(sa.hista);


    }
}