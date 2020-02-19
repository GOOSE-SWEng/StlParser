import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class StlParser2 {
	
	
	public File cube = new File("src/space_invader_magnet.stl");
	public ArrayList<Facet> facets =  new ArrayList<>();
	public int facetCount = 0;
	public int vertexCount = 0;
	BufferedReader br;
	
	public StlParser2() throws IOException {
		System.out.println("Parser Running...");
		StlParse(cube);
		System.out.println("No. of facets: " + facets.size());
		System.out.println("Parser Finished...");
		printFacets();
		System.out.println("DONE");
		
	}
	
	public void StlParse(File file) throws IOException {
		try{
			br = new BufferedReader(new FileReader(cube));
		}catch(FileNotFoundException e) {
			System.out.println("File not found...");
		}
		
		String line = "";
		line = br.readLine();
		CartesianCoordinate facetNormal = null;
		CartesianCoordinate vertex1 = null;
		CartesianCoordinate vertex2 = null;
		CartesianCoordinate vertex3 = null;
		
		while(line !=  null) {
			if(line.contains("facet normal")) {
				facetCount++;
				String[] normal = line.trim().split("\\s+");
				//System.out.println("NORMALS: " + normal[4] + "," + normal[5] + "," + normal[6]);

				facetNormal = new CartesianCoordinate(Double.valueOf(normal[2]),Double.valueOf(normal[3]) , Double.valueOf(normal[4]));
				//System.out.println("FACET NORMAL STORED");

				
			} else if(line.contains("vertex")) {
				String[] vertex = line.trim().split("\\s+");

				if(vertexCount == 0) {
					vertex1 = new CartesianCoordinate(Double.valueOf(vertex[1]), Double.valueOf(vertex[2]), Double.valueOf(vertex[3]));
					vertexCount++;
				}else if(vertexCount == 1) {
					vertex2 = new CartesianCoordinate(Double.valueOf(vertex[1]), Double.valueOf(vertex[2]), Double.valueOf(vertex[3]));
					vertexCount++;
				}else if(vertexCount == 2){
					vertex3 = new CartesianCoordinate(Double.valueOf(vertex[1]), Double.valueOf(vertex[2]), Double.valueOf(vertex[3]));
					vertexCount++;
				}
			}
			
			if(vertexCount == 3) {
				facets.add(new Facet(facetNormal, vertex1, vertex2, vertex3));
				vertexCount = 0;
				//System.out.println("Facet Added, No." + facetCount);
			}
			//Utils.pause(100);
			line = br.readLine();
			//System.out.println(line);
		}
	}
	
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
		new StlParser2();

	}

}
