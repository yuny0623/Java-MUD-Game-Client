2022-11-24

이슈:
readLine 이 blocking 으로 동작함.
이것때문에 서버에서 오는 데이터가 한 박자 느리게 받아지거나 혹은
여러 개의 input 을 client측에서 받지 못하는 문제가 생김.

그래서 server 에서 받는 input 과
client에서 받은 input을 서버로 전송하는 것을
두 개의 thread로 나눠서 처리하는 것이 더 좋다.

남은 과제:
client 측에서는 남은 일이 별로 없네.