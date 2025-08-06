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

    @Test
    void appendNullShouldDoNothing() {
        TextEditorBuffer buffer = new TextEditorBuffer();

        buffer.append(null);
        assertThat(buffer.getText()).isEqualTo("");

        buffer.append("");
        assertThat(buffer.getText()).isEqualTo("");

        buffer.append("Test");
        buffer.append("");
        buffer.append(null);
        assertThat(buffer.getText()).isEqualTo("Test");
    }

    @Test
    void deleteZeroOrNegativeShouldDoNothing() {
        TextEditorBuffer buffer = new TextEditorBuffer();

        buffer.append("Hello");

        buffer.delete(0);
        assertThat(buffer.getText()).isEqualTo("Hello");

        buffer.delete(-5);
        assertThat(buffer.getText()).isEqualTo("Hello");
    }

    @Test
    void getLastCharOnEmptyBufferShouldReturnNull() {
        TextEditorBuffer buffer = new TextEditorBuffer();
        assertThat(buffer.getLastChar()).isNull();

        buffer.append("A");
        buffer.delete(1);
        assertThat(buffer.getText()).isEqualTo("");
        assertThat(buffer.getLastChar()).isNull();
    }
}

