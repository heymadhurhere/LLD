#include <iostream>
#include <string>
#include <vector>
#include <fstream>

using namespace std;

class DocumentEditor {
private:
    vector<string> documentElements;
    string renderedDocument;

public:
    // adds text
    void addText(string text) {
        documentElements.push_back(text);
    }

    // adds image representd by filepath
    void addImage(string imagePath) {
        documentElements.push_back(imagePath);
    }

    // renders the document by checking the type of each element at runtime
    string renderDocument() {
        if (renderedDocument.empty()) {
            string result;
            for (auto element : documentElements) {
                if (element.size() > 4 && (element.substr(element.size() - 4) == ".jpg" || // check if the size of string is > 4 and it contains .jpg or .png at the end
                    element.substr(element.size() - 4) == ".png")) {
                        result += "[Image: " + element + "]" + "\n";
            } else {
                result += element + "\n";
            }
        }
        renderedDocument = result; // all the elements put into the renderedDocument string
    }
        return renderedDocument;
    }

    void saveToFile() {
        ofstream file("document.txt"); // open the file
        if (file.is_open()) {
            file << renderDocument(); // write the rendered document to the file
            file.close(); // close the file
            cout << "Document saved to document.txt" << endl;
        } else {
            cout << "Unable to open file" << endl;
        }
    }
};

int main() {
    DocumentEditor editor;
    editor.addText("Hello, this is a document.");
    editor.addImage("image1.jpg");
    editor.addText("This is an image.");
    editor.saveToFile();
    return 0;
}