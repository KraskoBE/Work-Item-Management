package com.company.models;

import com.company.models.contracts.Comment;
import com.company.models.contracts.unit.Member;

public class CommentImpl implements Comment {
    private Member author;
    private String message;

    public Member getAuthor() {
        return author;
    }

    public String getMessage() {
        return message;
    }

    private void setMessage(String message) {
        this.message = message;
    }

    private void setAuthor(Member author) {
        this.author = author;
    }
}
