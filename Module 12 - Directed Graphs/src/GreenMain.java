import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

/**
 * Program for generating kanji component dependency order via topological sort.
 *
 * @author Jason Green, jgreen44@asu.edu
 * @version 1.0
 */
public class GreenMain {

    private static BetterDiGraph directedGraph;


    /**
     * Entry point for testing.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {


        directedGraph = new BetterDiGraph();
        try {
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(
                                    new File("SER 222 - Data Structures and Algorithms\\Development\\Module 12 - Directed Graphs\\data-kanji.txt")), StandardCharsets.UTF_8));
            HashMap<Integer, Integer> dataKanji = new HashMap<>();
            String firstCharacter;
            while ((firstCharacter = bufferedReader.readLine()) != null) {
                if (!firstCharacter.contains("#")) {
                    String[] split = firstCharacter.split("\t");

                    int node = Integer.parseInt(split[0]);
                    int edgeTo = Character.codePointAt(split[1], 0);
                    dataKanji.put(node, edgeTo);
                    directedGraph.addVertex(node);

                }
            }

            bufferedReader = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(
                                    new File("SER 222 - Data Structures and Algorithms\\Development\\Module 12 - Directed Graphs\\data-components.txt")), StandardCharsets.UTF_8));

            while ((firstCharacter = bufferedReader.readLine()) != null) {
                if (!firstCharacter.contains("#")) {
                    String[] split = firstCharacter.split("\t");

                    int node = Integer.parseInt(split[0]);
                    int edgeTo = Integer.parseInt(split[1]);

                    directedGraph.addEdge(node, edgeTo);

                }
            }

            bufferedReader.close();
            for (Integer i : directedGraph.vertices()) {
                System.out.print(String.valueOf(Character.toChars(dataKanji.get(i))));
            }
            System.out.println("");
            TopologicalSort topSort = new IntuitiveTopological(directedGraph);
            for(Integer i : topSort.order()){
                System.out.print(String.valueOf(Character.toChars(dataKanji.get(i))));
            }




        } catch (IOException e) {
            System.out.println("File not found!");
        }
    }
}