package woowa;

public class problem1 {

	public static void main(String[] args) {

		int[] pobi = new int[2];
		int[] crong = new int[2];
		do {
			// 펼쳐보고 첫 페이지가 짝수가 나오면 값 재삽입
			pobi[0] = (int) (Math.random() * 399 + 1); // 1~399범위 삽입
			crong[0] = (int) (Math.random() * 399 + 1);
		} while ((pobi[0] % 2 == 0) || (crong[0] % 2 == 0));

		pobi[1] = pobi[0] + 1;
		crong[1] = crong[0] + 1;

		System.out.println("pobi : " + pobi[0] + ", " + pobi[1]);
		System.out.println("crong : " + crong[0] + ", " + crong[1]);

		System.out.println(solution(pobi, crong));

	}

	static int addarr(int[] arr) { // 배열값 전부 더하기
		int add = 0;
		for (int i = 0; i < arr.length; i++) {
			add += arr[i];
		}
		return add;
	}

	static int mularr(int[] arr) { // 배열값 전부 곱하기
		int mul = 1;
		arr = mul_exception(arr);
		for (int i = 0; i < arr.length; i++) {
			mul *= arr[i];
			// System.out.println(arr[i]);
		}
		return mul;
	}

	static int[] mul_exception(int[] arr) {
		// 자릿 수 곱에서 최대 단위값이 0인 경우 배열에서 제외
		int[] result = new int[arr.length - 1];
		if (arr[0] == 0) {
			result = mul_exception_zerodrop(arr);
			return mul_exception(result);
		}
		return arr;
	}

	static int[] mul_exception_zerodrop(int[] arr) {
		// mul_exception 함수에서 배열을 받아 최대 단위를 잘라 반환
		int[] result = new int[arr.length - 1];
		for (int i = 0; i < result.length; i++) {
			result[i] = arr[i + 1];
		}
		return result;
	}

	static int select_max(int x, int y) { // 둘 중 큰 수 반환
		if (x > y)	{	return x;	}
		if (y > x)	{	return y;	}
		return x;
	}

	static int solution(int[] pobi, int[] crong) {
		if (pobi[0] + 1 != pobi[1] || crong[0] + 1 != crong[1])
			{	return -1;	}
		// 예외사항1 : 펼친 페이지 수가 연속되지 않을 경우

		if ((pobi[0] | crong[0]) > 399 && (pobi[0] | crong[0]) < 1 && (pobi[1] | crong[1]) > 400
				&& (pobi[0] | crong[0]) < 2)
			{	return -1;	}
		// 예외사항2 : 펼친 페이지 번호의 범위가 왼쪽 1~399, 오른쪽 2~400 이 아닐 경우

		if ((pobi[0] | crong[0]) % 2 != 1 || (pobi[1] | crong[1]) % 2 != 0)
			{	return -1;	}
		// 예외사항3 : 펼쳐진 페이지 왼쪽과 오른쪽이 각각 홀수, 짝수가 아닐 경우
		
		int[] pld = { pobi[0] / 100, pobi[0] / 10 % 10, pobi[0] % 10 }; // pobi left split
		int[] prd = { pobi[1] / 100, pobi[1] / 10 % 10, pobi[1] % 10 }; // pobi right split

		int[] cld = { crong[0] / 100, crong[0] / 10 % 10, crong[0] % 10 }; // crong left split
		int[] crd = { crong[1] / 100, crong[1] / 10 % 10, crong[1] % 10 }; // crong right split

		int ppm = 0; // pobi page max
		int cpm = 0; // crong page max

		System.out.println("pobi_left_sum : " + addarr(pld) + ", pobi_left_mul : " + mularr(pld));
		System.out.println("pobi_right_sum : " + addarr(prd) + ", pobi_right_mul : " + mularr(prd));

		System.out.println("crong_left_sum : " + addarr(cld) + ", crong_left_mul : " + mularr(cld));
		System.out.println("crong_right_sum : " + addarr(crd) + ", crong_right_mul : " + mularr(crd));

		ppm = select_max(select_max(addarr(
				pld), mularr(pld)), select_max(addarr(prd), mularr(prd)));
		cpm = select_max(select_max(addarr(cld), mularr(cld)), select_max(addarr(crd), mularr(crd)));

		System.out.println("pobi_score : " + ppm);
		System.out.println("crong score : " + cpm);

		if (ppm > cpm) 	{	return 1;	}
		if (ppm < cpm)	{	return 2;	}
		if (ppm == cpm)	{	return 0;	}
		return -1;
	}
}
/*
 * solution 을 이욜하여 하기까진 가능 그러나 지금 예외처리 부분이 문제임
 */