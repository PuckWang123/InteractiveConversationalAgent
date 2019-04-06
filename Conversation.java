import java.util.ArrayList;
import java.util.HashMap;
/**
 * The Conversation Class takes user input from the GUI
 * 		and form a conversation with the Chatbot. 
 * 
 * The main purpose of the Conversation Class is to communicate
 * 		human with the Chatbot primitively, and validate if the conversation is closed.
 * 
 */
public class Conversation{
	public String msg = "";
	private static BDialog dialog = new BDialog();
	private ArrayList<String> aList = new ArrayList<String>();
	private ArrayList<String> randresp = new ArrayList<String>();
	public Conversation(){
		dialog.yeBot.setVisible(true);
		aList.add("quit");
		aList.add("see you");
		aList.add("goodbye");
		randresp.add("wish i could help   i dont know what that means");
		randresp.add("you got good vibes   but i dont know what to say to that");
		randresp.add("yo man you gotta slow down   maybe try saying that a different way");
		randresp.add("it is out of my scope man");
		randresp.add("that sounds interesting");
		randresp.add("you got me there what is it about");
	}
	
	//used to print and set an input
	public String response(String msg){
		this.msg=msg;
		String result = null;
		String response = dialog.showInputDialog(msg);  
		result = dialog.recieveInput();
		return result;
	}

	public boolean containedRandomResponse(String msg) {
		return (msg != null) ? randresp.contains(msg.toLowerCase()) : false;
	}
	public String recieveInput() {
		String result = dialog.recieveInput();
		return result;
	}
	//verify if the user input contains a exit prompt
	public boolean isContained(String input) {
		return (input != null) ? aList.contains(input.toLowerCase()) : false;
	}
	
	
}
