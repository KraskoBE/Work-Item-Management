package com.company.models.workItem;

import com.company.models.common.Status;
import com.company.models.contracts.Comment;
import com.company.models.contracts.workItem.WorkItem;

import java.util.ArrayList;
import java.util.List;

public abstract class WorkItemBase implements WorkItem {
    private static final int TITLE_MIN_LENGTH = 10;
    private static final int TITLE_MAX_LENGTH = 50;
    private static final int DESCRIPTION_MIN_LENGTH = 10;
    private static final int DESCRIPTION_MAX_LENGTH = 500;

    private int id;
    private String title;
    private String description;
    private Status status;
    private List<Comment> comments;
    private List<String> history;

    public WorkItemBase(int id, String title, String description, Status status) {
        setId(id);
        setTitle(title);
        setDescription(description);
        setStatus(status);
        setComments();
        setHistory();
    }

    private void setId(int id) {
        this.id = id;
    }

    private void setTitle(String title) {
        if (title.length() < TITLE_MIN_LENGTH || title.length() > TITLE_MAX_LENGTH)
            throw new IllegalArgumentException();
        this.title = title;
    }

    private void setDescription(String description) {
        if (description.length() < DESCRIPTION_MIN_LENGTH || description.length() > DESCRIPTION_MAX_LENGTH)
            throw new IllegalArgumentException();
        this.description = description;
    }

    abstract void setStatus(Status status);

    private void setComments() {
        this.comments = new ArrayList<>();
    }

    private void setHistory() {
        this.history = new ArrayList<>();
    }


    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }

    public List<Comment> getComments() {
        return new ArrayList<>(comments);
    }

    public List<String> getHistory() {
        return new ArrayList<>(history);
    }
}
