# Codesquad_Mission_Test
코드스쿼드 2021년 테스트

Step1 - 단어 밀어내기 구현하기 
1. mainScreen() : 화면표시 및 데이터 입력
2. inputNumberCheck(int inputNumber) : 입력한 정수 값에 대한 조건 확인 
  * checkNumber >= -100 또는 checkNumber < 100 일 경우 int checkNumber를 리턴 
  * 윗 조건에 해당하지 않을 경우 
   → "잘못 입력하셨습니다. -100 이상 100 미만의 정수를 입력해 주세요" 라고 화면에 출력한다. 
3. inputLRcheck(String inputLR) : 입력한 L 또는 R 확인 처리
  * 입력값 (String inputLR)이 L 또는 R 인지를 확인한다.
  * 윗 조건에 해당하지 않을 경우 (L 또는 R이 아닐 경우)
   → "잘못 입력하셨습니다. L 또는 R은 대소문자 모두 입력해 주세요 " 라고 화면에 출력한다. 
4. shiftRightLight(String inputWord, int confirmNumber, String confirmLR) : 단어 밀어내기 처리
  * 입력한 문자열을 문자 하나하나 나눈다 String[] array = inputWord.split("")
  * confirmNumber > 0 일 경우 l 과 r을 대문자 L 과 R로 변환한다.
  * confirmNumber < 0 일 경우 l 또는 r을 R로 변환 한 후 confirmNumber에 -1를 곱해 양수로 만든다.
    confirmNumber < 0 일 경우 r 또는 l을 L로 변환 한 후 confirmNumber에 -1를 곱해 양수로 만든다.
  * confirmLR L 인 경우 왼쪽으로 R 인 경우 오른쪽으로 밀어낸다.

Step2 - 평면 큐브 구현하기

Step3 - 루빅스 큐브 구현하기

