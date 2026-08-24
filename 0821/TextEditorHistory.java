import java.util.ArrayDeque;
import java.util.Deque;

public class TextEditorHistory {

    static class TextEditor {
        private final Deque<String> undoStack = new ArrayDeque<>();
        private final Deque<String> redoStack = new ArrayDeque<>();
        private String currentText = "";

        public void edit(String text) {
            if (text == null) {
                return;
            }

            undoStack.push(currentText);
            currentText = text;
            redoStack.clear();

            printState("edit: " + text);
        }

        public String undo() {
            if (undoStack.isEmpty()) {
                printState("undo: empty");
                return currentText;
            }

            redoStack.push(currentText);
            currentText = undoStack.pop();

            printState("undo");
            return currentText;
        }

        public String redo() {
            if (redoStack.isEmpty()) {
                printState("redo: empty");
                return currentText;
            }

            undoStack.push(currentText);
            currentText = redoStack.pop();

            printState("redo");
            return currentText;
        }

        private void printState(String operation) {
            System.out.println(
                    operation
                    + " | current=" + currentText
                    + " | undo=" + undoStack
                    + " | redo=" + redoStack
            );
        }
    }

    public static void main(String[] args) {
        TextEditor editor = new TextEditor();

        editor.edit("Hello");
        editor.edit("Hello World");
        editor.edit("Hello World!");

        editor.undo();
        editor.undo();

        editor.redo();

        editor.edit("Hello Java");

        editor.redo();

        editor.undo();
        editor.undo();
        editor.undo();
    }
}