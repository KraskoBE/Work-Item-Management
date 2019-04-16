package com.company.engine.commands;

import com.company.engine.EngineConstants;
import com.company.engine.EngineImpl;
import com.company.engine.FactoryImpl;
import com.company.models.common.Status;
import com.company.models.contracts.workItem.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class ListWorkItemsCmd {

    public static String execute(EngineImpl engine, List<String> parameters) {
        if (parameters.size() < 1 || parameters.size() > 3)
            return EngineConstants.InvalidNumberOfParameters;

        String firstParam = parameters.get(0);
        if (parameters.size() == 1)
            return listWorkItemsByCategory(engine, firstParam);

        if (parameters.size() == 2) {
            String secondParam = parameters.get(1);
            if (firstParam.equals("status"))
                return listWorkItemsByStatus(engine, secondParam);
            if (firstParam.equals("assignee"))
                return listWorkItemsByAssignee(engine, secondParam);
        }

        if (!firstParam.equals("statusassignee"))
            return String.format(EngineConstants.InvalidCommandErrorMessage, firstParam);

        String secondParam = parameters.get(1);
        String thirdParam = parameters.get(2);
        return listWorkItemsByStatusAndAssignee(engine, secondParam, thirdParam);
    }

    private static String listWorkItemsByCategory(EngineImpl engine, String category) {
        Stream<WorkItem> workItemStream = engine.getWorkItems().values().stream();

        switch (category.toLowerCase()) {
            case "bug":
                workItemStream = workItemStream.filter(workItem -> workItem instanceof Bug);
                break;
            case "story":
                workItemStream = workItemStream.filter(workItem -> workItem instanceof Story);
                break;
            case "feedback":
                workItemStream = workItemStream.filter(workItem -> workItem instanceof Feedback);
                break;
            case "all":
                break;
            default:
                return String.format(EngineConstants.InvalidCategoryErrorMessage, category);
        }

        String result = workItemStream.map(WorkItem::toString).collect(Collectors.joining("\n"));

        return result.length() == 0 ?
                EngineConstants.WorkItemListEmptyErrorMessage : result.trim();
    }

    private static String listWorkItemsByStatus(EngineImpl engine, String statusStr) {
        Status status = FactoryImpl.getStatus(statusStr);

        String result = engine.getWorkItems()
                .values()
                .stream()
                .filter(workItem -> workItem.getStatus() == status)
                .map(WorkItem::toString)
                .collect(Collectors.joining("\n"));

        return result.length() == 0 ?
                EngineConstants.WorkItemListEmptyErrorMessage : result.trim();
    }

    private static String listWorkItemsByAssignee(EngineImpl engine, String assignee) {
        if (!engine.getMembers().containsKey(assignee))
            return String.format(EngineConstants.MemberDoesNotExistErrorMessage, assignee);

        Stream<BugStory> bugStoryStream = engine.getWorkItems()
                .values()
                .stream()
                .filter(workItem -> workItem instanceof Bug || workItem instanceof Story)
                .map(workItem -> (BugStory) workItem);

        String result = bugStoryStream
                .filter(workItem -> workItem.getAssignee().getName().equals(assignee))
                .map(WorkItem::toString)
                .collect(Collectors.joining("\n"));

        return result.length() == 0 ?
                EngineConstants.WorkItemListEmptyErrorMessage : result.trim();
    }

    private static String listWorkItemsByStatusAndAssignee(EngineImpl engine, String statusStr, String assignee) {
        Status status = FactoryImpl.getStatus(statusStr);
        if (!engine.getMembers().containsKey(assignee))
            return String.format(EngineConstants.MemberDoesNotExistErrorMessage, assignee);
        Stream<BugStory> bugStoryStream = engine.getWorkItems()
                .values()
                .stream()
                .filter(workItem -> workItem instanceof Bug || workItem instanceof Story)
                .map(workItem -> (BugStory) workItem);

        String result = bugStoryStream
                .filter(workItem -> workItem.getAssignee().getName().equals(assignee))
                .filter(workItem -> workItem.getStatus() == status)
                .map(WorkItem::toString)
                .collect(Collectors.joining("\n"));

        return result.length() == 0 ?
                EngineConstants.WorkItemListEmptyErrorMessage : result.trim();
    }
}
