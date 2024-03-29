package com.company.engine.commands;

import com.company.engine.EngineConstants;
import com.company.engine.EngineImpl;
import com.company.models.contracts.workItem.Bug;
import com.company.models.contracts.workItem.BugStory;
import com.company.models.contracts.workItem.Story;

import java.util.List;

public final class UnassignCmd {

    public static String execute(EngineImpl engine, List<String> parameters) {
        if (parameters.size() != 2)
            return EngineConstants.InvalidNumberOfParameters;

        int workItemID = Integer.parseInt(parameters.get(0));
        String assignee = parameters.get(1);

        if (!engine.getWorkItems().containsKey(workItemID))
            return String.format(EngineConstants.WorkItemDoesNotExistErrorMessage, workItemID);

        if (!engine.getMembers().containsKey(assignee))
            return String.format(EngineConstants.MemberDoesNotExistErrorMessage, assignee);

        if (!engine.getMembers().get(assignee).getItems().containsKey(workItemID))
            return String.format(EngineConstants.MemberDoesNotHaveWorkItemErrorMessage, assignee, workItemID);

        engine.getMembers().get(assignee).removeWorkItem(workItemID);
        if (engine.getWorkItems().get(workItemID) instanceof Bug || engine.getWorkItems().get(workItemID) instanceof Story)
            ((BugStory) engine.getWorkItems().get(workItemID)).removeAssignee();

        return EngineConstants.WorkItemUnassignedSuccessMessage;
    }
}
