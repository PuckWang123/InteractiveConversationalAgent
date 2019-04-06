import java.util.*;
import org.alicebot.ab.*;
import java.io.File;
//import edu.mit.jwi.Dictionary;
import edu.mit.jwi.Dictionary;
import edu.mit.jwi.IDictionary;
import edu.mit.jwi.item.IIndexWord;
import edu.mit.jwi.item.ISynset;
import edu.mit.jwi.item.IWord;
import edu.mit.jwi.item.IWordID;
import edu.mit.jwi.item.POS;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreAnnotations.NamedEntityTagAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.PartOfSpeechAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TextAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.simple.Sentence;
import edu.stanford.nlp.util.CoreMap;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
/**
 * YeBot is the main class where the conversation is held.
 */
public class YeBot {

	static Conversation conversation;			
	static ArrayList<String> randresp = new ArrayList<String>();
	public static void main(String[] args) {
		randresp.add("wish i could help   i dont know what that means");
		randresp.add("you got good vibes   but i dont know what to say to that");
		randresp.add("yo man you gotta slow down   maybe try saying that a different way");
		randresp.add("it is out of my scope man");
		randresp.add("that sounds interesting");
		randresp.add("you got me there what is it about");
		//initialize
		String dir = new File(".").getAbsolutePath();
		System.out.println(dir.substring(0,dir.length()-2));
		MagicBooleans.trace_mode = false;
		Bot yebot = new Bot("YeBot",dir.substring(0,dir.length()-2));
		yebot.writeAIMLFiles();
		
		Chat session = new Chat(yebot);
		conversation = new Conversation();
		
		//init WordNet
//		construct the dictionary object and open it
		String path = dir.substring(0, dir.length() - 2) + "\\lib\\WordNet-3.0\\dict";
		URL url = null;
		try {
			url = new URL("file", null, path);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		if (url == null) return;
		IDictionary dict = (IDictionary) new Dictionary(url);
		POS[] pos = new POS[] { POS.ADJECTIVE, POS.ADVERB, POS.NOUN, POS.VERB };
		
		String input = "test";
		int i = 1;
		while(!conversation.isContained(input)){
			input = null;
			input = conversation.recieveInput();
			
			String temp = handle_spell(input);
			
			// knowning the entity of the words, finding synonym
			//NOTE: StanfordCoreNLP is working but produces different POS enums from JWI, therefore, it is not applicable
			try {
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
						if(input != null) {
							String[] words = input.split(" ");
							Sentence sent = new Sentence(input);
							List<String> nerTags = sent.nerTags(); // [PERSON, O, O, O, O, O, O, O]
							for (int z = 0; z < nerTags.size(); z++) {
								String tag = sent.posTag(z);
								System.out.println(tag);
			//					if(tag =="IN" || tag == "DT" || tag == "WP" || tag == "WP$" || tag =="WRB" || tag == "CC" || tag == "CD")
			//						nerTags.remove(z);
							}
						}
//						input = find_syn(words, dict, pos, session);
						String output = session.multisentenceRespond(input);
						if(containedRandomResponse(output)) {
							output = session.multisentenceRespond(temp);
						}
						conversation.response(output);
					}
				}
			}catch(Exception e) {}
		}
		System.exit(1);//This statement terminates the program	
	}
	
	public static String find_syn(String[] input, IDictionary dict, final POS[] pos, Chat session) {
		String temp = "";
		try {
			System.out.println("Looking up synomous word...");
			dict.open();
			for (int i = 0; i < input.length; i++) {//words
				for (int j = 0; j < pos.length; j++) {//POS
					try {
						IIndexWord idxWord = dict.getIndexWord("run", pos[j]);
						for (int k = 0; k < 2; k++) {//synomous words
							IWordID wordID = idxWord.getWordIDs().get(k);
							IWord word = dict.getWord(wordID);
							ISynset synset = word.getSynset();
							
							for (IWord w : synset.getWords()) {
								String replace = w.getLemma();
								for(int z = 0; z < input.length; i++) {
									if(i == z) 
										temp += " " + replace;
									else
										temp += " " + input[z];
								}
								
								String rsl = session.multisentenceRespond(temp);
								if(!containedRandomResponse(rsl)) {
									System.out.println("Done Looking Up");
									return temp;
								}
								temp = "";
							}
						}
					} catch (Exception e) {}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Done Looking Up");
		return "";
	}
	
	
	public static boolean containedRandomResponse(String msg) {
		return (msg != null) ? randresp.contains(msg.toLowerCase()) : false;
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
