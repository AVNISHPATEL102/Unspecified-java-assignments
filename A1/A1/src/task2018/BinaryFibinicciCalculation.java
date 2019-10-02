package task2018;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class BinaryFibinicciCalculation {
public static void main(String args[]) {
List<String> outputList = new ArrayList<String>();
long startTime = System.currentTimeMillis();
long endTime = System.currentTimeMillis();
for (int i = 5; i < 50;) {
System.out.println();
i = i + 5;
endTime = System.currentTimeMillis();
outputList.add("i = " + i + ", Value = " + fib(i)
+ ", Time Taken = " + (endTime - startTime)+ "\n");
startTime = endTime;
}
writeFile(outputList);
}
private static long fib(int n) {
if (n <= 1)
return n;
return fib(n - 1) + fib(n - 2);
}
private static void writeFile(List<String> stringList) {
BufferedWriter bw = null;
FileWriter fw = null;
try {
fw = new FileWriter("\\\\Users\\\\mr.a\\\\Desktop\\java1.txt");
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
