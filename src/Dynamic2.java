
public class Dynamic2 {
	public static void main (String[]args) {
		String a = "this is a test";
		String b = "i'm testing 1 2 3 testing";
		int max = 0;
		int[][]lengths = new int [a.length()+1][b.length()+1];
		 for (int i = 0; i < a.length(); i++)
		        for (int j = 0; j < b.length(); j++) {
		            if (a.charAt(i) == b.charAt(j))
		                lengths[i+1][j+1] = lengths[i][j] + 1;
		            else
		                lengths[i+1][j+1] =Math.max(lengths[i+1][j], lengths[i][j+1]);
		            if (max < lengths[i+1][j+1])
		            	max = lengths[i+1][j+1];
		        }
		 // read the substring out from the matrix
		    StringBuffer sb = new StringBuffer();
		    for (int x = a.length(), y = b.length();
		         x != 0 && y != 0; ) {
		        if (lengths[x][y] == lengths[x-1][y])
		            x--;
		        else if (lengths[x][y] == lengths[x][y-1])
		            y--;
		        else {
		            assert a.charAt(x-1) == b.charAt(y-1);
		            sb.append(a.charAt(x-1));
		            x--;
		            y--;
		        }
		    }
		 
		   System.out.println(sb.reverse().toString());
		 System.out.println(max);
	}
}
