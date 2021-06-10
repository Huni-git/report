# report
# SimulatedAnnealing
## 모의담금질 기법
### 1. 모의담금질이란?
모의 담금질(Simulated Annealing)기법은 높은 온도에서 액체 상태인 물질이 온도가 점차 낮아지면서 결정체로 변하는 과정을 모방한 해 탐색 알고리즘이다. 용융상태에서는 물질의 분자가 자유로이 움직이는데, 이를 모방하여 해 탐색하는 과정도 특정한 패턴이 없이 이루어진다. 이 기법은 전역 최적화 문제에 대한 일반적인 확률적 메타 알고리즘이며, 이 기법은 광대한 탐색 공간 안에서, 주어진 함수의 전역 최적해에 대한 좋은 근사를 준다. 커크패트릭, 젤라트, 베키가 1983년에 고안했다. ~~왜만든걸까...ㅡㅡ~~ 담금질 기법이라는 말은 금속 공학의 담금질로 부터 왔다.
SA알고리즘은 해를 반복해 개선함으로써, 현재의 해 근방에 있는 해를 임의로 찾는데, 그때에 주어진 함수의 값과 전역 인자 T 가 영향을 준다. 그리고 앞에서 기술한 물리 과정과 비슷한 원리로. T(온도)의 값은 서서히 작아진다. 따라서, 처음에는 T가 크기 때문에 해가 크게 변화하지만, T가 0에 가까워짐에 따라 변화가 줄어든다. 처음은 간단하게 비탈을 올라갈 수 있으므로, 등산법으로 문제가 되는 지역 최적점에 빠졌을 때의 대책을 생각할 필요가 없다. ~~위키백과+책을 참고~~

![제목 없음](https://user-images.githubusercontent.com/80510945/121544472-b77de480-ca44-11eb-9d70-511dcdbfea4f.jpg)

![지역 최소값에서의 탈출](https://user-images.githubusercontent.com/80510945/121544097-6bcb3b00-ca44-11eb-8df4-d04e4343ad59.gif)

### 2.모의 담금질의 의사코드와 구현까지.

우선 돌다리를 생각해보자. 돌이 10가 있고, 가장 효율적으로 배치하려 할때, 가장 효율적인 방법으로 배치한 값이 담금질 기법의 최적화 값이 될 것이다. 만약 효율적으로 배치 하지 않는다면, 돌다리 사이의 거리가 멀어져 다리를 건널 수 없는 상황이 생길 수 있다. 10개의 돌을 배치를 할 때, 발생할 경우의 수가 10! (mul) 10의 경우의 수를 생각할 수 있다. 하지만 모든 경우의 수를 들어 이 곳의 돌을 둬야 건널 수 있겠지 라는 생각으로 돌을 배치한다면, 경우의 수가 매우 많을 뿐 아니라, 돌의 개수가 100개가 되고, 1000개가 된다면 수도 없이 많은 경우의 수가 생겨 모든 경우를 탐색하는 것은 어려워진다. 이럴 때 나온 것이 담금질 기법이다. 그리고 이 때 가장 중요한 것이 Step 이라는 것인데, Step의 사이즈가 작으면 최적화가 쉽겠지만, 사이즈가 작으면 작을 수록 모든 경우의 수를 찾는 것과 똑같기 때문에, 적당한 Step을 찾는 것도 중요하다. (위 내용은 위키 백과와 인터넷을 참고, 혼자서 예시를 만들어 보았다.)

* 의사코드
```java
초기값과 초기 온도 T를 설정한다.
최적의 경우를 찾을 때까지 즉 온도가 완전히 내려갈 때 까지 프로그램을 LOOP한다.
P=nk는 즉 사이즈 값이 되고, k는 주어진 종류를 n은 우리가 결정하게 된다.
//이때 n은 step size인데, 이 값을 잘 조절해야 효율적인 담금질을 할 수있다.
기존의 솔루션과 임의로 선택한 솔루션의 값을 비교한다.
기존의 솔루션과 새로운 솔루션 즉 최적화 값의 차를 만든다.
만약 차의 값에 따라 임의의 솔루션을 쓸지 기존의 솔루션을 쓸 지 선택한다.
반복을 통해 값을 return하고 우리가 찾는 최적화의 답을 리턴 후, 정확한 최적화를 했다면
Global Optimization을 얻을 수 있다.
```
### 3.모델 설정 및 3~4차 함수의 전역 최적점 찾기.
3~4차 함수의 전역 최적점 찾기
* 초기온도(t):100도, 냉각률:0.95, 자유롭게 탐색할 확률(p):e^(-d/t)
* 종료조건(반복횟수):t가 0.01이 되면 종료
* f(x)=2*a*a*a-9*a*a+12*a+1
 - 6a^2-18a+12=0
 - 즉 x= 2에서 극소값을 갖지만, 이는 전역최적해가 아닌 지역 최적해일 뿐이다.
 - 자세한 최적점 과정은 코드에서 보기로한다.
#### 모델설정
* 독립변수(x): 1월~ 6월의 각 날 (x의 경우 시간에 지남에 따라 x=1 -> 12까지 순차적으로 월에 따른 값을 갖는다고 하자.)
* 종속변수(y): 각 날의 온도.

![선형 모델](https://user-images.githubusercontent.com/80510945/121557115-6fb08a80-ca4f-11eb-9235-8f041922a87d.jpg)

겨울부터 여름까지의 날씨는 대체로 비례하는 성격을 가지므로 y=ax+b의 선형 모델을 선택했습니다.

![컴알 과제용](https://user-images.githubusercontent.com/80510945/121559614-b43d2580-ca51-11eb-8999-9c1b43f6fde8.jpg)

* 날짜에 따른 날씨 그래프이며, 날씨에 대한 방해 조건이 없다면 거의 3x+11이라는 값을 갖는다.
* 회귀식: y= 3x+11
