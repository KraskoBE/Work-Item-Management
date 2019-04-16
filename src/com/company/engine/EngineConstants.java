package com.company.engine;

public final class EngineConstants {

    // Commands
    static final String CreatePersonCommand = "createperson";
    static final String CreateTeamCommand = "createteam";
    static final String CreateBoardCommand = "createboard";
    static final String CreateBugCommand = "createbug";
    static final String CreateStoryCommand = "createstory";
    static final String CreateFeedbackCommand = "createfeedback";
    static final String AddPersonToTeamCommand = "addpersontoteam";
    static final String ShowAllPeopleCommand = "showallpeople";
    static final String ShowAllTeamsCommand = "showallteams";
    static final String ShowAllTeamMembersCommand = "showteammembers";
    static final String ShowAllTeamBoardsCommand = "showteamboards";
    static final String ShowPersonActivityCommand = "showpersonactivity";
    static final String ShowTeamActivityCommand = "showteamactivity";
    static final String ShowBoardActivityCommand = "showboardactivity";
    static final String ChangeCommand = "change";
    static final String AssignCommand = "assign";
    static final String UnassignCommand = "unassign";
    static final String ListWorkItemsCommand = "listworkitems";
    static final String SortWorkItemsCommand = "sortworkitems";
    static final String AddCommentCommand = "addcomment";

    // Error messages
    public static final String InvalidCommandErrorMessage = "Invalid command name: %s";
    public static final String TeamExistsErrorMessage = "Team %s already exists";
    public static final String PersonExistsErrorMessage = "Person %s already exists";
    public static final String BoardExistsInTeamErrorMessage = "Board %s already exists in %s";
    public static final String TeamDoesNotExistErrorMessage = "Team %s does not exists";
    public static final String MemberDoesNotExistErrorMessage = "Member %s does not exist";
    public static final String PersonDoesNotExistErrorMessage = "Person %s does not exist";
    public static final String BoardDoesNotExistErrorMessage = "Board %s does not exist";
    public static final String BoardIsNotOnTheTeamErrorMessage = "Board %s is not on team %s";
    public static final String PersonAlreadyInTeamErrorMessage = "Person %s is already in team %s";
    public static final String NoMembersErrorMessage = "No members found";
    public static final String NoTeamsErrorMessage = "No teams found";
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
    public static final String PersonCreatedSuccessMessage = "Person %s created";
    public static final String BoardCreatedSuccessMessage = "Board %s created";
    public static final String BugCreatedSuccessMessage = "Bug %s created with ID:%d";
    public static final String StoryCreatedSuccessMessage = "Story %s created with ID:%d";
    public static final String FeedBackSuccessMessage = "Feedback %s created with ID:%d";
    public static final String PersonAddedSuccessMessage = "Person %s added to team %s";
    public static final String WorkItemObjectChangedSuccessMessage = "Changed %s's %s to %s";
    public static final String WorkItemAssignedSuccessMessage = "Work item with ID:%d assigned to %s";
    public static final String WorkItemUnassignedSuccessMessage = "Work item unassigned successfully";
    public static final String CommentAddedSuccessMessage = "Comment added successfully";


    // Activity history messages
    public static final String PersonJoined_TeamActivity = "%s joined the team";
    public static final String PersonJoined_PersonActivity = "Joined team %s";
    public static final String AddedComment_PersonActivity = "Added comment to work item with ID:%d";
    public static final String BoardAdded_TeamActivity = "Board %s was added";
    public static final String BoardCreated_BoardActivity = "Board created";
    public static final String PersonCreated_PersonActivity = "Person created";
    public static final String WorkItemAdded_UnitActivity = "Work item with ID:%d added";
    public static final String WorkItemRemoved_UnitActivity = "Work item with ID:%d removed";
    public static final String SeveritySet_WorkItemActivity = "Severity set to:%s";
    public static final String StatusSet_WorkItemActivity = "Status set to:%s";
    public static final String PrioritySet_WorkItemActivity = "Priority set to:%s";
    public static final String Assigned_WorkItemActivity = "Assigned to:%s";
    public static final String Unassigned_WorkItemActivity = "Unassigned";
    public static final String RatingSet_WorkItemActivity = "Rating set to:%d";
    public static final String AddedComment_WorkItemActivity = "Member %s added a comment";
    public static final String TeamCreated_TeamActivity = "Team created";

    //Priority
    static final String HIGH = "high";
    static final String MEDIUM = "medium";
    static final String LOW = "low";
    static final String INVALID_PRIORITY_NAME = "Invalid priority name: %s";

    //Severity
    static final String CRITICAL = "critical";
    static final String MAJOR = "major";
    static final String MINOR = "minor";
    static final String INVALID_SEVERITY_NAME = "Invalid severity name: %s";

    //Status
    static final String ACTIVE = "active";
    static final String FIXED = "fixed";
    static final String NOTDONE = "notdone";
    static final String INPROGRESS = "inprogress";
    static final String DONE = "done";
    static final String NEW = "new";
    static final String UNSCHEDULED = "unscheduled";
    static final String SCHEDULED = "scheduled";
    static final String INVALID_STATUS_NAME = "Invalid status name: %s";

    //Size
    static final String LARGE = "large";
    static final String SMALL = "small";
    static final String INVALID_SIZE_NAME = "Invalid size name: %s";

}
