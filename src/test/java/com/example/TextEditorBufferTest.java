package com.example;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TextEditorBufferTest {

    @Test
    void positiveScenarioTest() {
        TextEditorBuffer buffer = new TextEditorBuffer();

        buffer.append("Hello");
        buffer.append(" World");
        assertThat(buffer.getText()).isEqualTo("Hello World");

        buffer.delete(6);
        assertThat(buffer.getText()).isEqualTo("Hello");

        buffer.undo(); // undo delete
        assertThat(buffer.getText()).isEqualTo("Hello World");

        buffer.undo(); // undo append " World"
        assertThat(buffer.getText()).isEqualTo("Hello");

        assertThat(buffer.getLastChar()).isEqualTo('o');
    }

    @Test
    void undoWithNoHistoryShouldDoNothing() {
        TextEditorBuffer buffer = new TextEditorBuffer();
        buffer.undo(); // nothing to undo
        assertThat(buffer.getText()).isEqualTo("");
        assertThat(buffer.getLastChar()).isNull();
    }

    @Test
    void deleteMoreThanBufferLength() {
        TextEditorBuffer buffer = new TextEditorBuffer();
        buffer.append("Hi");
        buffer.delete(10);
        assertThat(buffer.getText()).isEqualTo("");
    }
}

