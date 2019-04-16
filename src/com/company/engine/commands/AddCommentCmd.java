package com.company.engine.commands;

import com.company.engine.EngineConstants;
import com.company.engine.EngineImpl;
import com.company.models.CommentImpl;
import com.company.models.contracts.Comment;

import java.util.List;

public final class AddCommentCmd {

    public static String execute(EngineImpl engine, List<String> parameters) {
        if (parameters.size() != 3)
            return EngineConstants.InvalidNumberOfParameters;

        String author = parameters.get(0);
        String message = parameters.get(1);
        int workItemID = Integer.parseInt(parameters.get(2));

        if (!engine.getMembers().containsKey(author))
            return String.format(EngineConstants.MemberDoesNotExistErrorMessage, author);

        if (!engine.getWorkItems().containsKey(workItemID))
            return String.format(EngineConstants.WorkItemDoesNotExistErrorMessage, workItemID);

        if (!AssignCmd.getMemberTeam(engine, author).getName().equals(AssignCmd.getWorkItemTeam(engine, workItemID).getName()))
            return String.format(EngineConstants.MemberIsNotFromTeamErrorMessage, author, AssignCmd.getWorkItemTeam(engine, workItemID).getName());

        Comment comment = new CommentImpl(engine.getMembers().get(author), message);
        engine.getWorkItems().get(workItemID).addComment(comment);
        engine.getMembers().get(author).addActivity(String.format(EngineConstants.AddedComment_PersonActivity, workItemID));

        return EngineConstants.CommentAddedSuccessMessage;
    }
}
