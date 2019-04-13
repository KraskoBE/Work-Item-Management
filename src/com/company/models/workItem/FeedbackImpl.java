package com.company.models.workItem;

import com.company.models.common.Status;
import com.company.models.contracts.workItem.Feedback;

public class FeedbackImpl extends WorkItemBase implements Feedback {
    private int rating;

    public FeedbackImpl(int id, String title, String description, Status status, int rating) {
        super(id, title, description, status);
        setRating(rating);
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public void setStatus(Status status) {
        if (status != Status.New && status != Status.Unscheduled && status != Status.Done)
            throw new IllegalArgumentException(ERROR_INVALID_STATUS);
        this.status = status;
    }

    @Override
    String additionalInfo() {
        return String.format("Rating: %d", getRating());
    }
}
