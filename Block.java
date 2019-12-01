package ist.psu.edu;

import java.util.ArrayList;
import java.util.Date;
import java.security.MessageDigest;



enum PaymentType {cash, credit, debit}


public class Block {

    //public data variables
    public String hash;
    public String previousHash;
    //private data variables
    private String description;
    private long timeStamp;
    private int transaction;
    private PaymentType paymentType;

    //Constructor method for Block class
    public Block(String prevHash){
        this.previousHash = prevHash;
        this.hash = calculateHash();
        this.timeStamp = new Date().getTime();

    }
    //get-set values for variables
    public void setPaymentType(PaymentType paymentType){this.paymentType = paymentType;}
    public PaymentType getPaymentType(){return paymentType;}

    public void setTransaction(int transaction) { this.transaction = transaction; }
    public int getTransaction(){ return transaction; }

    public void setDescription(String description) {this.description = description;}
    public String getDescription() { return description; }

    //Method that prints out the information
    public static void listTransactions(ArrayList<Block> tList) {
        for (Block transaction : tList) {

            System.out.println("Current Hash: " + transaction.calculateHash());
            System.out.println("Previous Hash: " + transaction.hash);
            System.out.println("Transaction complete: " + transaction.getTransaction() + " dollar(s) has been added to your account");
            System.out.println("Reason for Transaction: " + transaction.getDescription());
            System.out.println("PaymentType: " + transaction.getPaymentType());
            System.out.println("Time Stamp: " + transaction.timeStamp);
            System.out.println("");



        }
    }

    //Method that uses sha256
    public static String useSha1(String value){
        try{
            //variable that gets sha256 from MessageDigest
            MessageDigest message = MessageDigest.getInstance("SHA-1");

            //creates the hash by getting bytes from a string
            byte[] byteHash = message.digest(value.getBytes("UTF-16"));

            StringBuffer hexadecimal = new StringBuffer();

            //loop used to get the length of the string
            for(int i = 0; i < byteHash.length; i++){
                String hex = Integer.toHexString(0xff & byteHash[i]);
                if(hex.length() == 1) hexadecimal.append('0');
                hexadecimal.append(hex);
            }
            return hexadecimal.toString();
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    //function used to calculate the current hash
    public String calculateHash(){
        String calcHash = useSha1(previousHash + Long.toString(timeStamp)
                + getDescription() + getTransaction()
                +getPaymentType());

        return calcHash;
    }


}
