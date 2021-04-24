import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

/**
 * Backmasing class to reverse an .dat file
 */
public class BackMasking {

    /**
     * Runs backmasing with Stacks
     * @param in - input file name
     * @param out - output file name
     */
    public void runST(String in, String out){
        Stack<String> forward = new Stack<>();
        Stack<String> backwards = new Stack<>();

        try {
            Scanner file = new Scanner(new File("src/data_files/"+in));
            while (file.hasNextLine()) {
                String line = file.nextLine();
                forward.push(line);
            }

            int size = forward.size();
            for (int i = 0; i < size; i++) {
                backwards.add(forward.pop());
            }
            for (int x = 0; x < 2; x++){
                backwards.add(0, backwards.get(size - 1));
                backwards.remove(backwards.size() - 1);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        try {
            String outputSrc = "src/out/"+out;
            createFile(outputSrc);
            FileWriter output = new FileWriter(outputSrc);
            for (String backward : backwards) {
                output.write(backward + "\n");
            }
            output.close();
        }catch (IOException e){
            e.printStackTrace();
        }


    }

    /**
     * BackMasking method using Arraylists
     * @param in - input name
     * @param out - output name
     */
    public void runAL(String in, String out){
        ArrayList<String> forward = new ArrayList<>();
        ArrayList<String> backwards = new ArrayList<>();

        try {
            Scanner file = new Scanner(new File("src/data_files/"+in));
            while (file.hasNextLine()) {
                String line = file.nextLine();
                forward.add(line);
            }

            int size = forward.size();
            for (int i = 0; i < size; i++) {
                backwards.add(forward.get(size-i-1));
            }
            for (int x = 0; x < 2; x++){
                backwards.add(0, backwards.get(size - 1));
                backwards.remove(backwards.size() - 1);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        try {
            String outputSrc = "src/out/"+out;
            createFile(outputSrc);
            FileWriter output = new FileWriter(outputSrc);
            for (String backward : backwards) {
                output.write(backward + "\n");
            }
            output.close();
        }catch (IOException e){
            e.printStackTrace();
        }


    }

    /**
     * Method to create a new file
     * @param out - output filename
     */
    public void createFile(String out){
        try{
            File file = new File(out);
            if(file.createNewFile()){
                System.out.println("File created: "+ file.getName());
            }else{
                System.out.println("File already exist");
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Main method takes three arguments and runs new BackMask
     * @param args - arg0 - input name
     *               arg1 - output name
     *               arg2 - if true uses arrayLists else stacks
     */
    public static void main(String[] args) {
        BackMasking back = new BackMasking();
        if(args.length > 2)
            if (!args[2].equals("true")) {
                back.runST(args[0], args[1]);
            } else {
                back.runAL(args[0], args[1]);
            }
        else{
            back.runST(args[0], args[1]);
        }
    }
}
