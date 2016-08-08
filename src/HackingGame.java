import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class HackingGame {

	public static void main(String[] args) throws FileNotFoundException{

		int wordLength;

		Scanner sc=new Scanner(new File("Dictionary.txt"));
		Scanner input=new Scanner(System.in);
		ArrayList<String> dictionary=new ArrayList<String>();
		while(sc.hasNext()){
			dictionary.add(sc.next());
		}
		sc.close();
		wordLength=10;

		dictionary=filterList(dictionary,wordLength);
		String[] words = extractRandomStrings(dictionary,wordLength);
		int rand=new Random().nextInt(10);

		String targ = words[rand];

		String guess="";
		int tries=5;
		while( !guess.equals(targ) && tries>0 ){
			for(int i=0;i<10;i++){
				System.out.println( words[i] );
			}

			System.out.println();
			System.out.print("Your guess: ");
			guess=input.next().toUpperCase();

			int hit=0;
			for(int i=0;i<guess.length();i++){
				if(guess.charAt(i) == targ.charAt(i)){
					hit++;
				}
			}

			System.out.println(hit+"/"+wordLength+" correct");
			if(hit==wordLength){
				System.out.println("You Win!!!");
			}
			else{
				tries--;
				System.out.println("Tries Remaining: " + tries );
				System.out.println();
				if(tries==0){
					System.out.println("You Lose");
					System.out.println("The word was: " +targ);
				}
			}
		}



		input.close();
	}

	private static String[] extractRandomStrings(ArrayList<String> db, int wordlength) {
		String[] randomWords = new String[10];
		//loop randomly through dictionary list until this array is populated with 10 words of right length
		int i=0;
		while(i<10){
			int spot=new Random().nextInt( (db.size()) );
			if(db.get(spot)!=""){
				randomWords[i]=db.get(spot).toUpperCase();
				db.set(spot,"");
				i++;
			}
		}
		return randomWords;
	}

	static ArrayList<String> filterList(ArrayList<String> db, int wordlength){
		ArrayList<String> filter=new ArrayList<String>();

		for(int i=0;i<db.size();i++){

			if(db.get(i).length()==wordlength){
				filter.add(db.get(i));

			}

		}

		return filter;
	}
}
