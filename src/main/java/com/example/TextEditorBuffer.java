package com.example;

import java.util.Stack;

public class TextEditorBuffer {
    private final StringBuilder buffer = new StringBuilder();
    private final Stack<Operation> history = new Stack<>();

    // Add text to the end
    public void append(String s) {
        if (s == null || s.isEmpty()) return;
        buffer.append(s);
        history.push(new AppendOperation(s.length()));
    }

    // Remove last n characters
    public void delete(int n) {
        if (n <= 0) return;
        int actual = Math.min(n, buffer.length());
        String removed = buffer.substring(buffer.length() - actual);
        buffer.delete(buffer.length() - actual, buffer.length());
        history.push(new DeleteOperation(removed));
    }

    // Undo last non-undo operation
    public void undo() {
        if (history.isEmpty()) return;
        Operation last = history.pop();
        last.undo();
    }

    // Output full buffer
    public String getText() {
        return buffer.toString();
    }

    // Output last character
    public Character getLastChar() {
        return buffer.length() == 0 ? null : buffer.charAt(buffer.length() - 1);
    }

    // Internal interface and operation types
    private interface Operation {
        void undo();
    }

    private class AppendOperation implements Operation {
        private final int length;

        AppendOperation(int length) {
            this.length = length;
        }

        public void undo() {
            buffer.delete(buffer.length() - length, buffer.length());
        }
    }

    private class DeleteOperation implements Operation {
        private final String removedText;

        DeleteOperation(String removedText) {
            this.removedText = removedText;
        }

        public void undo() {
            buffer.append(removedText);
        }
    }
}
