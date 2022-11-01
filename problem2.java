package woowa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class problem2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean check_cryptogram = false;
		String cryptogram = null;
		while (!check_cryptogram) {
			System.out.print("cryptogram : ");
			cryptogram = sc.next();
			check_cryptogram = length_test(cryptogram) && split_to_char(cryptogram) && alpabet_test(cryptogram);
			// length_test = 참, 대문자 테스트 = 참이면 정상입력 판단
		}

		System.out.println(solution(cryptogram));
	}

	static boolean length_test(String cryptogram) { // 암호길이 점검
		if ((1 > cryptogram.length()) || (1000 < cryptogram.length())) {
			System.out.println("cryptogram's length error");
			return false;
		}
		return true;
	}

	static boolean alpabet_test(String cryptogram) {// 알파벳인지 확인

		char[] arr = cryptogram.toCharArray();
		boolean result = false;
		int i = -1;
		do { // 알파벳이 아닌 것이 감지 될 때 까지, 혹은 문자열이 끝날 때 까지 반복
			i++;
			result = check_alpbet_char(arr[i]);
		} while (!(check_alpbet_char(arr[i]) || arr.length == i + 1));
		return !result;
	}

	static boolean check_alpbet_char(char ch) { // 알파벳이 아니면 true반환
		if (ch > 'z' || ch < 'A') {
			return true;
		}
		return false;
	}

	static boolean split_to_char(String cryptogram) { // 문자열을 문자로 나눔
		// 대문자 포함여부 확인. 참 나오면 정상입력 판단(소문자만 있단 뜻)
		// 거짓이면 대문자 포함됨.
		char[] arr = cryptogram.toCharArray();
		boolean result = false;
		// 두 번쨰 이상으로 A가 들어가면
		int i = -1;
		do { // 대문자가 감지 될 때 까지, 혹은 문자열이 끝날 때 까지 반복
			i++;
			result = check_Uppercase(arr[i]);
		} while (!(check_Uppercase(arr[i]) || arr.length == i + 1));
		return !result;
	}

	static boolean check_Uppercase(char ch) { // 대문자면 true반환
		if (Character.isUpperCase(ch)) {
			return true;
		}
		return false;
	}

	static boolean same_test(char x, char y) {
		if (x == y) {
			return true;
		}
		return false;
	}

	static int same_count(int count, boolean same) { // 각 문자별 중복 카운팅
		if (same) {
			return ++count;
		}
		return count;
	}

	static List<Integer> same_point_add(boolean same, List<Integer> list, int index) { // 중복된 구간을 기록한 int리스트에 값 추가
		// index는 현재 중복된 구간값(추가해야할 값)
		if (same) {
			list.add(index);
			list.add(index+1); // 연속되는 인덱스 포인트도 같이 없애야함
		}
		return list;
	}

	static List<Integer> same_point(char[] arr) { // 문자열 전체 중복된 구간 반환
		boolean same = false;
		List<Integer> same_list = new ArrayList<>();

		for (int i = 0; i < arr.length - 1; i++) { // same_point의 값 추가
			same = same_test(arr[i], arr[i + 1]);
			same_list = same_point_add(same, same_list, i);
		}

		return same_list;
	}

	static List<Character> remove_same_input(boolean same, List<Character> list, char ch) {
		if (same) {
			list.add(ch);// index는 same_list.contains(i)(삭제 할 인덱스)로 변경. 중복값을 지운 새 배열 값 입력
		}
		return list;
	}

	static char[] remove_same(char[] arr, List<Integer> same_list) {
		List<Character> result_temp = new ArrayList<>(); // arraylist형식으로 임시 저장
		char[] result = new char[arr.length - (same_list.size())]; // 중복값을 지운 새 배열 arr.length - same_list.size() * 2
		
		boolean same = false;
		
		for (int i = 0; i < arr.length; i++) {
			same = !same_list.contains(i); // !same_list.contains(i) 가 true면 중복이란 뜻
			result_temp = remove_same_input(same, result_temp, arr[i]);
		}
		int index=0;
		for(char ch:result_temp) {
			result[index++] = ch;
		}
		
		return result;
	}

	static String solution(String cryptogram) {

		String result;
		int count = 0; // 중복 개수 확인
		char[] arr = cryptogram.toCharArray();
		boolean same = false;

		List<Integer> same_list; // 중복되는 구간들을 모아놓은 리스트
		
		do {
			System.out.print(++count+".\""); 
			for(int i=0;i<arr.length;i++) System.out.print(arr[i]);
			System.out.println("\"");
			same_list = new ArrayList<>();
			same_list = same_point(arr); // 중복되는 구간 추가
			arr = remove_same(arr, same_list);// 반복구간 내용 제거 후 재 삽입
		} while (!same_list.isEmpty());
		result = String.valueOf(arr);
		
		return result;
	}

}
