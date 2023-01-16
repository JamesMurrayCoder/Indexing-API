# MultithreadedIndexingAPI
Text Indexer README
Although the requirements of the assignment said that we were to create a console based API, I
aimed to allow the user to obtain the functionality without actually using a menu. I wanted to be
able to create an object by passing it the relevant parameters, and then invoking the appropriate
methods on the object. The menu would then be able to create an object of that class, and simply
display the options on the screen. I felt that would achieve the best level of abstraction for the user.
I did this with the TextIndexer class, which became the controller of the program. The menu, and
subsequently the GUI, could create an object of the class and display the available options to the
user.
As the output file was not actually updating the view, I set out to use the Model Adaptor View design
pattern. However, I finished the project a few days early and decided to add a GUI so I could see how
it is done. As this included a TextArea Panel that is updated to display the output, the design pattern
had to change to MVC. However, I did not have time to implement this fully and so the encapsulation
in the GUI is not at the required level.
I took a lot of code for the GUI from the Cave of Programming tutorial on J Swing, then edited it to fit
my requirements.

To run the program you need to enable the preview features of Java 19, either by using the command line
java --enable-preview Runner after the program has been compiled, or by enabling preview features in whatever
IDE you prefer. 

To use the API, you can either:
1. Use the menu that is loaded when you run the program
2. Launch the GUI from the menu
3. Create an object of the TextIndexer class in your project and invoke the methods from there.
The additional functionality of the program simply:
1. Print the words of the index.
2. Print the words of the index in reverse.
3. Get the total number of words.
4. Print the five most common words.
