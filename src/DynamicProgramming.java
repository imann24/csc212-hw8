
public class DynamicProgramming {

		public static void main (String[]args) {
			
			int max = 0;
			String s1 = "thisisatest";
			String s2 = "testing123testing";
			//String s1 = "angel";
			//String s2 = "baabelon";
			int length1 = s1.length();
			int length2 = s2.length();
			int i1 = 0;
			int j1 = 0;
			int [][] table = new int [length1+1][length2+1];
			String substring ="";
			for (int i = 1; i < length1+1; i++) {
				for (int j = 1; j <length2+1; j++) {
					if (s1.charAt(i-1) == s2.charAt(j-1)) {
							table[i][j] = 1 + table[i-1][j-1];
					} else {
						table[i][j] = Math.max(table[i-1][j], table[i][j-1]);
					}
			
					if (max < table[i][j]) {
						max = table[i][j];
					}
				}
			}
			i1 = s1.length();
			j1 = s2.length();
			while (i1 > 0 && j1 > 0) {				
				 if (table[i1][j1] == table[i1][j1-1]){
					j1--;
				} else if (table[i1][j1] == table[i1-1][j1]) {
					i1--;
				} else {
					substring += s1.charAt(i1 - 1);
					i1--;
					j1--;
				}
			}
			substring = new StringBuilder(substring).reverse().toString();
			
			System.out.println(max);
			System.out.println(substring);
		}
}
