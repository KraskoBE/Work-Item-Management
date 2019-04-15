package com.company.engine;

public final class EngineConstants {

    // Commands
    static final String CreateTeamCommand = "createteam";
    static final String CreateMemberCommand = "createmember";
    static final String CreateBoardCommand = "createboard";
    static final String CreateBugCommand = "createbug";
    static final String CreateStoryCommand = "createstory";
    static final String CreateFeedbackCommand = "createfeedback";
    static final String AddMemberToTeamCommand = "addmembertoteam";
    static final String ShowAllPeopleCommand = "showallpeople";
    static final String ShowAllTeamsCommand = "showallteams";
    static final String ShowAllTeamMembersCommand = "showteammembers";
    static final String ShowAllTeamBoardsCommand = "showteamboards";
    static final String ChangeCommand = "change";
    static final String AssignCommand = "assign";
    static final String UnassignCommand = "unassign";
    static final String ListWorkItemsCommand = "listworkitems";
    static final String SortWorkItemsCommand = "sortworkitems";

    // Error messages
    public static final String InvalidCommandErrorMessage = "Invalid command name: %s";
    public static final String TeamExistsErrorMessage = "Team %s already exists";
    public static final String MemberExistsErrorMessage = "Member %s already exists";
    public static final String BoardExistsInTeamErrorMessage = "Board %s already exists in %s";
    public static final String TeamDoesNotExistErrorMessage = "Team %s does not exists";
    public static final String MemberDoesNotExistErrorMessage = "Member %s does not exist";
    public static final String BoardIsNotOnTheTeamErrorMessage = "Board %s is not on team %s";
    public static final String MemberAlreadyInTeamErrorMessage = "Member %s is already in team %s";
    public static final String NoMembersErrorMessage = "No members found";
    public static final String NoTeamsErrorMessage = "No teams found";
    public static final String EmptyTeamErrorMessage = "Team %s does not have members";
    public static final String EmptyBoardsErrorMessage = "Team %s does not have boards";
    public static final String WorkItemDoesNotExistErrorMessage = "Work item with ID:%d does not exist";
    public static final String InvalidObjectTypeErrorMessage = "Invalid object type: %s";
    public static final String WorkItemListEmptyErrorMessage = "There are no work items";
    public static final String MemberIsNotFromTeamErrorMessage = "Member %s is not from team %s";
    public static final String ItemAlreadyAssignedErrorMessage = "Work item already assigned to %s";
    public static final String MemberDoesNotHaveWorkItemErrorMessage = "Member %s does not have work item with ID:%d assigned.";
    public static final String InvalidCategoryErrorMessage = "Invalid category name %s";
    public static final String InvalidSortTypeErrorMessage = "Invalid sort type %s";
    public static final String InvalidNumberOfParameters = "Invalid number of parameters";
    public static final String InvalidCommandParameters = "Invalid command parameters";

    // Success messages
    public static final String TeamCreatedSuccessMessage = "Team %s created";
    public static final String MemberCreatedSuccessMessage = "Member %s created";
    public static final String BoardCreatedSuccessMessage = "Board %s created";
    public static final String BugCreatedSuccessMessage = "Bug %s created with ID:%d";
    public static final String StoryCreatedSuccessMessage = "Story %s created with ID:%d";
    public static final String FeedBackSuccessMessage = "Feedback %s created with ID:%d";
    public static final String MemberAddedSuccessMessage = "Member %s added to team %s";
    public static final String WorkItemObjectChangedSuccessMessage = "Changed %s's %s to %s";
    public static final String WorkItemAssignedSuccessMessage = "Work item with ID:%d assigned to %s";
    public static final String WorkItemUnassignedSuccessMessage = "Work item unassigned successfully";


    // Activity history messages
    public static final String AddedWorkItemToHistory = "Created %s: %s(ID:%d)";
    public static final String MemberJoinedTeam = "%s joined %s";

    //Priority
    public static final String HIGH = "high";
    public static final String MEDIUM = "medium";
    public static final String LOW = "low";
    public static final String INVALID_PRIORITY_NAME = "Invalid priority name: %s";

    //Severity
    public static final String CRITICAL = "critical";
    public static final String MAJOR = "major";
    public static final String MINOR = "minor";
    public static final String INVALID_SEVERITY_NAME = "Invalid severity name: %s";

    //Status
    public static final String ACTIVE = "active";
    public static final String FIXED = "fixed";
    public static final String NOTDONE = "notdone";
    public static final String INPROGRESS = "inprogress";
    public static final String DONE = "done";
    public static final String NEW = "new";
    public static final String UNSCHEDULED = "unscheduled";
    public static final String SCHEDULED = "scheduled";
    public static final String INVALID_STATUS_NAME = "Invalid status name: %s";

    //Size
    public static final String LARGE = "large";
    public static final String SMALL = "small";
    public static final String INVALID_SIZE_NAME = "Invalid size name: %s";

}
