import java.util.Stack;

public class KevinBacon{
	
	public KevinBacon(){
	
	}
	
	public void getBaconNumber(String actor){
		if(actor.equals("Kevin Bacon")){
			//TODO output path and 0
		}
		
		Stack actorPath = new Stack();
		UndirectedGraph<String> graph = new UndirectedGraph<>();
		
		
		
		System.out.println(actor + " is " + actorPath.size() + " steps away from Kevin Bacon.");
		System.out.println("Path: " + actorPath);
	}
	/*
	Example of output:
	? Garbo, Greta
	"Garbo, Greta" is 2 steps away from Kevin B. The path is <a>Bacon, Kevin
	(I)<a><t>FrostNixon (2008)<t><a>Ford, Gerald (I)<a><t>Brother Can You Spare a Dime
	(1975)<t><a>Garbo, Greta<a>
	 */
}
