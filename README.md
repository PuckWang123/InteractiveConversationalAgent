# InteractiveConversationalAgent
**Purpose:**
The Interactive Conversational Agent allows an individual to hold a conversation of at least 30 turns. The agent is a celebrity and the user can be anyone. The celebrity chosen is Kanye West. The conversation will be primarily question/answer based, but there may be certain specific responses for some non-question statements. The responses from the chat agent is a collection of tweets and song lyrics by Kanye West himself.

## Class Organization

**Breakdown of the classes:**
<<<<<<< HEAD
* BDialog: This class is responsible to create a Graphical User Interface for the conversation between the user and the Chatbot.
* Conversation: This class is responsible to communicate and transfer user inputs and chatbot outputs.
* YeBot: YeBot is the main class for the interaction between a user and the chatbot.
* Stemmer: This class uses Portor Stemmer Algorithm to remove misspelling from user input.
=======
* BDialog: This class is responsible to create a Graphical User Interface for the conversation between the user and the Chatbot. 
* Conversation: This class is responsible to communicate and transfer user inputs and chatbot outputs. 
* YeBot: YeBot is the main class for the interaction between a user and the chatbot. 

>>>>>>> parent of c1f332e... added WordNet feature and StanfordNLP feature
## How to Compile and Run the Code
**Enter the following code into command line to run Yebot:**
* javac BDialog.java Conversation.java Stemmer.java YeBot.java
* java YeBot

**Or run the yebot.java file**

## NOTE
* A cleanup is required. When you use the repo, please unzip the lib fold.

## Built With

* [Java](https://www.java.com/) - Programming language 
* [AIML](https://www.tutorialspoint.com/aiml/) - AIML dialogue
<<<<<<< HEAD
* [StanfordCoreNLP](https://stanfordnlp.github.io/CoreNLP) - Nature language process library
* [JWI](https://projects.csail.mit.edu/jwi/) - library of WordNet dictionary

## What's new
* Added new responses for kim kardashian as additional topic.
* Added more flexible responses when the chatbot is unable to reply.
* Added feature of looking up synonymous words when AIML cannot find an appropriate response due to different expression of language. Due to different decompression technique, at current stage the feature is not working properly.
* Added feature of Name Entity Recognition. However, the POS it produces does not match with previous feature.
=======





>>>>>>> parent of c1f332e... added WordNet feature and StanfordNLP feature
