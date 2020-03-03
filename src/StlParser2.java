import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class StlParser2 {
	//Choose ASCII stl to parse
	public File model = new File("src/toothed_pulley.stl");
	public ArrayList<Facet> facets =  new ArrayList<>(); //Arraylist to store all facets of the model
	public int facetCount = 0;	//Facet counter to keep track of how many facets in a model
	public int vertexCount = 0;	//Vertex counter to keep track of how many vertices have been stored per facet
	BufferedReader br;	//The type of reader used to scan the file
	
	public StlParser2() throws IOException {
		System.out.println("Parser Running...");
		StlParse(model); //Begin parsing of the model
		System.out.println("No. of facets: " + facets.size()); //Print total amount of facets stored in array
		System.out.println("Parser Finished...");
		printFacets(); //Print all facets and their vertices
		System.out.println("DONE");
	}
	
	public void StlParse(File file) throws IOException {
		try{
			br = new BufferedReader(new FileReader(model));
		}catch(FileNotFoundException e) {
			System.out.println("File not found...");
		}
		
		String line = "";
		line = br.readLine(); //Stores lines of file in line string
		//Initialise the global coords
		CartesianCoordinate facetNormal = null;
		CartesianCoordinate vertex1 = null;
		CartesianCoordinate vertex2 = null;
		CartesianCoordinate vertex3 = null;
		
		//Loop until the end of the file is reached
		while(line !=  null) {
			if(line.contains("facet normal")) { //If facet coords are found
				facetCount++;
				String[] normal = line.trim().split("\\s+"); //Split stored line into an array of words, also removes whitespace infront and behind string
				//Store and parse the numeric values from the array of strings
				facetNormal = new CartesianCoordinate(Double.valueOf(normal[2]),Double.valueOf(normal[3]) , Double.valueOf(normal[4]));
			} else if(line.contains("vertex")) { //If vertex is found in stored line
				String[] vertex = line.trim().split("\\s+");
				if(vertexCount == 0) { //If no vertices have been stored
					vertex1 = new CartesianCoordinate(Double.valueOf(vertex[1]), Double.valueOf(vertex[2]), Double.valueOf(vertex[3]));
					vertexCount++;
				}else if(vertexCount == 1) { //If only 1 vertex has been stored
					vertex2 = new CartesianCoordinate(Double.valueOf(vertex[1]), Double.valueOf(vertex[2]), Double.valueOf(vertex[3]));
					vertexCount++;
				}else if(vertexCount == 2){ //If 2 vertices have been stored
					vertex3 = new CartesianCoordinate(Double.valueOf(vertex[1]), Double.valueOf(vertex[2]), Double.valueOf(vertex[3]));
					vertexCount++;
				}
			}
			
			if(vertexCount == 3) { //If all vertices have been stored
				facets.add(new Facet(facetNormal, vertex1, vertex2, vertex3)); //Create facet with these values and add to facet array
				vertexCount = 0; //Reset the vertex counter
			}
			line = br.readLine(); //Stores next line in string
		}
	}
	
	//USED FOR DEBUGGING
	//PRINTS ALL FACETS AND THEIR COORDS
	public void printFacets() {
		for(int i = 0; i< facets.size();i++) {
			System.out.println("FACET NO." + i);
			System.out.println("FACET NORMAL: " + facets.get(i).getNormal().getX() + "," + facets.get(i).getNormal().getY() + "," + facets.get(i).getNormal().getZ());
			System.out.println("VERTEX1: " + facets.get(i).getV1().getX() + "," + facets.get(i).getV1().getY() + "," + facets.get(i).getV1().getZ());
			System.out.println("VERTEX2: " + facets.get(i).getV2().getX() + "," + facets.get(i).getV2().getY() + "," + facets.get(i).getV2().getZ());
			System.out.println("VERTEX3: " + facets.get(i).getV3().getX() + "," + facets.get(i).getV3().getY() + "," + facets.get(i).getV3().getZ());
		}
	}
	
	public static void main(String[] args) throws IOException {
		new StlParser2(); //Create new StlParser object and run the parser
	}
}
