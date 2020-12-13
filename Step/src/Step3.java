import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Step3 {

	private Scanner scan = new Scanner(System.in);
	private int countNumber = 0;
	private long startTime = 0;
	private char[][] tmpCube1 = new char[3][3];
	private char[][] tmpCube2 = new char[3][3];
	private char[][] tmpCube3 = new char[3][3];
	private char[][] tmpCube4 = new char[3][3];
	private char[][] tmpCube5 = new char[3][3];
	private char[][] tmpCube6 = new char[3][3];
	private char[][][] initCubic = {

			{ // 1. 윗면
					{ 'B', 'B', 'B' }, { 'B', 'B', 'B' }, { 'B', 'B', 'B' } },
			{ // 2. 왼쪽면
					{ 'W', 'W', 'W' }, { 'W', 'W', 'W' }, { 'W', 'W', 'W' } },
			{ // 3. 앞쪽
					{ 'O', 'O', 'O' }, { 'O', 'O', 'O' }, { 'O', 'O', 'O' } },
			{ // 4. 오른쪽면
					{ 'G', 'G', 'G' }, { 'G', 'G', 'G' }, { 'G', 'G', 'G' } },
			{ // 5. 뒷쪽
					{ 'Y', 'Y', 'Y' }, { 'Y', 'Y', 'Y' }, { 'Y', 'Y', 'Y' } },
			{ // 6. 아랫면
					{ 'R', 'R', 'R' }, { 'R', 'R', 'R' }, { 'R', 'R', 'R' } } };

	// 초기 큐빅
	public void prinInit() {
		for (int i = 0; i < initCubic[0].length; i++) {
			for (int j = 0; j < initCubic[0][0].length; j++) {
				if (j == 0 || j == 4 || j == 7) {
					System.out.printf("%18s", " ");
				}
				System.out.printf("%2s", initCubic[0][i][j]);
			}
			System.out.println();
		}

		for (int i = 0; i < initCubic[0].length; i++) {
			for (int j = 1; j < initCubic.length - 1; j++) {
				for (int k = 0; k < 3; k++) {
					System.out.printf("%2s", initCubic[j][i][k]);
				}
				System.out.printf("%6s", " ");
			}
			System.out.println();
		}

		for (int i = 0; i < initCubic[0].length; i++) {
			for (int j = 0; j < initCubic[0][0].length; j++) {
				if (j == 0 || j == 4 || j == 7) {
					System.out.printf("%18s", " ");
				}
				System.out.printf("%2s", initCubic[5][i][j]);
			}
			System.out.println();
		}
	}

	// 시작 화면
	public void startPrint() {
		System.out.println();
		System.out.println("--------------------------- 큐빅 설명  ------------------------------------------");
		System.out.println(" F - 앞 (Front) ");
		System.out.println(" R – 오른쪽 (Right)");
		System.out.println(" U – 위 (Up)");
		System.out.println(" B – 뒤 (Back)");
		System.out.println(" L – 왼쪽 (Left)");
		System.out.println(" D – 아랫쪽 (Down)");
		System.out.println(" Q  프로그램을 종료한다.");
		System.out.println(" M  큐브를 무작위 섞기.");
		System.out.println();
		System.out.println(" 알파벳만 (' 이 없을때) 시계 방향 으로 회전을 하고 반시계 방향 회전은 ' 으로 나타낸다 (예) F').");
		System.out.println("------------------------------------------------------------------------------");
		System.out.print("CUBE > ");
		String input = scan.nextLine();
		System.out.println();
		ArrayList<String> arrayList = inputAddArray(input);
		// startTime = System.currentTimeMillis();
		checkInput(arrayList);

	}

	// 입력한 데이터 리스트에 담기
	public ArrayList<String> inputAddArray(String input) {
		String[] inputArray = input.split("");
		ArrayList<String> arrayList = new ArrayList<String>();
		for (int i = 0; i < inputArray.length; i++) {
			String inputElement = inputArray[i];
			if (inputElement.equals("'")) {
				inputArray[i - 1] = inputArray[i - 1] + "'";
			}
		}
		for (int i = 0; i < inputArray.length; i++) {
			String element = inputArray[i];
			if (!element.equals("'")) {
				arrayList.add(element);
			}
		}
		countNumber = arrayList.size();
		return arrayList;
	}

	// 입력한 데이터 확인
	public void checkInput(ArrayList<String> array) {
		for (int i = 0; i < array.size(); i++) {
			String element = array.get(i);

			if (element.equals("F") || element.equals("F'") || element.equals("R") || element.equals("R'")
					|| element.equals("U") || element.equals("U'") || element.equals("B") || element.equals("B'")
					|| element.equals("L") || element.equals("L'") || element.equals("D") || element.equals("D'")
					|| element.equals("Q")) {
				// 큐브 동작
				moveCubic(element);
			} else {
				System.out.println("잘못된 입력 데이터가 들어가 있습니다.");
				startPrint();
			}
		}
		// 완성 확인
		boolean isComplete = checkComplete();
		if (isComplete == true) {
			System.out.println("큐브를 완성했습니다.");
			long endTime = System.currentTimeMillis();
			long term = endTime - startTime;
			long minute = (term) / (1000 * 60);
			long second = (term - (minute * 1000 * 60)) / 1000;
			System.out.println("경과시간 : " + minute + "분 " + second + "초");
			System.out.println("조작갯수 : " + countNumber);

		} else if (isComplete == false) {
			System.out.print("CUBE > ");
			String input = scan.nextLine();
			System.out.println();
			ArrayList<String> arrayList = inputAddArray(input);
			checkInput(arrayList);
		}
	}

	// 완성 확인
	public boolean checkComplete() {

		boolean isComplete = false;
		char[][] completeUp = new char[3][3];
		char[][] completeLeft = new char[3][3];
		char[][] completeFront = new char[3][3];
		char[][] completeRight = new char[3][3];
		char[][] completeBack = new char[3][3];
		char[][] completeDown = new char[3][3];

		char[][] currentUp = new char[3][3];
		char[][] currentLeft = new char[3][3];
		char[][] currentFront = new char[3][3];
		char[][] currentRight = new char[3][3];
		char[][] currentBack = new char[3][3];
		char[][] currentDown = new char[3][3];

		for (int i = 0; i < 3; i++) {
			completeUp[i] = new char[] { 'B', 'B', 'B' };
			completeLeft[i] = new char[] { 'W', 'W', 'W' };
			completeFront[i] = new char[] { 'O', 'O', 'O' };
			completeRight[i] = new char[] { 'G', 'G', 'G' };
			completeBack[i] = new char[] { 'Y', 'Y', 'Y' };
			completeDown[i] = new char[] { 'R', 'R', 'R' };
		}
		for (int i = 0; i < initCubic[0].length; i++) {
			for (int j = 0; j < initCubic[0][i].length; j++) {
				currentUp[i][j] = initCubic[0][i][j];
			}
		}
		for (int i = 0; i < initCubic[1].length; i++) {
			for (int j = 0; j < initCubic[1][i].length; j++) {
				currentLeft[i][j] = initCubic[1][i][j];
			}
		}
		for (int i = 0; i < initCubic[2].length; i++) {
			for (int j = 0; j < initCubic[2][i].length; j++) {
				currentFront[i][j] = initCubic[2][i][j];
			}
		}
		for (int i = 0; i < initCubic[3].length; i++) {
			for (int j = 0; j < initCubic[3][i].length; j++) {
				currentRight[i][j] = initCubic[3][i][j];
			}
		}
		for (int i = 0; i < initCubic[4].length; i++) {
			for (int j = 0; j < initCubic[4][i].length; j++) {
				currentBack[i][j] = initCubic[4][i][j];
			}
		}
		for (int i = 0; i < initCubic[5].length; i++) {
			for (int j = 0; j < initCubic[5][i].length; j++) {
				currentDown[i][j] = initCubic[5][i][j];
			}
		}
		if (Objects.deepEquals(currentUp, completeUp) && Objects.deepEquals(currentLeft, completeLeft)
				&& Objects.deepEquals(currentFront, completeFront) && Objects.deepEquals(currentRight, completeRight)
				&& Objects.deepEquals(currentBack, completeBack) && Objects.deepEquals(currentDown, completeDown)) {
			isComplete = true;
			return isComplete;
		} else {
			return isComplete;
		}
	}

	// 임시큐브
	public void tempCubic() {
		for (int i = 0; i < initCubic[0].length; i++) {
			for (int j = 0; j < initCubic[0][i].length; j++) {
				tmpCube1[i][j] = initCubic[0][i][j];
			}
		}
		for (int i = 0; i < initCubic[1].length; i++) {
			for (int j = 0; j < initCubic[1][i].length; j++) {
				tmpCube2[i][j] = initCubic[1][i][j];
			}
		}
		for (int i = 0; i < initCubic[2].length; i++) {
			for (int j = 0; j < initCubic[2][i].length; j++) {
				tmpCube3[i][j] = initCubic[2][i][j];
			}
		}
		for (int i = 0; i < initCubic[3].length; i++) {
			for (int j = 0; j < initCubic[3][i].length; j++) {
				tmpCube4[i][j] = initCubic[3][i][j];
			}
		}
		for (int i = 0; i < initCubic[4].length; i++) {
			for (int j = 0; j < initCubic[4][i].length; j++) {
				tmpCube5[i][j] = initCubic[4][i][j];
			}
		}
		for (int i = 0; i < initCubic[5].length; i++) {
			for (int j = 0; j < initCubic[5][i].length; j++) {
				tmpCube6[i][j] = initCubic[5][i][j];
			}
		}
	}
	
	// 큐브 무작위 섞기
	public void randomCube() {
		
	}
	// 큐빅 이동
	public void moveCubic(String direction) {

		switch (direction) {
		case "F":
			System.out.println("F 으로 이동");
			tempCubic();

			initCubic[0][2][0] = tmpCube2[0][2];
			initCubic[0][2][1] = tmpCube2[1][2];
			initCubic[0][2][2] = tmpCube2[2][2];

			initCubic[1][0][2] = tmpCube6[0][0];
			initCubic[1][1][2] = tmpCube6[0][1];
			initCubic[1][2][2] = tmpCube6[0][2];

			initCubic[3][0][0] = tmpCube1[2][0];
			initCubic[3][1][0] = tmpCube1[2][1];
			initCubic[3][2][0] = tmpCube1[2][2];

			initCubic[5][0][0] = tmpCube4[0][0];
			initCubic[5][0][1] = tmpCube4[1][0];
			initCubic[5][0][2] = tmpCube4[2][0];

			prinInit();

			break;
		case "F'":
			System.out.println("F' 으로 이동");
			tempCubic();

			initCubic[0][2][0] = tmpCube4[0][0];
			initCubic[0][2][1] = tmpCube4[1][0];
			initCubic[0][2][2] = tmpCube4[2][0];

			initCubic[1][0][2] = tmpCube1[2][0];
			initCubic[1][1][2] = tmpCube1[2][1];
			initCubic[1][2][2] = tmpCube1[2][2];

			initCubic[3][0][0] = tmpCube6[0][0];
			initCubic[3][1][0] = tmpCube6[0][1];
			initCubic[3][2][0] = tmpCube6[0][2];

			initCubic[5][0][0] = tmpCube2[0][2];
			initCubic[5][0][1] = tmpCube2[1][2];
			initCubic[5][0][2] = tmpCube2[2][2];

			prinInit();

			break;
		case "R":

			System.out.println("R 으로 이동");
			tempCubic();

			initCubic[0][0][2] = tmpCube3[0][2];
			initCubic[0][1][2] = tmpCube3[1][2];
			initCubic[0][2][2] = tmpCube3[2][2];

			initCubic[2][0][2] = tmpCube6[0][2];
			initCubic[2][1][2] = tmpCube6[1][2];
			initCubic[2][2][2] = tmpCube6[2][2];

			initCubic[4][0][0] = tmpCube1[0][2];
			initCubic[4][1][0] = tmpCube1[1][2];
			initCubic[4][2][0] = tmpCube1[2][2];

			initCubic[5][0][2] = tmpCube5[0][0];
			initCubic[5][1][2] = tmpCube5[1][0];
			initCubic[5][2][2] = tmpCube5[2][0];

			prinInit();
			break;
		case "R'":
			System.out.println("R' 으로 이동");
			tempCubic();

			initCubic[0][0][2] = tmpCube5[0][0];
			initCubic[0][1][2] = tmpCube5[1][0];
			initCubic[0][2][2] = tmpCube5[2][0];

			initCubic[2][0][2] = tmpCube1[0][2];
			initCubic[2][1][2] = tmpCube1[1][2];
			initCubic[2][2][2] = tmpCube1[2][2];

			initCubic[4][0][0] = tmpCube6[0][2];
			initCubic[4][1][0] = tmpCube6[1][2];
			initCubic[4][2][0] = tmpCube6[2][2];

			initCubic[5][0][2] = tmpCube3[0][2];
			initCubic[5][1][2] = tmpCube3[1][2];
			initCubic[5][2][2] = tmpCube3[2][2];

			prinInit();
			break;
		case "L":

			System.out.println("L 으로 이동");
			tempCubic();

			initCubic[0][0][0] = tmpCube5[0][2];
			initCubic[0][1][0] = tmpCube5[1][2];
			initCubic[0][2][0] = tmpCube5[2][2];

			initCubic[2][0][0] = tmpCube1[0][0];
			initCubic[2][1][0] = tmpCube1[1][0];
			initCubic[2][2][0] = tmpCube1[2][0];

			initCubic[4][0][2] = tmpCube3[0][0];
			initCubic[4][1][2] = tmpCube3[1][0];
			initCubic[4][2][2] = tmpCube3[2][0];

			initCubic[5][0][0] = tmpCube6[0][0];
			initCubic[5][1][0] = tmpCube6[1][0];
			initCubic[5][2][0] = tmpCube6[2][0];

			prinInit();
			break;
		case "L'":

			System.out.println("L' 으로 이동");
			tempCubic();
			initCubic[0][0][0] = tmpCube3[0][0];
			initCubic[0][1][0] = tmpCube3[1][0];
			initCubic[0][2][0] = tmpCube3[2][0];

			initCubic[2][0][0] = tmpCube6[0][0];
			initCubic[2][1][0] = tmpCube6[1][0];
			initCubic[2][2][0] = tmpCube6[2][0];

			initCubic[4][0][2] = tmpCube1[0][0];
			initCubic[4][1][2] = tmpCube1[1][0];
			initCubic[4][2][2] = tmpCube1[2][0];

			initCubic[5][0][0] = tmpCube5[0][2];
			initCubic[5][1][0] = tmpCube5[1][2];
			initCubic[5][2][0] = tmpCube5[2][2];

			prinInit();
			break;

		case "U":
			System.out.println("U 으로 이동");
			tempCubic();

			initCubic[1][0][0] = tmpCube5[0][0];
			initCubic[1][0][1] = tmpCube5[0][1];
			initCubic[1][0][2] = tmpCube5[0][2];

			initCubic[2][0][0] = tmpCube2[0][0];
			initCubic[2][0][1] = tmpCube2[0][1];
			initCubic[2][0][2] = tmpCube2[0][2];

			initCubic[3][0][0] = tmpCube3[0][0];
			initCubic[3][0][1] = tmpCube3[0][1];
			initCubic[3][0][2] = tmpCube3[0][2];

			initCubic[4][0][0] = tmpCube4[0][0];
			initCubic[4][0][1] = tmpCube4[0][1];
			initCubic[4][0][2] = tmpCube4[0][2];

			prinInit();
			break;

		case "U'":
			System.out.println("U'' 으로 이동");

			tempCubic();

			initCubic[1][0][0] = tmpCube3[0][0];
			initCubic[1][0][1] = tmpCube3[0][1];
			initCubic[1][0][2] = tmpCube3[0][2];

			initCubic[2][0][0] = tmpCube4[0][0];
			initCubic[2][0][1] = tmpCube4[0][1];
			initCubic[2][0][2] = tmpCube4[0][2];

			initCubic[3][0][0] = tmpCube5[0][0];
			initCubic[3][0][1] = tmpCube5[0][1];
			initCubic[3][0][2] = tmpCube5[0][2];

			initCubic[4][0][0] = tmpCube2[0][0];
			initCubic[4][0][1] = tmpCube2[0][1];
			initCubic[4][0][2] = tmpCube2[0][2];

			prinInit();
			break;

		case "B":
			System.out.println("B 으로 이동");
			tempCubic();

			initCubic[0][0][0] = tmpCube4[0][2];
			initCubic[0][0][1] = tmpCube4[1][2];
			initCubic[0][0][2] = tmpCube4[2][2];

			initCubic[1][0][0] = tmpCube1[0][0];
			initCubic[1][1][0] = tmpCube1[0][1];
			initCubic[1][2][0] = tmpCube1[0][2];

			initCubic[5][2][0] = tmpCube2[0][0];
			initCubic[5][2][1] = tmpCube2[1][0];
			initCubic[5][2][2] = tmpCube2[2][0];

			initCubic[3][0][2] = tmpCube6[2][0];
			initCubic[3][1][2] = tmpCube6[2][1];
			initCubic[3][2][2] = tmpCube6[2][2];

			prinInit();
			break;
		case "B'":
			System.out.println("B' 으로 이동");
			tempCubic();

			initCubic[0][0][0] = tmpCube2[0][0];
			initCubic[0][0][1] = tmpCube2[1][0];
			initCubic[0][0][2] = tmpCube2[2][0];

			initCubic[1][0][0] = tmpCube6[2][0];
			initCubic[1][1][0] = tmpCube6[2][1];
			initCubic[1][2][0] = tmpCube6[2][2];

			initCubic[5][2][0] = tmpCube4[0][2];
			initCubic[5][2][1] = tmpCube4[1][2];
			initCubic[5][2][2] = tmpCube4[2][2];

			initCubic[3][0][2] = tmpCube1[0][0];
			initCubic[3][1][2] = tmpCube1[0][1];
			initCubic[3][2][2] = tmpCube1[0][2];

			prinInit();
			break;

		case "D":
			System.out.println("D 으로 이동");
			tempCubic();

			initCubic[1][2][0] = tmpCube5[2][0];
			initCubic[1][2][1] = tmpCube5[2][1];
			initCubic[1][2][2] = tmpCube5[2][2];

			initCubic[2][2][0] = tmpCube2[2][0];
			initCubic[2][2][1] = tmpCube2[2][1];
			initCubic[2][2][2] = tmpCube2[2][2];

			initCubic[3][2][0] = tmpCube3[2][0];
			initCubic[3][2][1] = tmpCube3[2][1];
			initCubic[3][2][2] = tmpCube3[2][2];

			initCubic[4][2][0] = tmpCube4[2][0];
			initCubic[4][2][1] = tmpCube4[2][1];
			initCubic[4][2][2] = tmpCube4[2][2];

			prinInit();

			break;
		case "D'":

			System.out.println("D' 으로 이동");
			tempCubic();

			initCubic[1][2][0] = tmpCube3[2][0];
			initCubic[1][2][1] = tmpCube3[2][1];
			initCubic[1][2][2] = tmpCube3[2][2];

			initCubic[2][2][0] = tmpCube4[2][0];
			initCubic[2][2][1] = tmpCube4[2][1];
			initCubic[2][2][2] = tmpCube4[2][2];

			initCubic[3][2][0] = tmpCube5[2][0];
			initCubic[3][2][1] = tmpCube5[2][1];
			initCubic[3][2][2] = tmpCube5[2][2];

			initCubic[4][2][0] = tmpCube2[2][0];
			initCubic[4][2][1] = tmpCube2[2][1];
			initCubic[4][2][2] = tmpCube2[2][2];

			prinInit();
			break;

		case "Q":
			System.out.println("종료합니다.");

			long endTime = System.currentTimeMillis();
			long term = endTime - startTime;

			long minute = (term) / (1000 * 60);
			long second = (term - (minute * 1000 * 60)) / 1000;
			System.out.println("경과시간 : " + minute + "분 " + second + "초");
			System.out.println("조작갯수 : " + countNumber);
			break;
		}
	}
	
	public static void main(String[] args) {

		Step3 step3 = new Step3();
		step3.prinInit();
		step3.startPrint();
	}
}
