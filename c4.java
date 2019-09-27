import java.util.Scanner;
public class c4 {
	public static void main(String[] args) {
		String[][] c4 = new String[6][7];
		CB(c4);
		while(RW(c4) == false || YW(c4) == false) {
			RT(c4);
			PB(c4);
			if(RW(c4) == true) {
				System.out.println("Red player won!");
				break;
			}
			YT(c4);
			PB(c4);
			if(YW(c4) == true) {
				System.out.println("Yellow player won!");
				break;
			}
			}
		if(RW(c4) != true && YW(c4) != true){
				System.out.println("No player won! It's a tie!");
			}
		}	

	
	//Red turn
	public static void RT(String[][] c4) {
		boolean t = false;
		Scanner in = new Scanner(System.in);
		System.out.println("Red player's turn!\nDrop a red disk at column(0-6):");
		int r = in.nextInt();
		do {
			if(r<0 || r>6) {
				t=true;
				System.out.println("Incorrect input try again!\nDrop a red disk at column(0-6):");
				r =in.nextInt();
			}else {
				t=false;
				for(int i =5;i>=0;i--) {
					if(c4[i][r] == " ") {
						c4[i][r] = "R";
						break;
					}}}
		}while(t==true);
	}
	
	//Yellow turn
	public static void YT(String[][] c4) {
		boolean t = false;
		Scanner in = new Scanner(System.in);
		System.out.println("Yellow player's turn \nDrop a yellow disk at column(0-6):");
		int y = in.nextInt();
		do {
			if(y<0 || y>6) {
				t=true;
				System.out.println("Incorrect input try again!\nDrop a yellow disk at column(0-6):");
				y =in.nextInt();
			}else {
				t=false;
				for(int i =5;i>=0;i--) {
					if(c4[i][y] == " ") {
						c4[i][y] = "Y";
						break;
				}}}
		}while(t==true);
	}
	
	//Checks if there is a row of 4 R on the board (vertical, horizontal, ascending diagonal, and descending diagonal. 
	//If there is boolean w is changed to True, thus Red wins.
	public static boolean RW(String[][] c4) {
		boolean w = false;
		
		//horizontal
		for(int i =0;i<6;i++) {
			for(int j=0;j<4;j++) {
				if(c4[i][j] == "R" && c4[i][j+1] == "R" && c4[i][j+2] == "R" && c4[i][j+3] == "R") {
					w=true;
					break;
				}}}
		
		//vertical
		for(int i=0;i<7;i++) {
			for(int j =0;j<3;j++) {
				if(c4[j][i] == "R" && c4[j+1][i] == "R" && c4[j+2][i] == "R" && c4[j+3][i] == "R") {
					w = true;
					break;
				}}}
		
		// ascending diagonal
		for(int i=0; i<3;i++) {
			for(int j =0; j<4;j++) {
				if(c4[i][j] == "R" && c4[i+1][j+1] == "R" && c4[i+2][j+2] == "R" && c4[i+3][j+3] == "R") {
					w=true;
					break;
				}}}
		
		//descending diagonal
		for(int i=5; i>=3;i--) {
			for(int j = 0; j<4;j++) {
				if(c4[i][j] == "R" && c4[i-1][j+1] == "R" && c4[i-2][j+2] == "R" && c4[i-3][j+3] == "R") {
					w=true;
					break;
				}}}	
		return w;
	}
	
	//Checks if there is a row of 4 Y on the board (vertical, horizontal, ascending diagonal, and descending diagonal. 
	//If there is boolean w is changed to True, thus Yellow wins.
	public static boolean YW(String[][] c4) {
		boolean w = false;
		
		//horizontal
		for(int i =0;i<6;i++) {
			for(int j=0;j<4;j++) {
				if(c4[i][j] == "Y" && c4[i][j+1] == "Y" && c4[i][j+2] == "Y" && c4[i][j+3] == "Y") {
					w=true;
				}}}
		
		//vertical
		for(int i=0;i<7;i++) {
			for(int j =0;j<3;j++) {
				if(c4[j][i] == "Y" && c4[j+1][i] == "Y" && c4[j+2][i] == "Y" && c4[j+3][i] == "Y") {
					w = true;
				}}}
		
		// ascending diagonal
		for(int i=0; i<3;i++) {
			for(int j =0; j<4;j++) {
				if(c4[i][j] == "Y" && c4[i+1][j+1] == "Y" && c4[i+2][j+2] == "Y" && c4[i+3][j+3] == "Y") {
					w=true;
					break;
				}}}
				
		//other diagonal
		for(int i=5; i>=3;i--) {
			for(int j = 0; j<4;j++) {
				if(c4[i][j] == "Y" && c4[i-1][j+1] == "Y" && c4[i-2][j+2] == "Y" && c4[i-3][j+3] == "Y") {
					w=true;
					break;
				}}}	
		return w;
	}
	
	//Creates Board
	public static void CB(String[][] c4) {
		for(int i=0;i<c4.length;i++){
			for(int j=0;j<c4[i].length;j++) {
				c4[i][j] = " ";
			}}}
	
	//Prints Board
	public static void PB(String[][] c4) {
		for(int i=0;i<c4.length;i++) {
			System.out.print("|");
			for(int j=0;j<c4[i].length;j++) {
				System.out.print( c4[i][j] + "|");
			}
			System.out.println();
		}}
	
}
