package com.company.engine.commands;

import com.company.engine.EngineConstants;
import com.company.engine.EngineImpl;
import com.company.engine.contracts.Factory;
import com.company.models.contracts.workItem.Story;

import java.util.List;

public final class CreateStoryCmd {

    public static String execute(EngineImpl engine, Factory factory, List<String> parameters) {
        if (parameters.size() != 6)
            return EngineConstants.InvalidNumberOfParameters;

        String storyName = parameters.get(0);
        String storyDescription = parameters.get(1);
        String storyPriority = parameters.get(2);
        String storySize = parameters.get(3);
        String storyBoard = parameters.get(4);
        String storyTeam = parameters.get(5);

        if (!engine.getTeams().containsKey(storyTeam))
            return String.format(EngineConstants.TeamDoesNotExistErrorMessage, storyTeam);

        if (!engine.getTeams().get(storyTeam).getBoards().containsKey(storyBoard))
            return String.format(EngineConstants.BoardIsNotOnTheTeamErrorMessage, storyBoard, storyTeam);

        Story story = factory.createStory(engine.getGlobalID(), storyName, storyDescription, storyPriority, storySize);

        engine.getWorkItems().put(engine.getGlobalID(), story);
        engine.getTeams().get(storyTeam).getBoards().get(storyBoard).addWorkItem(story);

        return String.format(EngineConstants.StoryCreatedSuccessMessage, storyName, engine.getGlobalIDWithIncrease());
    }
}
