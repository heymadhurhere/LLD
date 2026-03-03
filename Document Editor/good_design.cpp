#include <iostream>
#include <string>
#include <vector>
#include <fstream>

using namespace std;

// Abstracct class for document elements
class DocumentElement {
public:
    virtual string render() = 0;
};

// Concrete implementation for text element
class TextElement : public DocumentElement {
private:
    string text;
public:
    TextElement(string t) {
        text = t;
    }

    string render() override {
        return text;
    }
};

// Concrete implementation for image element
class ImageElement : public DocumentElement {
private:
    string imagePath;
public:
    ImageElement(string path) {
        imagePath = path;
    }

    string render() override {
        return "[Image: " + imagePath + "]";
    }
};

// NewLine element to represent new lines in the document
class NewLineElement : public DocumentElement {
public:
    string render() override {
        return "\n";
    }
};

// Tab element to represent tabs in the document
class TabElement : public DocumentElement {
public:
    string render() override {
        return "\t";
    }
};

// Document class to hold colelction of elements
class Document {
private:
    vector<DocumentElement*> documentElements;

public:
    // adds an element to the document
    void addElement(DocumentElement* element) {
        documentElements.push_back(element);
    }

    // renders the document by calling render on each element
    string render() {
        string result;
        for (auto element : documentElements) {
            result += element->render();
        }
        return result;
    }
};

// Persistence class to hande saving the document to a file
class Persistence {
public:
    virtual void save(string data) = 0;
};

// Concrete implementation for saving to a file
class saveToFile : public Persistence {
public:
    void save(string data) override {
        ofstream outFile("document.txt");
        if (outFile) {
            outFile << data;
            outFile.close();
            cout << "Document saved to document.txt" << endl;
        } else {
            cout << "Unable to open file" << endl;
        }
    }
};

// Concrete implementation for saving to a database
class saveToDB : public Persistence {
public:
    void save(string data) override {
        cout << "Document saved to database" << endl;
    }
};

// Document Editor class to interact with the client
class DocumentEditor {
private:
    Document* document;
    Persistence* persistence;
    string renderedDocument;

public:
    // constructor to initialize the document and persistence
    DocumentEditor(Document* doc, Persistence* persist) {
        document = doc;
        persistence = persist;
    }

    // adds text to the document
    void addText(string text) {
        document->addElement(new TextElement(text));
    }

    // adds image to the document
    void addImage(string imagePath) {
        document->addElement(new ImageElement(imagePath));
    }

    // adds a new line to the document
    void addNewLine() {
        document->addElement(new NewLineElement());
    }

    // adds a tab to the document
    void addTab() {
        document->addElement(new TabElement());
    }

    // renders the document
    string renderDocument() {
        if (renderedDocument.empty()) {
            renderedDocument = document->render();
        }
        return renderedDocument;
    }

    // saves the document using the persistence mechanism
    void saveDocument() {
        persistence->save(renderDocument());
    }
};

int main() {
    Document* doc = new Document();
    Persistence* filePersistence = new saveToFile();

    //client interacts with the document editor through the DocumentEditor class
    DocumentEditor* editor = new DocumentEditor(doc, filePersistence);

    // Adding elements to the document
    editor->addText("Hello, this is a document.");
    editor->addNewLine();
    editor->addImage("image1.jpg");
    editor->addTab();
    editor->addText("This is an image.");
    editor->addNewLine();
    editor->addText("This is a new line after the image.");
    editor->addImage("image2.png");

    // rendering and saving the document
    cout << editor->renderDocument() << "\n";

    editor->saveDocument();

    return 0;
} 