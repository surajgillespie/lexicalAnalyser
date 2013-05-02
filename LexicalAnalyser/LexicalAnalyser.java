//package lexicalanalyser;
/**
 *
 * @author Suraj K.S
 */
import java.lang.*;
import java.io.*;
import java.util.*;
public class LexicalAnalyser {

   static String filename=("names.txt");
   static BufferedReader ds=new BufferedReader(new InputStreamReader(System.in));
   static String keywords[]={"create","select","update","and","all","begin","check","column","delete","desc","do","each","else","insert","table","join","like","loop","new","from","int","double","char","key","distinct"};
   static String keyId[]={"ky001","ky002","ky003","ky004","ky005","ky006","ky007","ky008","ky009","ky010","ky011","ky012","ky013","ky014","ky015","ky016","ky017","ky018","ky019","ky020","ky021","ky022","ky023","ky024","ky025","ky026"};
   static String operators[]={ " ",".",",","*",";",">","<","<>",">=","<=","&&",")","(","="};
   static String opId[]={"op001","op002","op003","op004","op005","op006","op007","op008","op009","op010","op011","op012","op013","op014"};
   static int sl=0;
 boolean operatorCheck(String c)
 {
        int flag=0,i=0;
        for( i=0;i<operators.length;i++)
           if(c.equals(operators[i]))
             {flag=1;break;
              }  
          
     if(flag==0)
     return false;
     else
     return true;
   }
 
     boolean keywordCheck(String  str)
 {
        int flag=0,i=0;
        for(i=0;i<keywords.length;i++)
           if(str.equalsIgnoreCase(keywords[i]) )
              {flag=1;break;
              }  
           
     if(flag==0)
     {  
       identifierLiteralCheck(str);
       return false;
       }
     else
     {
     System.out.println(++sl+"\t"+str+"\t\t"+keyId[i]+"\t\tKeyword\n");
     return true;}
   }
     
     void identifierLiteralCheck(String str)
     {
           try{
        int dig=Integer.parseInt(str);//if the string is a digit this will not generate an exception
        System.out.println(++sl+"\t"+dig+"\t\t"+dig+"\t\tLiteral\n");     
                }
         catch(Exception e)//exception is generated only if  str is an identifier
         {
             str.trim();//to make sure whitespaces are removed
             if(!(str.equals("")))
             {
                 int key=0;
                 for(int i=0;i<str.length();i++)//token ids for identifiers are created by suming up their asii values
                     key+=str.charAt(i);
              System.out.println(++sl+"\t"+str+"\t\tid"+key+"\t\tIdentifier\n");   
             }
         }
     }
     
 public static void main(String[] args)throws IOException 
 { 
     LexicalAnalyser obj=new LexicalAnalyser();
     //PrintWriter outFile=new PrintWriter(new BufferedWriter(new FileWriter(filename)));
     //outFile.println("select * from table abc;");
     //outFile.println("create table xyz(int akl );");
     //outFile.println("CREATE TABLE Enrolled ( studid CHAR(20) ,cid CHAR(20),grade CHAR(10),PRIMARY KEY (studid, cid),FOREIGN KEY (studid) REFERENCES Students)");
     //outFile.println("SELECT DISTINCT S.sname FROM Sailors S where age=20");
     //outFile.println("SELECT B.bid, COUNT (*) AS reservationcount FROM Boats B, Reserves R WHERE R.bid = B.bid AND B.color = 'red'GROUP BY B.bid");
    // outFile.println(". . . ");
     //outFile.close();
     BufferedReader fileInput=new BufferedReader(new FileReader("names.txt"));

     String text;
     
     System.out.println("---------------------------------------------------------------");   
    while((text=fileInput.readLine())!=null)
   {    
       String dup=text;
       System.out.println("input scanned: "+text);
        System.out.println("----------------------------------------------------");
        System.out.println("Sl no.\tTOKEN\t\tTOKEN ID\tTYPE ");
        for(int i=0;i<text.length();i++)
       {
           String identifiedToken="";
           String c = Character.toString(dup.charAt(i)) ;
           
           while((!obj.operatorCheck(c)))
           {
                identifiedToken=identifiedToken+c;
                if(i+1>=text.length())
                break;
                else
                   i++;
                c = Character.toString(dup.charAt(i));
             }
           
           if(obj.keywordCheck(identifiedToken))
           {
             //System.out.println("keyword found: "+identifiedToken);  
           }
            if(obj.operatorCheck(c))
           { int j=0;
               for(  j=0;j<operators.length;j++)
           if(c.equals(operators[j]))
             break;
            if(!c.equals(" "))    
          System.out.println(++sl+"\t"+c+"\t\t"+opId[j]+"\t\tOperator\n");
           }
           
       }
        System.out.println();
    }
   fileInput.close();
      System.out.println("------------------------------------------------------------------");  
}
}



