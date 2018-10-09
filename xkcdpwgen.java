//!/usr/bin/env java

import java.util.ArrayList;
import java.util.Random;
import java.io.BufferedReader;
import java.io.FileReader;

public class xkcdpwgen {

  static Random random = new Random();

  public static void main(String[] args) throws Exception {
    //The next 8 lines are putting all the words in dictionary.txt into an ArrayList
    BufferedReader bufReader = new BufferedReader(new FileReader("dict.txt"));
    ArrayList<String> dict = new ArrayList<>();
    String line = bufReader.readLine();
    while (line != null) {
      dict.add(line);
      line = bufReader.readLine();
    }
    bufReader.close();

    String newPassword = "";
    
    //Checks what arguments are given, this one states what to do when there is no argument
    // (just spit out a four-word lower-case password)
    if (args.length == 0) {
      for (int i=0; i < 4; i++) {
        int index = random.nextInt(dict.size());
        String gen_password = dict.get(index);
        newPassword = newPassword.concat(gen_password);
      }

      System.out.println(newPassword);
    }
    else {
      // Set some initial variables
      boolean numWords = false;
      boolean caps = false;
      boolean numbers = false;
      boolean symbols = false;

      for (String argument: args) {
        //When -h or --help is an argument
        if (argument.equals("-h") || argument.equals("--help")) {
          System.out.println("usage: xkcdpwgen [-h] [-w WORDS] [-c CAPS] [-n NUMBERS] [-s SYMBOLS]");
          System.out.println("");
          System.out.println("Generate a secure, memorable password using the XKCD method");
          System.out.println("");
          System.out.println("optional arguments:");
          System.out.println("-h, --help                        show this help message and exit");
          System.out.println("-w WORDS, --words WORDS           include WORDS words in the password (default=4)");
          System.out.println("-c CAPS, --caps CAPS              capitalize the first letter of CAPS random words (default=0)");
          System.out.println("-n NUMBERS, --numbers NUMBERS     insert NUMBERS random numbers in the password (default=0)");
          System.out.println("-s SYMBOLS, --symbols SYMBOLS     insert SYMBOLS random symbols in the password (default=0)");
          System.out.println("");
        }
        if (argument.equals("-w") || argument.equals("--words")) {
          numWords = true;   
          
        }
        if (argument.equals("-c") || argument.equals("--caps")) {
          caps = true;
        }
        if (argument.equals("-n") || argument.equals("--numbers")) {
          numbers = true;
        }
        if (argument.equals("-s") || argument.equals("--symbols")) {
          symbols = true;
        }
        //When -n or --numbers is an argument
        if (numWords) {
          for (int i=0; i < Integer.parseInt(argument); i++) {
            int index = random.nextInt(dict.size());
            String gen_password = dict.get(index);
            newPassword = newPassword.concat(gen_password);
          }
          System.out.println(newPassword);
        }
        //When -c or --caps is an argument
        if (caps) {
          ArrayList<String> wordList = new ArrayList<String>();
          ArrayList<String> wordCapList = new ArrayList<String>();
          ArrayList<String> newList = new ArrayList<String>();
          
          for (int i=0; i < 4; i++) {
            int index = random.nextInt(dict.size());
            String gen_word = dict.get(index);
            wordList.add(gen_word);
          }
          
          for (int i=0; i < Integer.parseInt(argument); i++) {
            String randomWord = wordList.get(random.nextInt(wordList.size()));
            wordList.remove(randomWord);
            String wordWithCap = randomWord.substring(0, 1).toUpperCase() + randomWord.substring(1);
            wordCapList.add(wordWithCap);
          }

          newList.addAll(wordCapList);
          newList.addAll(wordList);
          
          while (newList.size() > 0) {
            String randomWord = newList.get(random.nextInt(newList.size()));
            newPassword = newPassword.concat(randomWord);
            newList.remove(randomWord);
          }

          System.out.println(newPassword);
        }
        //when -n or --numbers is an argument
        if (numbers) {
          ArrayList<String> wordList = new ArrayList<String>();
          ArrayList<String> wordNumList = new ArrayList<String>();
          ArrayList<String> newList = new ArrayList<String>();
          
          for (int i=0; i < 4; i++) {
            int index = random.nextInt(dict.size());
            String gen_word = dict.get(index);
            wordList.add(gen_word);
          }
          
          for (int i=0; i < Integer.parseInt(argument); i++) {
            String randomWord = wordList.get(random.nextInt(wordList.size()));
            wordList.remove(randomWord);
            String wordWithNum = String.valueOf(random.nextInt(10)).concat(randomWord);
            wordNumList.add(wordWithNum);
          }

          newList.addAll(wordNumList);
          newList.addAll(wordList);
          
          while (newList.size() > 0) {
            String randomWord = newList.get(random.nextInt(newList.size()));
            newPassword = newPassword.concat(randomWord);
            newList.remove(randomWord);
          }

          System.out.println(newPassword);
        }
        //when -s or --symbols is an argument
        if (symbols) {
          String[] symbolsArray = {"!", "~", "@", "#", "$", "%", "^", "*", ".", ":", ";"};
          ArrayList<String> wordList = new ArrayList<String>();
          ArrayList<String> wordSymList = new ArrayList<String>();
          ArrayList<String> newList = new ArrayList<String>();
          
          for (int i=0; i < 4; i++) {
            int index = random.nextInt(dict.size());
            String gen_word = dict.get(index);
            wordList.add(gen_word);
          }
          for (int i=0; i < Integer.parseInt(argument); i++) {
            String randomWord = wordList.get(random.nextInt(wordList.size()));
            wordList.remove(randomWord);
            String wordWithSym = symbolsArray[new Random().nextInt(symbolsArray.length)].concat(randomWord);
            wordSymList.add(wordWithSym);
            
          }

          newList.addAll(wordSymList);
          newList.addAll(wordList);
          
          while (newList.size() > 0) {
            String randomWord = newList.get(random.nextInt(newList.size()));
            newPassword = newPassword.concat(randomWord);
            newList.remove(randomWord);
          }

          System.out.println(newPassword);
        }
        //if an incorrect argument is put in
        else {
          System.out.println("Invalid argument. Type -h for a directory of possible arguments.");
        }
      }
    }
  }
}