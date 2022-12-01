2022-11-30

bot mode 는 server 에서 돌리는 게 아니라 server 에서는 동일하게 처리하되
client 측에서 랜덤한 command 를 날리는 방식으로 작동하는게 더 맞다는 생각이 드네.

client 측에서는 딱히 고려될 사항이 없음.
사용자의 입력을 받고 서버로부터 response 를 받고 request를 날리고 하는 동작 외에는
별다른 동작이 필요하진 않을듯.

그리고 bot mode 를 구현할때 bot 과 exit bot 명령어를 입력받아야하는데 readLine이 blocking 동작이라서
exit bot 을 받기 위해서는 bot을 thread 로 구현해야함을 대강 짐작할 수 있음.
-> 이건 굳이 thread 만들지 않고 다른 방법 구현이 떠오르지가 않네...


특정 범위의 난수 생성하기.
(int) Math.random() * (최댓값-최소값+1) + 최소값