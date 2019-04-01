package com.company.models.workItem;

import com.company.models.common.Status;
import com.company.models.contracts.Comment;
import com.company.models.contracts.workItem.WorkItem;

import java.util.List;

public abstract class WorkItemBase implements WorkItem {
    private int id;
    private String title;
    private String description;
    private Status status;
    private List<Comment> comments;
    private List<String> history;

}
