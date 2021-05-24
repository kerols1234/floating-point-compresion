package com.company;

import java.io.*;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        try {
            floatingPiontCompresion("file.txt");
            floatingPiontDecompresion("comp.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void floatingPiontDecompresion(String fileName) throws IOException {
        HashMap<Character, Double> map = new HashMap<>();
        HashMap<Integer, Character> c = new HashMap<>();
        String str, data = "";
        double f = 0, Size = 0;
        File file = new File(fileName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        str = br.readLine();
        Size = Double.parseDouble(str);
        str = br.readLine();
        f = Double.parseDouble(str);
        String[] t = new String[2];
        int j = 0;
        while ((str = br.readLine()) != null) {
            t = str.split(" ");
            map.put(t[0].charAt(0), Double.parseDouble(t[1]));
            c.put(j, t[0].charAt(0));
            j++;
        }
        br.close();
        fr.close();

        double[] arr = new double[c.size() + 1];
        arr[0] = 0;
        arr[1] = map.get(c.get(0)) / Size;
        for (int i = 0; i < c.size(); i++) {
            arr[i + 1] = (map.get(c.get(i)) / Size) + arr[i];
        }

        j = 0;
        double low = 0, high = 1, range = 1;
        int z = 0;
        do {
            for (int i = 0; i < c.size(); i++) {
                if (arr[i] <= f && arr[i+1] >= f) {
                    z = i;
                }
            }
            data += c.get(z);
            high = arr[z + 1];
            low = arr[z];
            range = high - low;
            f = (f - low)/range;
            j++;
        } while (j < Size);

        FileWriter fileWriter = new FileWriter("decomp.txt");
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        bufferedWriter.write( data);

        bufferedWriter.close();
        fileWriter.close();
    }

    public static void floatingPiontCompresion(String fileName) throws IOException {

        HashMap<Character, Double> map = new HashMap<>();
        HashMap<Integer, Character> c = new HashMap<>();
        String str, data = "";
        double Size = 0;
        File file = new File(fileName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        while ((str = br.readLine()) != null) {
            for (int i = 0; i < str.length(); i++) {
                if (map.containsKey(str.charAt(i))) {
                    map.put(str.charAt(i), map.get(str.charAt(i)) + 1);
                } else {
                    map.put(str.charAt(i), (double) 1);
                    c.put(c.size(), str.charAt(i));
                }
            }
            data += str;
        }
        Size = data.length();
        br.close();
        fr.close();

        double[] arr = new double[c.size() + 1];
        arr[0] = 0;
        arr[1] = map.get(c.get(0)) / Size;
        for (int i = 0; i < c.size(); i++) {
            arr[i + 1] = (map.get(c.get(i)) / Size) + arr[i];
        }
        int j = 0;
        double low = 0, high = 1, range = 1;
        int z = 0;
        do {
            for (int i = 0; i < c.size(); i++) {
                if (data.charAt(j) == c.get(i)) {
                    z = i;
                }
            }
            high = low + (range * arr[z + 1]);
            low = low + (range * arr[z]);
            range = high - low;
            j++;
        } while (j < data.length());

        FileWriter fileWriter = new FileWriter("comp.txt");
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        bufferedWriter.write(String.valueOf(Size) + '\n');
        bufferedWriter.write(String.valueOf(low + ((high - low) / 2)) + '\n');
        for (int i = 0; i < c.size(); i++) {
            bufferedWriter.write(c.get(i) + " " + map.get(c.get(i)) + '\n');
        }

        bufferedWriter.close();
        fileWriter.close();
    }
}
