import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Map.Entry;
 
public class Ownum {
	//counts words in the file "passage.txt"
	public static void wordCount() throws IOException {
		int count =0;
	    File file = new File("C:/Users/JKpar/eclipse-workspace/Ownum/src/passage.txt");
	    FileInputStream fis = new FileInputStream(file);
	    byte[] bytesArray = new byte[(int)file.length()];
	    fis.read(bytesArray);
	    String s = new String(bytesArray);
	    String[] data = s.split(" ");
	    for (int i=0; i<data.length; i++) {
	    	count++;
	    }
	    System.out.println("Number of words in the given file are "+count);
	}
	
	//populates words from file and calculates frequency of word used
    public Map<String, Integer> wordFrequency(String fileName){
 
        FileInputStream fis = null;
        DataInputStream dis = null;
        BufferedReader br = null;
        Map<String, Integer> wordMap = new HashMap<String, Integer>();
        try {
            fis = new FileInputStream(fileName);
            dis = new DataInputStream(fis);
            br = new BufferedReader(new InputStreamReader(dis));
            String text = null;
            //file reader and tallies word frequency
            while((text = br.readLine()) != null){
                StringTokenizer token = new StringTokenizer(text, " ");
                while(token.hasMoreTokens()){
                    String word = token.nextToken().toLowerCase();
                    if(wordMap.containsKey(word)){
                        wordMap.put(word, wordMap.get(word)+1);
                    } else {
                        wordMap.put(word, 1);
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return wordMap;
    }
    //Ranks words given from populated wordMap; returns only top 10
    public List<Entry<String, Integer>> sorter(Map<String, Integer> wordMap){
         
        Set<Entry<String, Integer>> set = wordMap.entrySet();
        List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(set);
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>()
        {
            public int compare(Map.Entry<String, Integer> i1, Map.Entry<String, Integer> i2)
            {
                return (i2.getValue()).compareTo( i1.getValue() );
            }
        } );
        return list.subList(0, 10);
    }
    //
    public static void sentenceEntry(String word) throws IOException {
    	File f1=new File("C:/Users/JKpar/eclipse-workspace/Ownum/src/passage.txt");
    	FileReader fr = new FileReader(f1);
    	BufferedReader br = new BufferedReader(fr);
    	String[] sentence= null;
    	String s;
    	int x=0;
    	
    	while((s=br.readLine())!=null){
    		int y=0;
    		sentence=s.split("\\.");
    		y++;
    	}	
    	fr.close();
    	x = sentence.length-1;
//		System.out.println(sentence[8]);
    	while(x<sentence.length){	
		if(sentence[x].contains(word)) {
    			System.out.println(sentence[x]+".");
				break;
		}
		else
			x--;
    	}
    }
     
    public static void main(String a[]) throws IOException{
    	int x = 1;
        Ownum mdc = new Ownum();
        Map<String, Integer> wordMap = mdc.wordFrequency("C:/Users/JKpar/eclipse-workspace/Ownum/src/passage.txt");
        List<Entry<String, Integer>> list = mdc.sorter(wordMap);
        String first = list.get(0).toString().replaceAll("[^a-z]", "");
        
        wordCount();
        
        for(Map.Entry<String, Integer> entry:list){
            System.out.println(x+")\t"+entry.getKey());
            x++;
        }
        System.out.println("Last sentence "+first+ " is used:" );
        sentenceEntry(first+" ");
    }
}
