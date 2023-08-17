package Summer0817;

import java.util.Scanner;




//사분면..
public class Main2 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        int X=sc.nextInt();
        int Y=sc.nextInt();

        if(X>0){
            if(Y>0){
                System.out.print(1);
            }
            else{
                System.out.print(4);
            }
        }
        else{
            if(Y>0){
                System.out.print(2);
            }
            else{
                System.out.print(3);
            }
        }
    } 
}
