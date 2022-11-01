package woowa;

public class problem1 {

	public static void main(String[] args) {

		int[] pobi = new int[2];
		int[] crong = new int[2];
		do {
			// ���ĺ��� ù �������� ¦���� ������ �� �����
			pobi[0] = (int) (Math.random() * 399 + 1); // 1~399���� ����
			crong[0] = (int) (Math.random() * 399 + 1);
		} while ((pobi[0] % 2 == 0) || (crong[0] % 2 == 0));

		pobi[1] = pobi[0] + 1;
		crong[1] = crong[0] + 1;

		System.out.println("pobi : " + pobi[0] + ", " + pobi[1]);
		System.out.println("crong : " + crong[0] + ", " + crong[1]);

		System.out.println(solution(pobi, crong));

	}

	static int addarr(int[] arr) { // �迭�� ���� ���ϱ�
		int add = 0;
		for (int i = 0; i < arr.length; i++) {
			add += arr[i];
		}
		return add;
	}

	static int mularr(int[] arr) { // �迭�� ���� ���ϱ�
		int mul = 1;
		arr = mul_exception(arr);
		for (int i = 0; i < arr.length; i++) {
			mul *= arr[i];
			// System.out.println(arr[i]);
		}
		return mul;
	}

	static int[] mul_exception(int[] arr) {
		// �ڸ� �� ������ �ִ� �������� 0�� ��� �迭���� ����
		int[] result = new int[arr.length - 1];
		if (arr[0] == 0) {
			result = mul_exception_zerodrop(arr);
			return mul_exception(result);
		}
		return arr;
	}

	static int[] mul_exception_zerodrop(int[] arr) {
		// mul_exception �Լ����� �迭�� �޾� �ִ� ������ �߶� ��ȯ
		int[] result = new int[arr.length - 1];
		for (int i = 0; i < result.length; i++) {
			result[i] = arr[i + 1];
		}
		return result;
	}

	static int select_max(int x, int y) { // �� �� ū �� ��ȯ
		if (x > y)	{	return x;	}
		if (y > x)	{	return y;	}
		return x;
	}

	static int solution(int[] pobi, int[] crong) {
		if (pobi[0] + 1 != pobi[1] || crong[0] + 1 != crong[1])
			{	return -1;	}
		// ���ܻ���1 : ��ģ ������ ���� ���ӵ��� ���� ���

		if ((pobi[0] | crong[0]) > 399 && (pobi[0] | crong[0]) < 1 && (pobi[1] | crong[1]) > 400
				&& (pobi[0] | crong[0]) < 2)
			{	return -1;	}
		// ���ܻ���2 : ��ģ ������ ��ȣ�� ������ ���� 1~399, ������ 2~400 �� �ƴ� ���

		if ((pobi[0] | crong[0]) % 2 != 1 || (pobi[1] | crong[1]) % 2 != 0)
			{	return -1;	}
		// ���ܻ���3 : ������ ������ ���ʰ� �������� ���� Ȧ��, ¦���� �ƴ� ���
		
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
 * solution �� �̿��Ͽ� �ϱ���� ���� �׷��� ���� ����ó�� �κ��� ������
 */