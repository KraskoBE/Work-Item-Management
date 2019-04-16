package com.company.models.contracts.workItem;

import com.company.models.common.Status;
import com.company.models.contracts.Comment;

import java.util.List;

public interface WorkItem {

    int getId();

    String getTitle();

    String getDescription();

    Status getStatus();

    List<Comment> getComments();

    List<String> getHistory();

    void setStatus(Status status);

    void addActivityHistory(String activity);

    void addComment(Comment comment);
}
