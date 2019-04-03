package com.company.engine;

public class EngineConstants {

    // Commands
    static final String CreateTeamCommand = "createteam";
    static final String CreateMemberCommand = "createmember";
    static final String CreateBoardCommand = "createboard";
    static final String CreateBugCommand = "createbug";

    // Error messages
    static final String InvalidCommandErrorMessage = "Invalid command name: %s";
    static final String TeamExistsErrorMessage = "Team %s already exists";
    static final String MemberExistsErrorMessage = "Member %s already exists";
    static final String BoardExistsInTeamErrorMessage = "Board %s already exists in %s";
    static final String TeamDoesNotExist = "Team %s does not exists";
    static final String BugExistsErrorMessage = "Bug %s already exists";
    static final String MemberDoesNotExist = "Member %s does not exist";
    static final String MemberIsNotFromTheTeam = "Member %s is not from team: %s";
    static final String BoardIsNotOnheTeam = "Board %s is not on team: %s";

    // Success messages
    static final String TeamCreatedSuccessMessage = "Team %s created";
    static final String MemberCreatedSuccessMessage = "Member %s created";
    static final String BoardCreatedSuccessMessage = "Board %s created";
    static final String BugCreatedSuccessMessage = "Bug %s created";

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

}