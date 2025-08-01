import java.util.*;
public class StudentGradeCalculator {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the number of subjects");
        int n=sc.nextInt();
        int sum=0;
        for(int i=1;i<=n;i++){
            System.out.println("Enter the marks for Subject "+i);
            int marks=sc.nextInt();
            sum+=marks;
        }
        int per=sum/n;
        String grade;
        if(per>=90)
            grade="A+";
        else if(per>=80 && per<90)
            grade="A";
        else if(per>=70 && per<80)
            grade="B+";
        else if(per>=60 && per<70)
            grade="B";
        else if(per>=50 && per<60)
            grade="C+";
        else if(per>=40 && per<50)
            grade="C";
        else
            grade="D";
        System.out.println("Total Marks="+sum);
        System.out.println("Percentage="+per+"%");
        System.out.println("Grade:"+grade);
    }
}
