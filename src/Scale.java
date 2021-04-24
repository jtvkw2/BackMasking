import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Scale {

    /**
     * Creates the scale and writes to a file
     */
    public void createScale(){
        try {
            String outputSrc = "src/out/scale.dat";
            createFile(outputSrc);
            FileWriter out = new FileWriter(outputSrc);
            out.write("; Sample Rate 8192\n" +
                      "; Channels 1\n" +
                      "               0                0 \n");
            for (int i = 0; i <= 8192; i++) {
                int scaleNum = (i/1000);
                if(scaleNum == 8)
                    scaleNum = 7;
                out.write("0.25      "+String.valueOf(Math.sin(3+Math.PI * i * (261.63 +  ((523.55-261.63)/8192)*i) / 8192))+ "     \n");
            }
            out.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    /**
     * Creates a file based on file name
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
     * Creates new Scale and runs createScale
     * @param args - No inputs arguments
     */
    public static void main(String[] args) {
      Scale scale = new Scale();
      scale.createScale();
    }
}
