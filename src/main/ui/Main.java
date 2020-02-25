package ui;

//Main class
public class Main {
    public static void main(String[] args) {
        new VocabApp();
    }
}







//notes to myself:
// - I create methods inside a class if I want to do more specific things than just access the fields
// - if I simply want to access the fields I could call Database.fieldname.size()
// - generally do NOT call the fields the same as the type name
// https://www.w3schools.com/js/js_json_objects.asp
// we can access values from a JSON object by just calling with . notation.
// we need to use the parser if we have a String, which is often the case when we're
// receiving data from a webserver.
// https://stackoverflow.com/questions/7451600/jsonobject-how-to-get-a-value