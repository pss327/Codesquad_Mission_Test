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
1. mainScreen() : 화면표시 및 데이터입력
2. inputAddArray(String input) : 입력 값 ArrayList에 담기
 * 입력한 문자열을 문자로 나누어 배열에 담는다 String[] inputArray = input.split("")
 * inputArray 배열에 "'"이 있을 경우 이전 인덱스에 있는 문자에 합친다.
 * inputArray를 for문으로 돌려서 배열의 값을 ArrayList<String> arrayList에 담아둔다. 단 "'"가 들어있는 배열은 담지않는다.
3. checkElement(ArrayList<String> array) : 입력 값 확인
 * array에 [ U, U', R, R', L, L', B, B', Q ] 가 들어 있는지 확인한다.
 * 윗 조건에 해당할 경우 controllCube(String input) 를 실행한다.
 * 해당하지 않을 경우 "잘못된 입력 데이터가 들어가 있습니다." 를 출력한다.
4. tmpCube() : 임시 큐브 확인 즉 이전 큐브를 저장해 두는 곳이다.
5. changedCube() : 큐브 이동 즉 변경된 큐브를 출력한다.
6. controllCube(String input) : checkElement(ArrayList<String> array)에서 확인 된 큐브 실행을 컨트롤한다.  
 
Step3 - 루빅스 큐브 구현하기

