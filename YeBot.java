import java.util.*;
import org.alicebot.ab.*;
import java.io.File;
/**
 * YeBot is the main class where the conversation is held.
 */
public class YeBot {

	static Conversation conversation;			

	public static void main(String[] args) {
		//initialize
		String dir = new File(".").getAbsolutePath();
		System.out.println(dir.substring(0,dir.length()-2));
		MagicBooleans.trace_mode = false;
		Bot yebot = new Bot("YeBot",dir.substring(0,dir.length()-2));
		yebot.writeAIMLFiles();
		
		Chat session = new Chat(yebot);
		conversation = new Conversation();
		
		String input = "test";
		int i = 1;
		while(!conversation.isContained(input)){
			input = null;
			input = conversation.recieveInput();
			
			input = handle_spell(input);
			
			if (input!=""&&input!=null&&input.length()>1||i==1) {
				if(input==""||input==null||input.length()<1) {
					//start conversation
					conversation.response("Ye is in the BUILDING NOW!");
					i=0;	
				}
				else if(conversation.isContained(input)) {
					//user calls for exiting the conversation
					try {
						Thread.sleep(500);
						conversation.response(session.multisentenceRespond(input));
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					break;
				}
				else {
					//regular response
					conversation.response(session.multisentenceRespond(input));
				}
			}
		}
		System.exit(1);//This statement terminates the program	
	}
	
	public static void wordnet() {
		
	}
	
	public static String handle_spell(String input) {
	    Stemmer s = new Stemmer();
		try {
			input = input.toLowerCase();
			while(true) {
				for(int i = 0; i < input.length();i++) {
					s.add(input.charAt(i));
				}
				s.stem();
				input = s.toString();
				System.out.println(input);
				break;
			}
			return input;
		}catch(NullPointerException e) {
//			e.printStackTrace();
		}
		return "";
	}
}
