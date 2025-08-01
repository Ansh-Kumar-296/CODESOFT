import java.util.*;
class NumberGame{
    public static void main(String[] args) {
        int ch;
        int won=0;
        int r=0;
        Scanner sc=new Scanner(System.in);
        do{  
           int n=(int)((Math.random()*100)+1);
           r++;
            System.out.println("Guess the Correct Number :)");
           for(int i=0;i<3;i++){
             
             int guess=sc.nextInt();
             if(guess==n){
                System.out.println("Hurray!!You guessed the correct number.");
                won++;
                break;
             }
             else if(i==2){
                System.out.println("Sorry!!No chances Left, The correct number was "+n+".");
             }
             else if(guess>n){
                System.out.println("Oops the guessed number is too big,"+(3-i-1)+" chances remaining. Try again!!");
             }
             else{
                 System.out.println("Oops the guessed number is too small,"+(3-i-1)+" chances remaining. Try again!!");
             }
           }
           System.out.println("Do you want to play again? Press '1' to play again and '0' to end the game.");
           ch=sc.nextInt();
           
         }
         while(ch!=0);
         System.out.println("Here are the final scores:");
         System.out.println("Total no. of rounds attempted is "+r);
         System.out.println("Total no. of rounds won is "+won);
    }
}
