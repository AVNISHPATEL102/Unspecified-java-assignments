package task2018;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class TailRecursiveFibCalculation {
private static long fib(long n, long a, long b) {
if (n == 0)
return a;
if (n == 1)
return b;
return fib(n - 1, b, a + b);
}
public static void main(String[] args) {
List<String> outputList = new ArrayList<String>();
long startTime = System.currentTimeMillis();
long endTime = System.currentTimeMillis();
for (long i = 5; i < 50;) {
i = i + 5;
endTime = System.currentTimeMillis();
outputList.add("i = " + i + ", Value = " + fib(i, 0, 1)
+ ", Time Taken = " + (endTime - startTime) + "\n");
startTime = endTime;
}
writeFile(outputList);
}
private static void writeFile(List<String> stringList) {
BufferedWriter bw = null;
FileWriter fw = null;
try {
fw = new FileWriter("\\\\Users\\\\mr.a\\\\Desktop\\java3.txt");
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
