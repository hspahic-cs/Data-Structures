package complexity;

public class Complexity {
    // BigO time complexity of n^2
    public static void method1(int n){
        int calls = 0;
        for (int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                calls ++;
            }
        }
        System.out.println("Number of calls : " + calls + " | Method 1");
    }


    // BigO time complexity of n^3
    public static void method2(int n){
        int calls = 0;
        for (int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                for(int k = 0; k < n; k++){
                    calls ++;
                }
            }
        }
        System.out.println("Number of calls : " + calls + " | Method 2");
    }

    // Time complexity = log(n)
    public static void method3(int n){
        int calls = 0;
        for(int i = 1; i < n; i *= 10){
            calls++;
        }
        System.out.println("Number of calls : " + calls + " | Method 3");
    }

    /*   ----- PROBLEM 4 -----
    Iteration | Start | End || n = 32
    1           0       31
    2           16      31
    3           24      31
    4           28      31
    5           30      31
    6           31      31
    7           32      31 
    RETURNS FALSE

    Iteration | Start | End || n = 64
    1           0       63
    2           32      63
    3           48      63
    4           56      63
    5           60      63
    6           62      63
    7           63      63
    8           64      63 
    RETURNS FALSE
    
        ----- PROBLEM 5 -----
    Number of iterations = (log(n) / log(2)) + 2, where n = a.length()
    
        ----- Problem 6 -----
    Time complexity search = log(n) / log(2)

    */

    // Time complexity = log(n) * n
    public static void method4(int n){
        int calls = 0;
        for(int i = 1; i < n; i *= 10){
            for (int j = 0; j < n; j++){
                calls++;
            }
        }
        System.out.println("Number of calls : " + calls + " | Method 4");
    }

    
    // Time complexity = log(log(n))
    public static void method5(int n){
        int calls = 0;
        for(int i = 1; i < n; i *= Math.pow(10, 10)){  
            calls++;
        }
        System.out.println("Number of calls : " + calls + " | Method 5");
    }

    // Time complexity = 2**n
    public static int method6(int n){
        if (n == 0){
            return 1;
        }
        return method6(n -1) + method6(n-1);
    }

    public static void main(String args[]){
        System.out.println("Why hello there");
        method1(10); // Equals 100 i.e 10**2
        method2(10); // Equals 1000 i.e 10**3
        method3(1000); // Equals 3 i.e log(1000)
        method4(1000); // Equals 3000 i.e log(1000) * 1000 
        method5((int) Math.pow(10,6)); // Equals 1 i.e log(log(10**6)) only gets called once 
        System.out.println(method6(6)); // Equals 64 i.e 2**6 
    }
}
