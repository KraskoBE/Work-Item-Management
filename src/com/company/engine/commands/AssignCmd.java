package com.company.engine.commands;

import com.company.engine.EngineConstants;
import com.company.engine.EngineImpl;
import com.company.models.contracts.Team;
import com.company.models.contracts.unit.Board;
import com.company.models.contracts.unit.Member;
import com.company.models.contracts.workItem.Bug;
import com.company.models.contracts.workItem.BugStory;
import com.company.models.contracts.workItem.Story;
import com.company.models.contracts.workItem.WorkItem;

import java.util.List;

public final class AssignCmd {

    public static String execute(EngineImpl engine, List<String> parameters) {
        if (parameters.size() != 2)
            return EngineConstants.InvalidNumberOfParameters;

        int workItemID = Integer.parseInt(parameters.get(0));
        String assignee = parameters.get(1);

        if (!engine.getWorkItems().containsKey(workItemID))
            return String.format(EngineConstants.WorkItemDoesNotExistErrorMessage, workItemID);

        if (!engine.getMembers().containsKey(assignee))
            return String.format(EngineConstants.MemberDoesNotExistErrorMessage, assignee);

        if (!getMemberTeam(engine, assignee).equals(getWorkItemTeam(engine, workItemID)))
            return String.format(EngineConstants.MemberIsNotFromTeamErrorMessage, assignee, getWorkItemTeam(engine, workItemID));

        if (engine.getMembers().get(assignee).getItems().containsKey(workItemID))
            return String.format(EngineConstants.ItemAlreadyAssignedErrorMessage, assignee);

        if (engine.getWorkItems().get(workItemID) instanceof Bug || engine.getWorkItems().get(workItemID) instanceof Story) {
            if (!((BugStory) engine.getWorkItems().get(workItemID)).getAssignee().getName().equals("No Assignee"))
                return String.format(EngineConstants.ItemAlreadyAssignedErrorMessage, ((BugStory) engine.getWorkItems().get(workItemID)).getAssignee().getName());
            ((BugStory) engine.getWorkItems().get(workItemID)).setAssignee(engine.getMembers().get(assignee));
        }

        engine.getMembers().get(assignee).addWorkItem(engine.getWorkItems().get(workItemID));
        return String.format(EngineConstants.WorkItemAssignedSuccessMessage, workItemID, assignee);
    }

    private static String getMemberTeam(EngineImpl engine, String name) {
        for (Team t : engine.getTeams().values())
            for (Member m : t.getMembers().values())
                if (m.getName().equals(name))
                    return t.getName();
        return "";
    }

    private static String getWorkItemTeam(EngineImpl engine, int id) {
        for (Team t : engine.getTeams().values())
            for (Board b : t.getBoards().values())
                for (WorkItem w : b.getItems().values())
                    if (w.getId() == id)
                        return t.getName();
        return "";
    }
}
