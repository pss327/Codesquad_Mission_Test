import java.util.ArrayList;
import java.util.Scanner;

public class Step2 {

	Scanner scan = new Scanner(System.in);
	String[][] cube = { { "R", "R", "W" }, { "G", "C", "W" }, { "G", "B", "B" } };
	String[][] tmpCube = new String[3][3];

	public void mainScreen() {

		System.out.println("<< 초기 큐브 >>");

		for (int i = 0; i < cube.length; i++) {
			for (int j = 0; j < cube[i].length; j++) {
				System.out.print(cube[i][j] + "\t"); // 열 출력
			}
			System.out.println(); // 행 출력
		}

		System.out.println("=========================================");
		System.out.println(" U  가장 윗줄을 왼쪽으로 한 칸 밀기 RRW -> RWR");
		System.out.println(" U' 가장 윗줄을 오른쪽으로 한 칸 밀기 RRW -> WRR");
		System.out.println(" R  가장 오른쪽 줄을 위로 한 칸 밀기 WWB -> WBW");
		System.out.println(" R' 가장 오른쪽 줄을 아래로 한 칸 밀기 WWB -> BWW");
		System.out.println(" L  가장 왼쪽 줄을 아래로 한 칸 밀기 RGG -> GRG (L의 경우 R과 방향이 반대임을 주의한다.)");
		System.out.println(" L' 가장 왼쪽 줄을 위로 한 칸 밀기 RGG -> GGR");
		System.out.println(" B  가장 아랫줄을 오른쪽으로 한 칸 밀기 GBB -> BGB (B의 경우도 U와 방향이 반대임을 주의한다.");
		System.out.println(" B' 가장 아랫줄을 왼쪽으로 한 칸 밀기 GBB -> BBG");
		System.out.println(" Q  Bye~를 출력하고 프로그램을 종료한다.");
		System.out.println("=========================================");
		System.out.print("CUBE > ");
		String input = scan.nextLine();
		ArrayList<String> array = inputAddArray(input);
		System.out.println();
		checkElement(array);

	}

	// 1. MARK : 입력 값 ArrayList에 담기
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
		return arrayList;
	}

	// 2. MARK : 입력 값 확인
	public void checkElement(ArrayList<String> array) {

		for (int i = 0; i < array.size(); i++) {

			String element = array.get(i);

			if (element.equals("U") || element.equals("U'") || element.equals("R") || element.equals("R'")
					|| element.equals("L") || element.equals("L'") || element.equals("B") || element.equals("B'")
					|| element.equals("Q")) {

				// 큐브 동작
				controllCube(element);

			} else {

				System.out.println("잘못된 입력 데이터가 들어가 있습니다.");
			}
		}
		System.out.print("");
		System.out.print("CUBE > ");
		String input = scan.nextLine();
		ArrayList<String> array1 = inputAddArray(input);
		System.out.println();
		checkElement(array1);

	}

	// 3. MARK : 임시 큐브 확인
	public void tmpCube() {
		for (int i = 0; i < cube.length; i++) {
			for (int j = 0; j < cube[i].length; j++) {
				tmpCube[i][j] = cube[i][j];
			}
		}
	}

	// 4. MARK : 큐브 이동
	public void changedCube() {
		for (int i = 0; i < cube.length; i++) {
			for (int j = 0; j < cube[i].length; j++) {
				System.out.print(cube[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println();

	}

	// 5. MARK : 시스템 동작
	public void controllCube(String input) {

		switch (input) {

		case "U":
			System.out.println("U");
			tmpCube();
			cube[0][0] = tmpCube[0][1];
			cube[0][1] = tmpCube[0][2];
			cube[0][2] = tmpCube[0][0];
			changedCube();
			break;

		case "U'":
			System.out.println("U");
			tmpCube();
			cube[0][0] = tmpCube[0][2];
			cube[0][1] = tmpCube[0][0];
			cube[0][2] = tmpCube[0][1];
			changedCube();
			break;

		case "R":

			System.out.println("R");

			for (int i = 0; i < cube.length; i++) {
				for (int j = 0; j < cube[i].length; j++) {
					tmpCube[i][j] = cube[i][j];
				}
			}

			cube[0][2] = tmpCube[1][2];
			cube[1][2] = tmpCube[2][2];
			cube[2][2] = tmpCube[0][2];

			for (int i = 0; i < cube.length; i++) {
				for (int j = 0; j < cube[i].length; j++) {
					System.out.print(cube[i][j] + "\t");
				}
				System.out.println();
			}
			System.out.println();

			break;

		case "R'":

			System.out.println("R'");

			for (int i = 0; i < cube.length; i++) {
				for (int j = 0; j < cube[i].length; j++) {
					tmpCube[i][j] = cube[i][j];
				}
			}

			cube[0][2] = tmpCube[2][2];
			cube[1][2] = tmpCube[0][2];
			cube[2][2] = tmpCube[1][2];

			for (int i = 0; i < cube.length; i++) {
				for (int j = 0; j < cube[i].length; j++) {
					System.out.print(cube[i][j] + "\t");
				}
				System.out.println();
			}
			System.out.println();

			break;

		case "L":

			System.out.println("L");

			for (int i = 0; i < cube.length; i++) {
				for (int j = 0; j < cube[i].length; j++) {
					tmpCube[i][j] = cube[i][j];
				}
			}

			cube[0][0] = tmpCube[2][0];
			cube[1][0] = tmpCube[0][0];
			cube[2][0] = tmpCube[1][0];

			for (int i = 0; i < cube.length; i++) {
				for (int j = 0; j < cube[i].length; j++) {
					System.out.print(cube[i][j] + "\t");
				}
				System.out.println();
			}
			System.out.println();

			break;

		case "L'":

			System.out.println("L'");

			for (int i = 0; i < cube.length; i++) {
				for (int j = 0; j < cube[i].length; j++) {
					tmpCube[i][j] = cube[i][j];
				}
			}

			cube[0][0] = tmpCube[1][0];
			cube[1][0] = tmpCube[2][0];
			cube[2][0] = tmpCube[0][0];

			for (int i = 0; i < cube.length; i++) {
				for (int j = 0; j < cube[i].length; j++) {
					System.out.print(cube[i][j] + "\t");
				}
				System.out.println(); // 행 출력
			}
			System.out.println();

			break;

		case "B":

			System.out.println("B");

			for (int i = 0; i < cube.length; i++) {
				for (int j = 0; j < cube[i].length; j++) {
					tmpCube[i][j] = cube[i][j];
				}
			}

			cube[2][0] = tmpCube[2][2];
			cube[2][1] = tmpCube[2][0];
			cube[2][2] = tmpCube[2][1];

			for (int i = 0; i < cube.length; i++) {
				for (int j = 0; j < cube[i].length; j++) {
					System.out.print(cube[i][j] + "\t");
				}
				System.out.println();
			}
			System.out.println();

			break;

		case "B'":

			System.out.println("B'");

			for (int i = 0; i < cube.length; i++) {
				for (int j = 0; j < cube[i].length; j++) {
					tmpCube[i][j] = cube[i][j];
				}
			}

			cube[2][0] = tmpCube[2][1];
			cube[2][1] = tmpCube[2][2];
			cube[2][2] = tmpCube[2][0];

			for (int i = 0; i < cube.length; i++) {
				for (int j = 0; j < cube[i].length; j++) {
					System.out.print(cube[i][j] + "\t");
				}
				System.out.println();
			}
			System.out.println();

			break;

		case "Q":

			System.out.println("Bye~");

			break;
		}

	}

	public static void main(String[] args) {

		Step2 step2 = new Step2();
		step2.mainScreen();
	}

}
