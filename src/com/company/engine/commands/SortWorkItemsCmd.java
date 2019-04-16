package com.company.engine.commands;

import com.company.engine.EngineConstants;
import com.company.engine.EngineImpl;
import com.company.models.contracts.workItem.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class SortWorkItemsCmd {

    public static String execute(EngineImpl engine, List<String> parameters) {
        if (parameters.size() != 1)
            return EngineConstants.InvalidNumberOfParameters;
        String sortType = parameters.get(0);
        return sortWorkItems(engine, sortType);
    }

    private static String sortWorkItems(EngineImpl engine, String sortType) {
        Stream<WorkItem> workItemStream = engine.getWorkItems().values().stream();
        Stream<Feedback> feedbackStream = engine.getWorkItems().values().stream().filter(w -> w instanceof Feedback).map(w -> (Feedback) w);
        Stream<BugStory> bugStoryStream = engine.getWorkItems().values().stream().filter(w -> w instanceof BugStory).map(w -> (BugStory) w);
        Stream<Bug> bugStream = engine.getWorkItems().values().stream().filter(w -> w instanceof Bug).map(w -> (Bug) w);
        Stream<Story> storyStream = engine.getWorkItems().values().stream().filter(w -> w instanceof Story).map(w -> (Story) w);

        String result;

        switch (sortType.toLowerCase()) {
            case "title":
                result = workItemStream
                        .sorted(Comparator.comparing(WorkItem::getTitle))
                        .map(WorkItem::toString)
                        .collect(Collectors.joining("\n"));
                break;
            case "priority":
                result = bugStoryStream
                        .sorted(Comparator.comparing(BugStory::getPriority))
                        .map(WorkItem::toString)
                        .collect(Collectors.joining("\n"));
                break;
            case "severity":
                result = bugStream
                        .sorted(Comparator.comparing(Bug::getSeverity))
                        .map(Bug::toString)
                        .collect(Collectors.joining("\n"));
                break;
            case "size":
                result = storyStream
                        .sorted(Comparator.comparing(Story::getSize))
                        .map(Story::toString)
                        .collect(Collectors.joining("\n"));
                break;
            case "rating":
                result = feedbackStream
                        .sorted(Comparator.comparing(Feedback::getRating))
                        .map(WorkItem::toString)
                        .collect(Collectors.joining("\n"));
                break;
            default:
                return String.format(EngineConstants.InvalidSortTypeErrorMessage, sortType);
        }

        return result.length() == 0 ?
                EngineConstants.WorkItemListEmptyErrorMessage : result.trim();
    }
}
