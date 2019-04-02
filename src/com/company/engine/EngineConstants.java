package com.company.engine;

public class EngineConstants {

    // Commands
    static final String CreateTeamCommand = "CreateTeam";
    static final String CreateMemberCommand = "CreateMember";
    static final String CreateBoardCommand = "CreateBoard";

    // Error messages
    static final String InvalidCommandErrorMessage = "Invalid command name: %s";
    static final String TeamExistsErrorMessage = "Team %s already exists";
    static final String MemberExistsErrorMessage = "Member %s already exists";
    static final String BoardExistsErrorMessage = "Board %s already exists";

    // Success messages
    static final String TeamCreatedSuccessMessage = "Team %s created";
    static final String MemberCreatedSuccessMessage = "Member %s created";
    static final String BoardCreatedSuccessMessage = "Board %s created";

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
    static final String FIXED = "fixed";
    static final String NOTDONE = "notdone";
    static final String INPROGRESS = "inprogress";
    static final String DONE = "done";
    static final String NEW = "new";
    static final String UNSCHEDULED = "unscheduled";
    static final String SCHEDULED = "scheduled";
    static final String INVALID_STATUS_NAME = "Invalid status name: %s";

}
