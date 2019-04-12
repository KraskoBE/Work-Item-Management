package com.company.engine.commands;

import com.company.engine.EngineConstants;
import com.company.engine.EngineImpl;
import com.company.engine.contracts.Factory;
import com.company.models.contracts.workItem.Feedback;

import java.util.List;

public final class CreateFeedbackCmd {

    public static String execute(EngineImpl engine, Factory factory, List<String> parameters) {
        if (parameters.size() != 6)
            return EngineConstants.InvalidNumberOfParameters;

        String feedbackName = parameters.get(0);
        String feedbackDescription = parameters.get(1);
        String feedbackStatus = parameters.get(2);
        int feedbackRating = Integer.parseInt(parameters.get(3));
        String feedbackBoard = parameters.get(4);
        String feedbackTeam = parameters.get(5);

        if(!engine.getTeams().containsKey(feedbackTeam))
            return String.format(EngineConstants.TeamDoesNotExistErrorMessage, feedbackTeam);

        if(!engine.getTeams().get(feedbackTeam).getBoards().containsKey(feedbackBoard))
            return String.format(EngineConstants.BoardIsNotOnTheTeamErrorMessage, feedbackBoard, feedbackTeam);


        Feedback feedback = factory.createFeedback(engine.getGlobalID(), feedbackName, feedbackDescription, feedbackStatus, feedbackRating);

        engine.getWorkItems().put(engine.getGlobalID(), feedback);
        engine.getTeams().get(feedbackTeam).getBoards().get(feedbackBoard).addWorkItem(feedback);

        return String.format(EngineConstants.FeedBackSuccessMessage, feedbackName, engine.getGlobalIDWithIncrease());

    }
}
