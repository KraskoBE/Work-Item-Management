package com.company.engine.commands;

import com.company.engine.EngineConstants;
import com.company.engine.EngineImpl;
import com.company.engine.FactoryImpl;
import com.company.models.contracts.workItem.Bug;
import com.company.models.contracts.workItem.Feedback;
import com.company.models.contracts.workItem.Story;

import java.util.List;

public final class ChangeCmd {

    public static String execute(EngineImpl engine, List<String> parameters) {
        if (parameters.size() != 3)
            return EngineConstants.InvalidNumberOfParameters;

        int workItemID;
        String changeType;
        String changeValue;

        try {
            workItemID = Integer.parseInt(parameters.get(0));
            changeType = parameters.get(1);
            changeValue = parameters.get(2);
        } catch (Exception ex) {
            return EngineConstants.InvalidCommandParameters;
        }

        if (!engine.getWorkItems().containsKey(workItemID))
            return String.format(EngineConstants.WorkItemDoesNotExistErrorMessage, workItemID);

        try {
            if (engine.getWorkItems().get(workItemID) instanceof Bug) {
                switch (changeType.toLowerCase()) {
                    case "priority":
                        ((Bug) engine.getWorkItems().get(workItemID)).setPriority(FactoryImpl.getPriority(changeValue));
                        break;
                    case "severity":
                        ((Bug) engine.getWorkItems().get(workItemID)).setSeverity(FactoryImpl.getSeverity(changeValue));
                        break;
                    case "status":
                        engine.getWorkItems().get(workItemID).setStatus(FactoryImpl.getStatus(changeValue));
                        break;
                    default:
                        return String.format(EngineConstants.InvalidObjectTypeErrorMessage, changeType);
                }
            }
        } catch (IllegalArgumentException ex) {
            return ex.getMessage();
        }

        try {
            if (engine.getWorkItems().get(workItemID) instanceof Story) {
                switch (changeType.toLowerCase()) {
                    case "priority":
                        ((Story) engine.getWorkItems().get(workItemID)).setPriority(FactoryImpl.getPriority(changeValue));
                        break;
                    case "size":
                        ((Story) engine.getWorkItems().get(workItemID)).setSize(FactoryImpl.getSize(changeValue));
                        break;
                    case "status":
                        engine.getWorkItems().get(workItemID).setStatus(FactoryImpl.getStatus(changeValue));
                        break;
                    default:
                        return String.format(EngineConstants.InvalidObjectTypeErrorMessage, changeType);
                }
            }
        } catch (IllegalArgumentException ex) {
            return ex.getMessage();
        }

        try {
            if (engine.getWorkItems().get(workItemID) instanceof Feedback) {
                switch (changeType.toLowerCase()) {
                    case "rating":
                        ((Feedback) engine.getWorkItems().get(workItemID)).setRating(Integer.parseInt(changeValue));
                        break;
                    case "status":
                        engine.getWorkItems().get(workItemID).setStatus(FactoryImpl.getStatus(changeValue));
                        break;
                    default:
                        return String.format(EngineConstants.InvalidObjectTypeErrorMessage, changeType);
                }
            }
        } catch (IllegalArgumentException ex) {
            return ex.getMessage();
        }
        return String.format(EngineConstants.WorkItemObjectChangedSuccessMessage, engine.getWorkItems().get(workItemID).getTitle(), changeType, changeValue);
    }
}
