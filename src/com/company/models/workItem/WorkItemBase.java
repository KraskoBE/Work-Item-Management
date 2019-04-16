package com.company.models.workItem;

import com.company.engine.EngineConstants;
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
    private static final String ERROR_WORK_ITEM_NAME = "Work item name should be %d-%d characters";
    private static final String ERROR_WORK_ITEM_DESCRIPTION = "Work item description should be %d-%d characters";
    static final String ERROR_INVALID_STATUS = "Invalid status";

    private int id;
    private String title;
    private String description;
    protected Status status;
    private List<Comment> comments;
    private List<String> history;

    WorkItemBase(int id, String title, String description, Status status) {
        setHistory();
        setId(id);
        setTitle(title);
        setDescription(description);
        setStatus(status);
        setComments();
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

    public void addActivityHistory(String activity) {
        history.add(activity);
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
        addActivityHistory(String.format(EngineConstants.AddedComment_WorkItemActivity, comment.getAuthor().getName()));
    }

    public abstract void setStatus(Status status);

    @Override
    public String toString() {
        return String.format("[%s]\n" +
                        "{\n" +
                        "   ID: %d\n" +
                        "   Title: %s\n" +
                        "   Description: %s\n" +
                        "   Status: %s\n" +
                        "   %s\n" + // additionalInfo
                        "   Comments: %s\n" +
                        "   History: %s\n" +
                        "}",
                getClass().getSimpleName().replace("Impl", ""),
                getId(),
                getTitle(),
                getDescription(),
                getStatus().toString(),
                additionalInfo(),
                getComments().toString(),
                getHistory().toString());
    }

    private void setId(int id) {
        this.id = id;
    }

    private void setTitle(String title) {
        if (title.length() < TITLE_MIN_LENGTH || title.length() > TITLE_MAX_LENGTH)
            throw new IllegalArgumentException(String.format(ERROR_WORK_ITEM_NAME, TITLE_MIN_LENGTH, TITLE_MAX_LENGTH));
        this.title = title;
    }

    private void setDescription(String description) {
        if (description.length() < DESCRIPTION_MIN_LENGTH || description.length() > DESCRIPTION_MAX_LENGTH)
            throw new IllegalArgumentException(String.format(ERROR_WORK_ITEM_DESCRIPTION, DESCRIPTION_MIN_LENGTH, DESCRIPTION_MAX_LENGTH));
        this.description = description;
    }

    private void setComments() {
        this.comments = new ArrayList<>();
    }

    abstract String additionalInfo();

    void setHistory() {
        this.history = new ArrayList<>();
    }
}
