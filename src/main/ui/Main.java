package ui;

//Main class
public class Main {
    public static void main(String[] args) {
        new VocabApp();
    }
}


//notes to myself:
// each class has a default constructor even if the there is just a method with public static void write etc.
// https://www.w3schools.com/js/js_json_objects.asp
// we can access values from a JSON object by just calling with . notation.
// we need to use the parser if we have a String, which is often the case when we're
// receiving data from a webserver.
// https://stackoverflow.com/questions/7451600/jsonobject-how-to-get-a-value