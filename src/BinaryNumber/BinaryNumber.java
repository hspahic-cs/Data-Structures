package BinaryNumber;

import java.util.Arrays;

class BinaryNumber {
    private int data[];
    private int length;
    
    /*
    * Contructs BinaryNumber as array of all zeroes given length 'length'
    * @param length The length of array
    */
    public BinaryNumber(int length){
        this.length = length;
        this.data = new int[length];
    }

     /*
    * Contructs BinaryNumber as array with elements given in string
    * @param str The string version of the binary number
    */
    public BinaryNumber(String str){
        this.data = new int[str.length()];
        for (int i = 0; i < str.length(); i++){
            char temp = str.charAt(i);
            if (temp != '0' && temp != '1'){
                throw new IllegalArgumentException ("You must input a BINARY NUMBER!!!!!");
            }
            data[i] = Character.getNumericValue(temp);
        }
        this.length = str.length();
    }


    public int getLength(){
        return length;
    }
    
    /*
    * Returns integer array representing binary number
    */
    public int[] getInnerArray(){
        return data;
    }
    
    /*
    * Returns integer at given index in BinaryNumber
    * @param index Index of element to be returned
    * @throws IllegalArgumentException If index out of bounds of list
    */

    public int getDigit(int index){
        if (index < 0 || index >= length){
            throw new IllegalArgumentException ("Index out of bounds!");
        }

        return data[index];
    }

    /*
    * Converts Binary Number into decimal form and returns result
    */
    public int toDecimal(){
        int bTen = 0;
        for(int i = length - 1; i >= 0; i--){
          bTen += data[i] * Math.pow(2, (length - 1) - i);   
        } 
        return bTen;
    }

    /*
    * Shifts all bits to left or right in array given diretion
    * @param direction Direction of shift | -1 --> Left && 1 --> Right
    * @param amount Specifies by how many digits array needs to be shifted
    * @throws IllegalArgumentException If direction != 1 || -1
    */
    public void bitShift(int direction, int amount){
        if (direction != 1 && direction != -1){
            throw new IllegalArgumentException("Invalid direction!");
        }
        if (amount < 0){
            throw new IllegalArgumentException("Amount must be >= 0!");
        }
        if (direction == 1 && amount > length){
            throw new IllegalArgumentException("Cannot delete values that don't exists!");
        }

        if (direction == 1){
            int [] temp = new int[length - amount];
            for(int i = 0; i < (temp.length); i++){
                temp[i] = data[i];
            }
            data = temp;
        }
        else{
            int [] temp = new int[length + amount];
            for(int i = 0; i < (length); i++){
                temp[i] = data[i];
            }
            data = temp;
        }
        length = data.length;
    }
    
       /*
    * Computes the 'bitwise or' of 2 BinaryNumbers and returns the result
    * @param bn1 First BinaryNumber
    * @param bn1 Second Binary Number
    * @throws IllegalArgumentException If length of bn1 != length of bn2
    */
    public static int[] bwor(BinaryNumber bn1, BinaryNumber bn2){
        if(bn1.getLength() != bn2.getLength()){
            throw new IllegalArgumentException("BinaryNumber lengths are not the same!");
        }

        int[] temp =  new int[bn1.getLength()];
        for (int i = 0; i < bn1.getLength(); i++){
            if (bn1.getDigit(i) == 0 && bn2.getDigit(i) == 0){
                temp[i] = 0;
            }
            else{
                temp[i] = 1;
            }
        }
        return temp;
    }

    /*
    * Computes the 'bitwise and' of 2 BinaryNumbers and returns the result
    * @param bn1 First BinaryNumber
    * @param bn1 Second Binary Number
    * @throws IllegalArgumentException If length of bn1 != length of bn2
    */
    public static int[] bwand(BinaryNumber bn1, BinaryNumber bn2){
        if(bn1.getLength() != bn2.getLength()){
            throw new IllegalArgumentException("BinaryNumber lengths are not the same!");
        }

        int[] temp =  new int[bn1.getLength()];
        for (int i = 0; i < bn1.getLength(); i++){
            if (bn1.getDigit(i) == 1 && bn2.getDigit(i) == 1){
                temp[i] = 1;
            }
            else{
                temp[i] = 0;
            }
        }
        return temp;
    }

    /*
    * Returns BinaryNumber as encoded string
    */

    public String toString(){
        StringBuilder s = new StringBuilder();
        for(int i = 0; i < length; i++){
            s.append(data[i]);
        }
        
        return s.toString();
    }

    /* 
     * Adds bitwise specified BinaryNumber to class BinaryNumber
     * @param bn1 BinaryNumber to be added to class BinaryNumber
    */

    
    public void add(BinaryNumber bn1){
        if (length != bn1.length){
            if (length > bn1.length){
                bn1.prepend(length - bn1.length);
            }
            else{
                this.prepend(bn1.length - length);
            }
        }
        // Now lengths are the same
        int[] temp = new int[length + 1];
        int carry = 0;
        int result = 0;

        for(int i = length - 1; i >= 0; i--){
            result = data[i] + bn1.data[i] + carry;
            if (result >= 2) {
                carry = 1;
            }
            else{
                carry = 0;
            }
            
            System.out.println("Result: " + result + " | " + "Index: " + i + " | " + "Temp: " + Arrays.toString(temp));
            temp[i + 1] = result % 2;
        }

        if (carry == 1){
            temp[0] = 1;
            length = temp.length;
            data = temp;
        }
        
        else{
            int[] newtemp = new int[length];
            for (int i = 1; i < temp.length; i++){
                newtemp[i - 1] = temp[i];
            }
            data = newtemp;
        }   
    }
    
    /*
    *   Helper for "add" method, prepends 0's to array equal to given amount
    *   @param amount Number of zeroes to prepend
    *
    */

    public void prepend(int amount){
        int[] temp = new int[amount + length];
        for (int i = amount; i < amount + length; i++){
            temp[i] = data[i - amount];
        }
        length += amount;
        data = temp;
    }


/*
    public static void main(String args[]){
        BinaryNumber bn1 = new BinaryNumber("01101");
        BinaryNumber bn2 = new BinaryNumber("01001");
        System.out.println("Bn1 Length: " + bn1.getLength() + " | " + "Bn1 Length: " + bn2.getLength());
        System.out.println("Bn1 : "  + bn1);
        //System.out.println("Bn2 : " + bn2);
        //System.out.println("Bn1 / Index = 4 " + " | " +  bn1.getDigit(4));
        //System.out.println("Bn2 in decimal: " + bn2.toDecimal()); // Should be 28
        //System.out.println("Bn1 || Bn2 ==> " + Arrays.toString(bwor(bn1, bn2)));
        //System.out.println("Bn1 && Bn2 ==> " + Arrays.toString(bwand(bn1, bn2)));
        //bn1.bitShift(-1, 3);
        //System.out.println("BitShift| A = 3 & D = -1 | " + bn1);
        //bn2.bitShift(1, 2);
        //System.out.println("BitShift| A = 2 & D = 1 | " + bn2);
        //bn1.prepend(3);
        //System.out.println(bn1);
        bn1.add(bn2);
        System.out.println(bn1);
        
    }
*/
    
}