import java.util.Arrays;
import java.util.Scanner;

public class Step1 {

	Scanner scan = new Scanner(System.in);

	// 1. MARK : 화면표시 처리
	public void mainScreen() {
		// 1. 단어 입력
		System.out.println("단어 하나를 입력해 주세요 : ");
		String inputWord = scan.next();
		// 2. 정수 입력
		System.out.println("정수를 입력해 주세요. 입력한 숫자갯수 만큼 이동한다.");
		System.out.println("-100이상, 100미만의 정수로 입력해 주세요.");
		int inputNumber = scan.nextInt();
		int confirmNumber = inputNumberCheck(inputNumber); // 조건에 만족하는 정수
		// 3. L또는 R 입력
		System.out.println("L 또는 R은 대소문자 모두 입력해 주세요 : ");
		String inputLR = scan.next();
		String confirmLR = inputLRcheck(inputLR); // 조거네 만족한 입력값 (L 또는 R)
		// 4. 조건확인 후 모든 입력값 표시
		System.out.println(inputWord + "," + confirmNumber + "," + confirmLR);
		// 5. 단어 밀어내기
		shiftRightLight(inputWord, confirmNumber, confirmLR);
	}

	// 2. MARK : 입력한 정수 값에 대한 조건확인 처리
	public int inputNumberCheck(int inputNumber) {

		int checkNumber = inputNumber;
		// 조건에 만족하는 정수를 입력할 경우
		if (checkNumber >= -100 && checkNumber < 100) {
			return checkNumber;
		} else {
			// 조건에 만족하지 않은 정수를 입력할 경우
			System.out.println("잘못 입력하셨습니다. -100 이상 100 미만의 정수를 입력해 주세요");
			inputNumber = scan.nextInt();
			inputNumberCheck(inputNumber);
			return checkNumber;
		}
	}

	// 3. MARK : 입력한 L 또는 R 확인 처리
	public String inputLRcheck(String inputLR) {
		String checkLR = inputLR;
		if (checkLR.equals("L") || checkLR.equals("l") || checkLR.equals("R") || checkLR.equals("r")) {
			return checkLR;
		} else {
			System.out.println("잘못 입력하셨습니다. L 또는 R은 대소문자 모두 입력해 주세요 : ");
			inputLR = scan.next();
			checkLR = inputLRcheck(inputLR);
			return checkLR;
		}
	}

	// 4. MARK : 단어 밀어내기 처리
	public void shiftRightLight(String inputWord, int confirmNumber, String confirmLR) {

		// 1. 입력한 문자열 자른 후 배열
		String[] array = inputWord.split("");
		int num = confirmNumber;
		if (confirmNumber > 0) {
			if (confirmLR.equals("l")) {
				confirmLR = "L"; // LR) 소문자 입력은 대문자로 변환
			} else if (confirmLR.equals("r")) {
				confirmLR = "R";
			}
		} else if (confirmNumber < 0) {
			if (confirmLR.equals("l") || confirmLR.equals("L")) {
				confirmLR = "R"; // LR) 소문자 입력은 대문자로 변환
				num = num * -1;
			} else if (confirmLR.equals("r") || confirmLR.equals("R")) {
				confirmLR = "L";
				num = num * -1;
			}
		}
		// 2. 왼쪽으로 단어 밀어내기 조건
		if (confirmLR.equals("L")) {
			for (int i = 0; i < num; i++) {
				String temp = array[0];
				for (int j = 1; j < array.length; j++) {
					array[j - 1] = array[j];
				}
				array[array.length - 1] = temp;
			}
			for (int i = 0; i < array.length; i++) {
				System.out.print(array[i]);
			}
			System.out.println();

			// 3. 오른쪽으로 단어 밀어내기 조건
		} else if (confirmLR.equals("R")) {
			String[] temp = new String[array.length];
			for (int i = 0; i < array.length; i++) {
				int newPosition = (i + num) % array.length;
				temp[newPosition] = array[i];
			}
			for (int i = 0; i < temp.length; i++) {
				System.out.print(temp[i]);
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {

		Step1 step1 = new Step1();
		step1.mainScreen();
	}
}
