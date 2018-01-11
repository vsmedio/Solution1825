import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/* 
Собираем файл
Собираем файл из кусочков.
Считывать с консоли имена файлов.
Каждый файл имеет имя: [someName].partN.

Например, Lion.avi.part1, Lion.avi.part2, ..., Lion.avi.part37.

Имена файлов подаются в произвольном порядке. Ввод заканчивается словом "end".
В папке, где находятся все прочтенные файлы, создать файл без суффикса [.partN].

Например, Lion.avi.

В него переписать все байты из файлов-частей используя буфер.
Файлы переписывать в строгой последовательности, сначала первую часть, потом вторую, ..., в конце - последнюю.
Закрыть потоки.


Требования:
1. Программа должна считывать имена файлов с консоли, пока не будет введено слово "end".
2. Создай поток для записи в файл без суффикса [.partN] в папке, где находятся все считанные файлы.
3. В новый файл перепиши все байты из файлов-частей *.partN.
4. Чтение и запись должны происходить с использованием буфера.
5. Созданные для файлов потоки должны быть закрыты.
*/


public class Solution {
    public static void main(String[] args) throws IOException {
        Map<Integer, String> tm = new TreeMap<Integer, String>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String resultFile = null;

        while (true) {
            String filename = reader.readLine();
            if (filename.equals("end"))
                break;
            resultFile = filename.substring(0, filename.indexOf(".part"));
            int index = Integer.parseInt(
                    filename.substring(filename.indexOf(".part") + 5));
            tm.put(index, filename);
        }

        FileOutputStream out = new FileOutputStream(resultFile);
        for (Map.Entry<Integer, String> item : tm.entrySet()) {
            FileInputStream in = new FileInputStream(item.getValue());
            byte[] buffer = new byte[in.available()];
            in.read(buffer);
            out.write(buffer);
            in.close();
        }
        out.close();
    }
}