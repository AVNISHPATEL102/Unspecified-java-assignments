package task2018;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class LinearFibonicciCalculation {
private static long fib(int num) {
long f[] = new long[num + 1];
int indx;
f[0] = 0;
f[1] = 1;
for (indx = 2; indx <= num; indx++) {
f[indx] = f[indx - 1] + f[indx - 2];
}
return f[num];
}
public static void main(String args[]) {
List<String> outputList = new ArrayList<String>();
long startTime = System.currentTimeMillis();
long endTime = System.currentTimeMillis();
for (int i = 5; i < 50;) {
i = i + 5;
endTime = System.currentTimeMillis();
outputList.add("i = " + i + ", Value = " + fib(i)
+ ", Time Taken = " + (endTime - startTime)+"\n");
startTime = endTime;
}
writeFile(outputList);
}
private static void writeFile(List<String> stringList) {
BufferedWriter bw = null;
FileWriter fw = null;
try {
fw = new FileWriter("\\\\Users\\\\mr.a\\\\Desktop\\java2.txt");
bw = new BufferedWriter(fw);
for (String line : stringList) {
bw.write(line);
bw.write("\n");
}
System.out.println(" File Writing Done");
} catch (IOException e) {
e.printStackTrace();
} finally {
try {
if (bw != null)
bw.close();
if (fw != null)
fw.close();
} catch (IOException ex) {
ex.printStackTrace();
}
}
}
}
