public interface Problem {
    double fit(double a); //함수 f(a)에서 x의 역할을 할 a 설정.
    boolean isNeighborBetter(double f0, double f1); // 기존 함수 값과 새로 나온 함수 값을 비교해주기 위한 함수.
}
