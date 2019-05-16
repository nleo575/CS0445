import java.text.NumberFormat;
import java.util.*; //Needed for scanner
public class Assig2B 
{

    public static void main(String[] args) 
    {
        //int [] arguments = {10000, 20000, 40000, 80000, 160000};
        int runs = Integer.parseInt(args[0]);
        int revTest = 0; //By default, reverse will not be tested
                         //0 will not run tests.
                         //1 will only run MyStringBuilder & StringBuilder reverse
                         //2 will run String, MyStringBuilder, & StringBuilder reverse
        long start, //Holds start time (ns)
             stop,  //Holds end time (ns)
             space, //Holds length of longest time in order to space results
             cTime; //Holds cumulative time, used in testing reverse operation
        String time; //String will be used to store formatted results

        if(args.length >1) //See if optional 2nd argument to test reverse was provided
            revTest = Integer.parseInt(args[1]);

        if (revTest == 2) //Confirm with use to test all 3 objects' reverse methods
        {   
            System.out.print("\033[H\033[2J"); //clears the console window
            StringBuilder prompt = new StringBuilder("Warning, String reverse");
            prompt.append(" is incredibly slow.\n");
            prompt.append("It is not recommended to test this feature ");
            prompt.append("with more than 10K operations.\n\nIt may take more than ");
            int mins = runs == 10000 
                            ? 5 
                            : runs == 20000 
                            ? 45
                            : runs == 40000 
                            ? 405 
                            : runs == 80000 
                            ? 3645 
                            : 32805;
            prompt.append(mins);
            prompt.append(" minutes to complete this test.");
            System.out.println(prompt);

            Scanner sc = new Scanner(System.in); //Get user's response
            do
            {
                System.out.println("Are you sure you want to run with String Reverse?");
                System.out.print("\t0 = Only run Append/Delete/Insert\n");
                System.out.print("\t1 = Run A/D/I & Reverse for MyStringBuilder ");
                System.out.print("and StringBuilder ONLY\n");
                System.out.println("\t2 = Run A/D/I & Reverse (for all 3)");
                revTest = sc.nextInt();
            }
            while(revTest <0 && revTest > 2);

            if(revTest == 2)
            {
                System.out.println("\nSystem will output String Reverse progress"+
                    " after every 100 reverses.\n");
            }
            else
                System.out.println();
        }

        //for(int x = 0; x < runs.length; x++)
        {
            MyStringBuilder msb = new MyStringBuilder();
            StringBuilder sb = new StringBuilder();
            String str = new String();

            System.out.println("Testing with " +
            NumberFormat.getIntegerInstance().format(runs) + " characters.\n");

            //***************************************************************************
            //Testing append
            //***************************************************************************

            System.out.println("Testing Append:\n");

            // Starting with String
            start = System.nanoTime();
            for(int i = 0; i < runs ; i++)
                str =  str.concat("a");
            stop = System.nanoTime();
            time = NumberFormat.getIntegerInstance().format((stop-start));

            //Format and display run-time
            System.out.println("\tString Total:\t\t" + time);
            space = time.length();
            time = NumberFormat.getIntegerInstance().format((stop-start)/(double) runs );
            System.out.print("\tString Avg:\t\t");
            for (int y = 0;y<(space-time.length());y++ ) 
                System.out.print(" ");    
            System.out.println(time + "\n");


            //Then MyStringBuilder
            start = System.nanoTime();
            for(int i = 0; i < runs ; i++)
                msb.append('A');
            stop = System.nanoTime();
            time = NumberFormat.getIntegerInstance().format((stop-start));

            //Format and display run-time
            System.out.print("\tMyStringBuilder Total:\t");
            for (int y = 0;y<(space-time.length());y++ ) 
                System.out.print(" ");    
            System.out.println(time);
            time = NumberFormat.getIntegerInstance().format((stop-start)/(double) runs );
            System.out.print("\tMyStringBuilder Avg:\t");
            for (int y = 0;y<(space-time.length());y++ ) 
                System.out.print(" ");    
            System.out.println(time + "\n");


            //Last StringBuilder
            start = System.nanoTime();
            for(int i = 0; i < runs ; i++)
                sb.append('A');
            stop = System.nanoTime();

            //Format and display run-time
            time = NumberFormat.getIntegerInstance().format((stop-start));
            System.out.print("\tStringBuilder Total:\t");
            for (int y = 0;y<(space-time.length());y++ ) 
                System.out.print(" ");    
            System.out.println(time);
            time = NumberFormat.getIntegerInstance().format((stop-start)/(double) runs );
            System.out.print("\tStringBuilder Avg:\t");
            for (int y = 0;y<(space-time.length());y++ ) 
                System.out.print(" ");    
            System.out.println(time);


            //***************************************************************************
            //Testing delete
            //***************************************************************************

            System.out.println("\nTesting Delete:\n");


            //Start with String
            start = System.nanoTime();
            for(int i = 0; i < runs ; i++)
                str = str.substring(1);
            stop = System.nanoTime();

            //Format and display run-time
            time = NumberFormat.getIntegerInstance().format((stop-start));
            System.out.println("\tString Total:\t\t" + time);
            space = time.length();
            time = NumberFormat.getIntegerInstance().format((stop-start)/(double) runs );
            System.out.print("\tString Avg:\t\t");
            for (int y = 0;y<(space-time.length());y++ ) 
                System.out.print(" ");    
            System.out.println(time + "\n");


            //Then MyStringBuilder
            start = System.nanoTime();
            for(int i = 0; i < runs ; i++)
                msb.delete(0,1);
            stop = System.nanoTime();

            //Format and display run-time
            time = NumberFormat.getIntegerInstance().format((stop-start));
            System.out.print("\tMyStringBuilder Total:\t");
            for (int y = 0;y<(space-time.length());y++ ) 
                System.out.print(" ");    
            System.out.println(time);
            time = NumberFormat.getIntegerInstance().format((stop-start)/(double) runs );
            System.out.print("\tMyStringBuilder Avg:\t");
            for (int y = 0;y<(space-time.length());y++ ) 
                System.out.print(" ");    
            System.out.println(time+"\n");


            //Last StringBuilder
            start = System.nanoTime();
            for(int i = 0; i < runs ; i++)
                sb.delete(0,1);
            stop = System.nanoTime();

            //Format and display run-time
            time = NumberFormat.getIntegerInstance().format((stop-start));
            System.out.print("\tStringBuilder Total:\t");
            for (int y = 0;y<(space-time.length());y++ ) 
                System.out.print(" ");    
            System.out.println(time);
            time = NumberFormat.getIntegerInstance().format((stop-start)/(double) runs );
            System.out.print("\tStringBuilder Avg:\t");
            for (int y = 0;y<(space-time.length());y++ ) 
                System.out.print(" ");    
            System.out.println(time +"\n");


            //***************************************************************************
            //Testing insert
            //***************************************************************************

            System.out.println("\nTesting Insert:\n");


            //Start with String
            start = System.nanoTime();
            for(int i = 0; i < runs ; i++)
                str = str.substring(0, str.length()/2) +"a" +
                                 str.substring(str.length()/2,str.length());
            stop = System.nanoTime();

            //Format and display run-time
            time = NumberFormat.getIntegerInstance().format((stop-start));
            System.out.println("\tString Total:\t\t" + time);
            space = time.length();
            time = NumberFormat.getIntegerInstance().format((stop-start)/(double) runs );
            System.out.print("\tString Avg:\t\t");
            for (int y = 0;y<(space-time.length());y++ ) 
                System.out.print(" ");    
            System.out.println(time + "\n");

            
            //Then MyStringBuilder
            start = System.nanoTime();
            for(int i = 0; i < runs ; i++)
                msb.insert(msb.length()/2,'A');
            stop = System.nanoTime();

            //Format and display run-time
            time = NumberFormat.getIntegerInstance().format((stop-start));
            System.out.print("\tMyStringBuilder Total:\t");
            for (int y = 0;y<(space-time.length());y++ ) 
                System.out.print(" ");    
            System.out.println(time);
            time = NumberFormat.getIntegerInstance().format((stop-start)/(double) runs );
            System.out.print("\tMyStringBuilder Avg:\t");
            for (int y = 0;y<(space-time.length());y++ ) 
                System.out.print(" ");    
            System.out.println(time+ "\n");


            //Last StringBuilder
            start = System.nanoTime();
            for(int i = 0; i < runs ; i++)
                sb.insert(sb.length()/2,'A');
            stop = System.nanoTime();

            //Format and display run-time
            time = NumberFormat.getIntegerInstance().format((stop-start));
            System.out.print("\tStringBuilder Total:\t");
            for (int y = 0;y<(space-time.length());y++ ) 
                System.out.print(" ");    
            System.out.println(time);
            time = NumberFormat.getIntegerInstance().format((stop-start)/(double) runs );
            System.out.print("\tStringBuilder Avg:\t");
            for (int y = 0;y<(space-time.length());y++ ) 
                System.out.print(" ");    
            System.out.println(time+"\n\n");


            if( revTest > 0)
            {
                //************************************************************************
                //Testing reverse (deleting after in order to test with different lengths)
                //************************************************************************

                cTime = 0; //Resets time counter
                System.out.println("\nTesting Reverse:\n");


                //Start with String

                if (revTest == 2) 
                {
                    //Output so that user can keep track of every 100 reverses

                    System.out.print("String Reverse progress: ");
                    for(int i = 0; i < runs ; i++)
                    {
                        str = sb.substring(i);
                        start = System.nanoTime();
                        int len = str.length();
                        for (int j = 0; j <len; j++) 
                        {
                            str = str.substring(1, len - j) + str.substring(0, 1) +
                                  str.substring(len - j, len);
                        }
                        stop = System.nanoTime();

                        cTime += (stop-start); //Calculates cumulative runtime
                        if(i%100 == 0) //Notifies every 100 reverses
                            System.out.print(i + ", ");
                    }
                    System.out.println (); System.out.println ();
                    //Format and display run-time
                    time = NumberFormat.getIntegerInstance().format(cTime);
                    System.out.println("\tString Total:\t\t" + time);
                    space = time.length();
                    time = NumberFormat.getIntegerInstance().format(cTime/(double) runs );
                    System.out.print("\tString Avg:\t\t");
                    for (int y = 0;y<(space-time.length());y++ ) 
                        System.out.print(" ");    
                    System.out.println(time + "\n");
                }
                else
                {
                    System.out.println("String Reverse not being tested this time.\n");
                }

                cTime = 0; //Resets time counter

                //Then MyStringBuilder
                for(int i = 0; i < runs ; i++)
                {
                    start = System.nanoTime();
                    msb.reverse();
                    stop = System.nanoTime();
                    cTime += (stop-start); //Calculates cumulative runtime

                    msb.delete(0,1); //Deletes 1st char
                }
                //Format and display run-time
                time = NumberFormat.getIntegerInstance().format(cTime);
                System.out.println("\tMyStringBuilder Total:\t\t" + time);
                space = time.length();
                time = NumberFormat.getIntegerInstance().format(cTime/(double) runs );
                System.out.print("\tMyStringBuilder Avg:\t\t");
                for (int y = 0;y<(space-time.length());y++ ) 
                    System.out.print(" ");    
                System.out.println(time + "\n");


                cTime = 0; //Resets time counter
                //Last StringBuilder
                for(int i = 0; i < runs ; i++)
                {
                    start = System.nanoTime();
                    sb.reverse();
                    stop = System.nanoTime();
                    cTime += (stop-start); //Calculates cumulative runtime

                    sb.deleteCharAt(sb.length()-1);; //Deletes last character
                }
                //Format and display run-time
                time = NumberFormat.getIntegerInstance().format(cTime);
                System.out.println("\tStringBuilder Total:\t\t" + time);
                space = time.length();
                time = NumberFormat.getIntegerInstance().format(cTime/(double) runs );
                System.out.print("\tStringBuilder Avg:\t\t");
                for (int y = 0;y<(space-time.length());y++ ) 
                    System.out.print(" ");    
                System.out.println(time + "\n");

            }//End Reverse tests
        }
    }   
}
