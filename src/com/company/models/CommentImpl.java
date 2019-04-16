package com.company.models;

import com.company.models.contracts.Comment;
import com.company.models.contracts.unit.Member;

public class CommentImpl implements Comment {
    private Member author;
    private String message;

    public CommentImpl(Member author, String message) {
        setAuthor(author);
        setMessage(message);
    }

    public Member getAuthor() {
        return author;
    }

    public String getMessage() {
        return message;
    }

    public String toString() {
        return this.message;
    }

    private void setMessage(String message) {
        if (message.isEmpty())
            throw new IllegalArgumentException("Message is empty");
        this.message = message;
    }

    private void setAuthor(Member author) {
        this.author = author;
    }
}
